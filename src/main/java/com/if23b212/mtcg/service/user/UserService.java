package com.if23b212.mtcg.service.user;

import com.if23b212.mtcg.db.user.UserRepository;
import com.if23b212.mtcg.exception.user.UserExceptionHelper;
import com.if23b212.mtcg.model.user.User;
import com.if23b212.mtcg.model.user.UserCredentials;
import com.if23b212.mtcg.service.jwt.JWTService;

public class UserService {
    UserRepository repo = new UserRepository();
    public void saveUser(UserCredentials userCredentials) throws UserExceptionHelper.AlreadyRegisteredException {
         repo.saveUser(userCredentials);
    }

    public String loginUser(UserCredentials userCredentials) throws UserExceptionHelper.InvalidCredentialsException {
        User user = repo.loginUser(userCredentials);
        return user != null ? JWTService.generateToken(user.getUsername()) : null;
    }

}
