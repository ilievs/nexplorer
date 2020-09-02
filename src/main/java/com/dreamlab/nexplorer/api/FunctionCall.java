package com.dreamlab.nexplorer.api;

import java.util.List;

public interface FunctionCall {

    String getFunctionName();

    String execute(List<String> args);
}
