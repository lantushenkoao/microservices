package com.lantushenkoao.common.exceptions;

public class DestinationNotFoundException extends CalendarException{
    public DestinationNotFoundException(Class messageClass){
        super("Destination not found for message class: " + messageClass.getName());
    }
}