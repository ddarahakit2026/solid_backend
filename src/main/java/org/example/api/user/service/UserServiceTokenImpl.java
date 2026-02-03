package org.example.api.user.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.api.user.model.LoginDtoReq;
import org.example.api.user.model.LoginDtoRes;
import org.example.api.user.model.SignupDtoReq;
import org.example.api.user.model.SignupDtoRes;
import org.example.api.user.repository.UserRepository;
import org.example.api.utils.JwtUtil;

public class UserServiceTokenImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceTokenImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public SignupDtoRes signup(SignupDtoReq req) {
        return userRepository.signup(req);
    }


    @Override
    public LoginDtoRes login(HttpServletRequest req, HttpServletResponse resp, LoginDtoReq reqDto) {
        LoginDtoRes resDto = userRepository.login(reqDto);
        if(resDto != null) {
            String token = JwtUtil.createToken(resDto.getIdx(), resDto.getEmail());
            resp.setHeader("Set-Cookie", "ATOKEN="+token + "; Path=/");

            return resDto;
        }

        return null;    }
}
