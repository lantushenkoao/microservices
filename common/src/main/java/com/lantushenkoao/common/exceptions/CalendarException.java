package com.lantushenkoao.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CalendarException extends RuntimeException{
    private String message;
}