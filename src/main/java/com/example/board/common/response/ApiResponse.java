package com.example.board.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private T data;
    
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(data);
    }
}