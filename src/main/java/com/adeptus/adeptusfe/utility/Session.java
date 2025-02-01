package com.adeptus.adeptusfe.utility;

import lombok.Getter;
import lombok.Setter;


public class Session {
    private static String token;

    // Phương thức để thiết lập token
    public static void setToken(String token) {
        Session.token = token;
    }

    public static String getToken() {
        return token;
    }
    public static void clearToken() {
        token = null;
    }
}
