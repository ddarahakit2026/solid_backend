package org.example.api.user.repository;

import org.example.api.user.model.LoginDtoReq;
import org.example.api.user.model.LoginDtoRes;
import org.example.api.user.model.SignupDtoReq;
import org.example.api.user.model.SignupDtoRes;

public interface UserRepository {
    SignupDtoRes signup(SignupDtoReq req);
    LoginDtoRes login(LoginDtoReq req);
}
