package com.pragma.serviciocliente.infraestructura.controlador;

import java.util.HashMap;
import java.util.Map;

public  class Message {

    public static Map<String, String> JsonMessage(String code, String message){
         HashMap<String, String> map = new HashMap<>();
        map.put(code, message);
        return map;
    }
}
