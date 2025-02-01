package com.adeptus.adeptusfe.service;

import com.adeptus.adeptusfe.utility.Session;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


public class HttpClient {

    private static final OkHttpClient client = new OkHttpClient();

    // Hàm khởi tạo client với interceptor
    static {
        // Interceptor để thêm token vào mỗi request
        Interceptor tokenInterceptor = chain -> {
            // Lấy token từ session hoặc nơi lưu trữ khác
            String token = Session.getToken(); // giả sử Session có phương thức getToken() để lấy token

            // Thêm header Authorization với Bearer token vào request
            Request originalRequest = chain.request();
            Request requestWithToken = originalRequest.newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            return chain.proceed(requestWithToken);
        };

        // Tạo OkHttpClient với interceptor
        client.newBuilder().addInterceptor(tokenInterceptor).build();
    }

    public static String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static String post(String url, RequestBody body) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static String put(String url, RequestBody body) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static String delete(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
