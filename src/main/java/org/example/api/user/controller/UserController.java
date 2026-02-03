package org.example.api.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.api.common.BaseResponse;
import org.example.api.common.Controller;
import org.example.api.user.model.LoginDtoReq;
import org.example.api.user.model.LoginDtoRes;
import org.example.api.user.model.SignupDtoReq;
import org.example.api.user.model.SignupDtoRes;
import org.example.api.user.service.UserService;
import org.example.api.utils.JsonParser;

public class UserController implements Controller  {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public BaseResponse process(HttpServletRequest req, HttpServletResponse resp) {
        if(req.getRequestURI().contains("login")) {
            LoginDtoReq reqDto = JsonParser.from(req, LoginDtoReq.class);
            LoginDtoRes resDto = userService.login(req, resp, reqDto);

            return BaseResponse.success(resDto);
        } else if(req.getRequestURI().contains("signup")) {
            SignupDtoReq reqDto = JsonParser.from(req, SignupDtoReq.class);
            SignupDtoRes resDto = userService.signup(reqDto);

            return BaseResponse.success(resDto);
        }
        return BaseResponse.fail("실패");
    }
}
