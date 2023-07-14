package com.example.block7crudvalidation.exception;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;


@Builder
@Getter
public class CustomError {
    Date timestamp;
    Integer httpCode;
    String message;
}