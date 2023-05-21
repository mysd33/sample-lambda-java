package com.example.backend.app.handler.user;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.example.backend.domain.model.User;
import com.example.backend.domain.service.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

/**
 * Spring Cloud FunctionによるユーザサービスのGet APIハンドラクラス
 *
 */
@Component
@RequiredArgsConstructor
public class UserGetAPIHandler implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private final UserService userService;
    private final ObjectMapper objectMapper;
    
    @Override    
    public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent request) {
               
        String userId = request.getPathParameters().get("user_id"); 
        User user = userService.findOne(userId);
        
        UserResource result = UserResource.builder().userId(user.getUserId()).userName(user.getName()).build();
        String message;
        try {
            message = objectMapper.writeValueAsString(result);
            return createAPIGwResponse(200, message);
        } catch (JsonProcessingException e) {
            //TODO: 仮の例外処理
            return createAPIGwResponse(500, e.getMessage());
        }                                

    }   
    
    private APIGatewayProxyResponseEvent createAPIGwResponse(Integer statusCode, String message){
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(statusCode)
                .withBody(String.format("%s%n", message));
    }
}
