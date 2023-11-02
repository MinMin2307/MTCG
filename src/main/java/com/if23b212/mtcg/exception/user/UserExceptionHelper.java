package com.if23b212.mtcg.exception.user;

import com.if23b212.mtcg.exception.card.PackageExceptionHelper;
import com.if23b212.mtcg.model.card.Card;
import com.if23b212.mtcg.model.user.User;

import java.util.List;

public class UserExceptionHelper {
    public static final String ALREADY_REGISTERED_EXCEPTION_MESSAGE = "User with same username already registered";
    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "User not found.";
    public static final String INVALID_CREDENTIALS_EXCEPTION_MESSAGE = "Invalid username/password provided";

    public static class UserException extends RuntimeException {
        public UserException(String message) {
            super(message);
        }
    }

    public static class AlreadyRegisteredException extends UserExceptionHelper.UserException {
        public AlreadyRegisteredException() {
            super(ALREADY_REGISTERED_EXCEPTION_MESSAGE);
        }
    }

    public static class UserNotFoundException extends UserExceptionHelper.UserException {
        public UserNotFoundException() {
            super(USER_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    public static class InvalidCredentialsException extends UserExceptionHelper.UserException {
        public InvalidCredentialsException() {
            super(INVALID_CREDENTIALS_EXCEPTION_MESSAGE);
        }
    }
}
