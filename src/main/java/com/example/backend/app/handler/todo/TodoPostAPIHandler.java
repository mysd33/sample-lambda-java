package com.example.backend.app.handler.todo;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.example.backend.app.handler.common.APIUtil;
import com.example.backend.domain.model.Todo;
import com.example.backend.domain.service.todo.TodoService;
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
        // サービスの実行
        Todo newTodo = todoService.create(todo);
        // TODO: Mapstructでのオブジェクトコピー
        TodoResource result = TodoResource.builder().todoId(newTodo.getTodoId()).todoTitle(newTodo.getTitle()).build();
        return APIUtil.createAPIGwResponse(objectMapper, result);
    }

}
