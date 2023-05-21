package com.example.userservice.app.handler.user;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

/**
 * Spring Cloud Functionでのハンドラクラス
 *
 */
@Component
public class UserPostAPIHandler implements Function<UserResource, APIGatewayProxyResponseEvent> {
    @Override
    public APIGatewayProxyResponseEvent apply(UserResource user) {
        // TODO: サービス呼び出しの実装
        String message = "User Post:" + user.getUserName(); 
        return createAPIGwResponse(200, message);
    }
    
    private APIGatewayProxyResponseEvent createAPIGwResponse(Integer statusCode, String message){
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(statusCode)
                .withBody(String.format("%s%n", message));
    }
}
