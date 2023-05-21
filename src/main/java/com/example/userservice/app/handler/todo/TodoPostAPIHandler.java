package com.example.userservice.app.handler.todo;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.example.userservice.domain.model.Todo;

/**
 * Spring Cloud Functionでのハンドラクラス
 *
 */
@Component
public class TodoPostAPIHandler implements Function<Todo, APIGatewayProxyResponseEvent> {
    @Override
    public APIGatewayProxyResponseEvent apply(Todo todo) {
        // TODO: サービス呼び出しの実装
        String message = "Todo Post:" + todo.title; 
        return createAPIGwResponse(200, message);
    }
    
    private APIGatewayProxyResponseEvent createAPIGwResponse(Integer statusCode, String message){
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(statusCode)
                .withBody(String.format("%s%n", message));
    }
}
