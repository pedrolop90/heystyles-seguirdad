package com.heystyles.seguridad.api.service.Impl;

import com.heystyles.seguridad.api.service.GenerarNumero;
import org.springframework.stereotype.Component;

@Component
public class GenerarNumeroImpl implements GenerarNumero {
    @Override
    public String generar(Long cantidadDigitos) {
        String value = String.valueOf(System.nanoTime());
        Long offset = value.length() - cantidadDigitos;
        return value.substring(offset.intValue());
    }
}
