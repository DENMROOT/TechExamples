package com.techexample.example01.providers;

import javax.inject.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techexample.example01.model.Currency;

/**
 * ObjectMapper Guice provider
 */
public class ObjectMapperProvider implements Provider<ObjectMapper> {
    @Override
    public ObjectMapper get() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerSubtypes(Currency.class);
        return objectMapper;
    }
}
