package com.pragma.serviciocliente.utils;

import com.github.javafaker.Faker;
import com.pragma.serviciocliente.dominio.Cliente;

import java.util.Locale;
import java.util.Random;

public class FakerData {

    private static final Faker faker = new Faker(new Locale("es"));
    private static final String[] tiposId = {"CC", "PA", "TI"};

    public static Cliente getCliente() {
        return Cliente.builder()
                .nombres(faker.name().firstName())
                .apellidos(faker.name().lastName())
                .tipoId(getRandomItem(tiposId))
                .numeroId(Integer.toString(faker.number().numberBetween(1, 99999999)))
                .edad(faker.number().numberBetween(1, 100))
                .ciudadNacimiento(faker.address().city())
                .foto(faker.app().name())
                .build();
    }

    private static String getRandomItem(String[] arr) {
        return arr[(new Random()).nextInt(arr.length)];
    }

}
