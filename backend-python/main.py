import os
import json
from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, Field
from typing import List, Optional
from google import genai
from google.genai import types

# Inicializamos FastAPI
app = FastAPI(title="Microservicio de IA - Auditoría Real")

# Inicializamos el cliente de Gemini de Google
client = genai.Client()

# --- CONTRATOS DE DATOS (Con alias estrictos para compatibilidad con Java) ---
class Hallazgo(BaseModel):
    categoria: str
    severidad: str
    lineaCodigo: Optional[int] = Field(None, alias="lineaCodigo")
    descripcion: str

    class Config:
        populate_by_name = True

class AnalisisResponse(BaseModel):
    codigoRefactorizado: str = Field(..., alias="codigoRefactorizado")
    explicacionPedagogica: str = Field(..., alias="explicacionPedagogica")
    hallazgos: List[Hallazgo]

    class Config:
        populate_by_name = True

class AnalisisRequest(BaseModel):
    lenguaje: str
    codigoFuente: str


# --- ENDPOINT CON CONEXIÓN REAL A LA IA ---
@app.post("/api/ia/auditar")
async def procesar_codigo(request: AnalisisRequest):
    print(f"Python-IA: Recibida petición real para auditar código en {request.lenguaje}")
    
    prompt_sistema = (
        f"Actúas como un experto Senior en Seguridad Informática y Calidad de Código. "
        f"Analiza el código fuente provisto por el usuario, el cual está escrito en el lenguaje: {request.lenguaje}. "
        f"Identifica vulnerabilidades (como inyección SQL, OWASP Top 10), errores de sintaxis o malas prácticas. "
        f"Debes proveer una versión refactorizada y segura del código, una explicación pedagógica clara "
        f"y un listado de hallazgos específicos indicando la línea del error (si aplica), categoría y severidad. "
        f"Es MANDATORIO que uses exactamente la estructura del esquema JSON provisto."
    )

    try:
        # Llamada oficial a la API de Gemini usando 2.5 Flash
        response = client.models.generate_content(
            model='gemini-2.5-flash',
            contents=request.codigoFuente,
            config=types.GenerateContentConfig(
                system_instruction=prompt_sistema,
                response_mime_type="application/json",
                response_schema=AnalisisResponse,
                temperature=0.2
            ),
        )
        
        # Limpieza de comillas rebeldes en caso de que Gemini las agregue
        texto_limpio = response.text.strip()
        if texto_limpio.startswith("'") and texto_limpio.endswith("'"):
            texto_limpio = texto_limpio[1:-1].strip()
            
        print("\n--- TEXTO PROCESADO DESDE GOOGLE ---")
        print(texto_limpio)
        print("-------------------------------------\n")
        
        # Convertimos el String limpio en un diccionario de Python real (JSON válido)
        objeto_json = json.loads(texto_limpio)
        return objeto_json

    except Exception as e:
        print(f"Error crítico al invocar la API de Gemini: {str(e)}")
        raise HTTPException(status_code=500, detail=f"Error en el motor de IA: {str(e)}")