package com.apigw.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Map;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private LocalDate timestamp;
    private int statusCode;
    private String reason;
    private String message;
    private String developerMessage;
    private Map<?,?> data;

}
