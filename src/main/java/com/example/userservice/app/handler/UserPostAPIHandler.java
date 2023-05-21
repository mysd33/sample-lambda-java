package com.example.userservice.app.handler;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.example.userservice.domain.model.User;

/**
 * Spring Cloud Functionでのハンドラクラス
 *
 */
@Component
public class UserPostAPIHandler implements Function<User, APIGatewayProxyResponseEvent> {
    @Override
    public APIGatewayProxyResponseEvent apply(User user) {
        // TODO: サービス呼び出しの実装
        String message = "Hello Post:" + user.userName; 
        return createAPIGwResponse(200, message);
    }
    
    private APIGatewayProxyResponseEvent createAPIGwResponse(Integer statusCode, String message){
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(statusCode)
                .withBody(String.format("%s%n", message));
    }
}
