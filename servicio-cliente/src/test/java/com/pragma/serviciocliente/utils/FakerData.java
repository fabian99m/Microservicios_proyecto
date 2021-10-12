package com.pragma.serviciocliente.utils;

import com.github.javafaker.Faker;
import com.pragma.serviciocliente.dominio.Cliente;

import java.util.Locale;

public class FakerData {

   private static final Faker faker = new Faker(new Locale("es"));

   public static Cliente getCliente() {
      return Cliente.builder()
              .nombres(faker.name().firstName())
              .apellidos(faker.name().lastName())
              .tipoId("CC")
              .numeroId(Integer.toString(faker.number().numberBetween(1, 99999999)))
              .edad(faker.number().numberBetween(1, 100))
              .ciudadNacimiento(faker.address().city())
              .foto(faker.app().name())
              .build();
   }

}
