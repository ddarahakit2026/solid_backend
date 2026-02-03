package org.example.api.user.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.api.user.model.LoginDtoReq;
import org.example.api.user.model.LoginDtoRes;
import org.example.api.user.model.SignupDtoReq;
import org.example.api.user.model.SignupDtoRes;
import org.example.api.user.repository.UserRepository;

public class UserServiceSessionImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceSessionImpl(UserRepository userRepository) {
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
            HttpSession session = req.getSession(true);
            session.setAttribute("USER_ID", resDto.getIdx());

            return resDto;
        }

        return null;
    }
}
