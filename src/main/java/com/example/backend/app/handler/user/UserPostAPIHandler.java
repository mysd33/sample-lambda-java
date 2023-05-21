package com.example.backend.app.handler.user;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.example.backend.domain.model.User;
import com.example.backend.domain.service.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

/**
 * Spring Cloud FunctionによるTodoサービスのGet APIハンドラクラス
 *
 */
@Component
@RequiredArgsConstructor
public class UserPostAPIHandler implements Function<UserResource, APIGatewayProxyResponseEvent> {
    private final UserService userService;
    private final ObjectMapper objectMapper;
    
    @Override
    public APIGatewayProxyResponseEvent apply(UserResource resource) {        
        User user = User.builder().name(resource.getUserName()).build();
        //サービスの実行
        User newUser = userService.create(user);        
        UserResource result = UserResource.builder().userId(newUser.getUserId()).userName(newUser.getName()).build();
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
