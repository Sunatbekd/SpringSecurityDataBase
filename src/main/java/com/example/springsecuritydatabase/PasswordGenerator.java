package com.example.springsecuritydatabase;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder();
        String password = "123";
        String encode = encoder.encode(password);
        System.out.println(encode);
    }
}
