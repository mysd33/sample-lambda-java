package com.example.backend.app.handler.todo;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.example.backend.domain.model.Todo;
import com.example.backend.domain.service.todo.TodoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

/**
 * Spring Cloud FunctionによるTodoサービスのPOST APIハンドラクラス
 *
 */
@Component
@RequiredArgsConstructor
public class TodoPostAPIHandler implements Function<TodoResource, APIGatewayProxyResponseEvent> {
    private final TodoService todoService;
    private final ObjectMapper objectMapper;
    
    @Override
    public APIGatewayProxyResponseEvent apply(TodoResource resource) {        
        Todo todo = Todo.builder().title(resource.getTodoTitle()).build();        
        Todo newTodo = todoService.create(todo);        
        TodoResource result = TodoResource.builder().todoId(newTodo.getTodoId()).todoTitle(newTodo.getTitle()).build();
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
