package org.example.api.user.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.api.user.model.LoginDtoReq;
import org.example.api.user.model.LoginDtoRes;
import org.example.api.user.model.SignupDtoReq;
import org.example.api.user.model.SignupDtoRes;

public interface UserService {
    SignupDtoRes signup(SignupDtoReq req);
    public LoginDtoRes login(HttpServletRequest req, HttpServletResponse resp, LoginDtoReq reqDto);
}
