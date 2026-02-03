package org.example.api.common;

import java.util.HashMap;
import java.util.Map;

public class AppConfig {

    private final Map<String, Controller> controllerMap = new HashMap<>();


    public AppConfig() {
//        controllerMap.put("주소", 컨트롤러 객체);
    }

    public Controller getController(String uri) {
        return controllerMap.get(uri);
    }
}
