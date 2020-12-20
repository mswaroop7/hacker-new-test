package com.example.hackernewstest.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HNApiResponse {
    Boolean success;
    Boolean error;
    String errorStr;
    Object result;
}
