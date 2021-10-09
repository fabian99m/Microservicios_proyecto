package com.pragma.serviciocliente.infraestructura.controlador;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data @Builder
public  class ResponseMessage {

    private String code;
    private String response;

    public static Map<String, String> JsonMessage(String code, String message){
         HashMap<String, String> map = new HashMap<>();
        map.put(code, message);
        return map;
    }
}
