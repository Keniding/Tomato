package com.keniding.tomato.config;

public class AuthManager {
    private static String authToken = null;

    public static void setAuthToken(String token) {
        authToken = token;
    }

    public static String getAuthToken() {
        return authToken;
    }
}