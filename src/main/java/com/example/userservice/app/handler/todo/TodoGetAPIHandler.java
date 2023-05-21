package com.example.userservice.app.handler.todo;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

/**
 * Spring Cloud Functionでのハンドラクラス
 *
 */
@Component
public class TodoGetAPIHandler implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    @Override    
    public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent request) {
        // TODO: サービス呼び出しの実装
        String message = "Todo Get:" + request.getPathParameters().get("todo_id"); 
        return createAPIGwResponse(200, message);

    }   
    
    private APIGatewayProxyResponseEvent createAPIGwResponse(Integer statusCode, String message){
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(statusCode)
                .withBody(String.format("%s%n", message));
    }
}