package com.auditoria.backend;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestPassword {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder =
                new BCryptPasswordEncoder();

        String hash = encoder.encode("1234");

        System.out.println("[Password generada: " + hash + "]");
    }
}