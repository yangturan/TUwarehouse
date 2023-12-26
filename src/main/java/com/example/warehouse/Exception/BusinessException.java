package com.example.warehouse.Exception;

import org.springframework.stereotype.Component;

public class BusinessException extends RuntimeException{
    public BusinessException(String msg){
        super(msg);
    }
    public BusinessException(){
        super();
    }
}
