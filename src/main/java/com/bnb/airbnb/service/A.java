package com.bnb.airbnb.service;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class A {
    public static void main(String[] args) {
        String bidyut123 = BCrypt.hashpw("bidyut123", BCrypt.gensalt(10));
        System.out.println(bidyut123);
    }
}
