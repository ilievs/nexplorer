package com.dreamlab.nexplorer.controller;

import com.dreamlab.nexplorer.api.FunctionCallExecutor;
import com.dreamlab.nexplorer.common.Constants;
import com.dreamlab.nexplorer.controller.model.FunctionCallPayload;
import com.dreamlab.nexplorer.exception.UnknownFunctionException;
import com.dreamlab.nexplorer.exception.WrongNumberOfArgumentsException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FunctionController.class)
public class FunctionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FunctionCallExecutor functionCallExecutor;

    @Autowired
    private ObjectMapper objectMapper;

    private MockHttpServletRequestBuilder postJsonRequest(String path, Object payload)
            throws JsonProcessingException {

        return post(path)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(payload));
    }

    @Test
    public void testFunctionCallWithValidArguments() throws Exception {
        String functionName = "isPrime";
        List<String> arguments = Collections.singletonList("76127863784678234");
        when(functionCallExecutor.execute(functionName, arguments)).thenReturn(Constants.TRUE_STRING);

        var path = "/function/call";
        var payload = new FunctionCallPayload(functionName, arguments);

        this.mockMvc.perform(postJsonRequest(path, payload))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.result", is("true")));
    }

    @Test
    public void testFunctionCallWithUnknownFunctionName() throws Exception {
        String functionName = "sqrt";
        List<String> arguments = Collections.singletonList("76127863784678234");
        var e = new UnknownFunctionException(functionName);
        when(functionCallExecutor.execute(functionName, arguments)).thenThrow(e);

        var path = "/function/call";
        var payload = new FunctionCallPayload(functionName, arguments);

        this.mockMvc.perform(postJsonRequest(path, payload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(e.getMessage()));
    }

    @Test
    public void testFunctionCallWithLessThanRequiredNumberOfArguments() throws Exception {
        String functionName = "isPrime";
        List<String> arguments = Collections.emptyList();
        var e = new WrongNumberOfArgumentsException(functionName, 1, arguments);
        when(functionCallExecutor.execute(functionName, arguments)).thenThrow(e);

        var path = "/function/call";
        var payload = new FunctionCallPayload(functionName, arguments);

        this.mockMvc.perform(postJsonRequest(path, payload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(e.getMessage()));
    }

    @Test
    public void testFunctionCallWithMoreThanRequiredNumberOfArguments() throws Exception {
        String functionName = "isPrime";
        List<String> arguments = Arrays.asList("823764782364", "34623782378");
        var e = new WrongNumberOfArgumentsException(functionName, 1, arguments);
        when(functionCallExecutor.execute(functionName, arguments)).thenThrow(e);

        var path = "/function/call";
        var payload = new FunctionCallPayload(functionName, arguments);

        this.mockMvc.perform(postJsonRequest(path, payload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(e.getMessage()));
    }
}
