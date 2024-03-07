package com.dreamlab.nexplorer.controller;

import com.dreamlab.nexplorer.api.FunctionCallExecutor;

import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

public class MocksConfiguration {

    @Bean
    public FunctionCallExecutor sessionRepository() {
        return mock(FunctionCallExecutor.class);
    }
}