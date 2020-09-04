package com.dreamlab.nexplorer.controller;

import com.dreamlab.nexplorer.api.FunctionCallExecutor;
import com.dreamlab.nexplorer.controller.model.FunctionCallPayload;
import com.dreamlab.nexplorer.controller.model.FunctionCallResultPayload;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/function")
public class FunctionController {

    @Autowired
    private FunctionCallExecutor functionCallExecutor;

    @ApiOperation(value = "Calls a mathematical function and returns the result of the execution")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Returns the result of calling the function",
                    response = FunctionCallResultPayload.class),

            @ApiResponse(
                    code = 400,
                    message = "User supplied invalid input. Returns a message with a description of the user error")
    })
    @PostMapping(value = "/call",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public FunctionCallResultPayload callFunction(@RequestBody FunctionCallPayload payload) {
        String result = functionCallExecutor.execute(payload.getFunctionName(), payload.getArguments());
        return new FunctionCallResultPayload(result);
    }
}
