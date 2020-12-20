package com.example.hackernewstest.utils;

import com.example.hackernewstest.models.HNApiResponse;

public class HNResponseUtil {

    public static HNApiResponse createSuccessResponse(Object response) {
        return HNApiResponse.builder()
                .error(false)
                .success(true)
                .result(response)
                .build();
    }

    public static HNApiResponse createErrorResponse(String errorStr) {
        return HNApiResponse.builder()
                .error(false)
                .errorStr(errorStr)
                .success(true)
                .result(null)
                .build();
    }
}
