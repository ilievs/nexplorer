package com.dreamlab.nexplorer.api;

import java.util.List;

public interface FunctionCallExecutor {

    String execute(String functionName, List<String> arguments);
}
