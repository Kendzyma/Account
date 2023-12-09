package com.accountstatement.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.Instant;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse <T> {
    private int status;
    private String message;
    private Instant date;
    private T data;

    private <T> ApiResponse<T> success(T data){
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setDate(Instant.now());
        apiResponse.setMessage("Success");
        apiResponse.setStatus(200);
        apiResponse.setData(data);

        return apiResponse;
    }

    private <T> ApiResponse<T> error(String message,int status){
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setDate(Instant.now());
        apiResponse.setMessage(message);
        apiResponse.setStatus(200);

        return apiResponse;
    }

}
