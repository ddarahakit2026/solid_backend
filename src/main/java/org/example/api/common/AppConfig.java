package org.example.api.common;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.example.api.user.controller.UserController;
import org.example.api.user.repository.UserJdbcRepository;
import org.example.api.user.repository.UserRepository;
import org.example.api.user.service.UserService;
import org.example.api.user.service.UserServiceSessionImpl;

import java.util.HashMap;
import java.util.Map;

public class AppConfig {

    private final Map<String, Controller> controllerMap = new HashMap<>();

    private final HikariDataSource dataSource = new HikariDataSource(new HikariConfig("db.properties"));

    private final UserRepository userRepository = new UserJdbcRepository(dataSource);
    private final UserService userService = new UserServiceSessionImpl(userRepository);
    private final UserController userController = new UserController(userService);

    public AppConfig() {

        controllerMap.put("/user/signup", userController);
        controllerMap.put("/user/login", userController);


    }

    public Controller getController(String uri) {
        return controllerMap.get(uri);
    }
}
