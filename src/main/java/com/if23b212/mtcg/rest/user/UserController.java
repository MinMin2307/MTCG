package com.if23b212.mtcg.rest.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.if23b212.mtcg.exception.user.UserExceptionHelper;
import com.if23b212.mtcg.model.user.UserCredentials;
import com.if23b212.mtcg.rest.HTTPMethod;
import com.if23b212.mtcg.rest.HTTPStatusCode;
import com.if23b212.mtcg.service.user.UserService;
import com.if23b212.mtcg.util.HttpUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

public class UserController {

    private final static ObjectMapper objectMapper = new ObjectMapper();
    private final static UserService service = new UserService();

    private final static String REGISTERED_RESPONSE_MESSAGE = "User successfully created";
    private final static String LOGGED_IN_RESPONSE_MESSAGE = "User login successful";

    public static class UserRegistration implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if(exchange != null && HTTPMethod.checkMethod(HTTPMethod.POST, exchange.getRequestMethod())) {

                InputStream is = exchange.getRequestBody();
                try {
                    UserCredentials userCredentials = objectMapper.readValue(is, UserCredentials.class);

                    service.saveUser(userCredentials);
                    HttpUtils.sendResponse(exchange,REGISTERED_RESPONSE_MESSAGE,HTTPStatusCode.CREATED.getCode());

                }catch (UserExceptionHelper.AlreadyRegisteredException e) {
                    e.printStackTrace();
                    HttpUtils.sendResponse(exchange,e.getMessage(),HTTPStatusCode.CONFLICT.getCode());
                }
            } else {
                HttpUtils.sendResponse(exchange,HTTPStatusCode.METHOD_NOT_ALLOWED.name(),HTTPStatusCode.METHOD_NOT_ALLOWED.getCode());
            }
        }
    }

    public static class UserLogin implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if(exchange != null && HTTPMethod.checkMethod(HTTPMethod.POST, exchange.getRequestMethod())) {

                InputStream is = exchange.getRequestBody();
                try {
                    UserCredentials userCredentials = objectMapper.readValue(is, UserCredentials.class);
                    String token = service.loginUser(userCredentials);
                    // Create a JSON response with the token
                    String jsonResponse = "{\"description\":\""+LOGGED_IN_RESPONSE_MESSAGE+"\", \"token\":\"" + token + "\"}";
                    HttpUtils.sendResponse(exchange, jsonResponse, HTTPStatusCode.OK.getCode());

                }catch (UserExceptionHelper.InvalidCredentialsException e) {
                    e.printStackTrace();
                    HttpUtils.sendResponse(exchange,e.getMessage(),HTTPStatusCode.UNAUTHORIZED.getCode());
                }
            } else {
                HttpUtils.sendResponse(exchange,HTTPStatusCode.METHOD_NOT_ALLOWED.name(),HTTPStatusCode.METHOD_NOT_ALLOWED.getCode());
            }
        }
    }
}
