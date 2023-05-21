package com.example.backend.domain.service.todo;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.backend.domain.model.Todo;

/**
 * TodoServiceの実装クラス
 *
 */
@Service
public class TodoServiceImpl implements TodoService {
    @Override
    public Todo create(Todo todo) {
        //TODO: 仮実装
        todo.setTodoId(UUID.randomUUID().toString());
        return todo;
    }

    @Override
    public Todo findOne(String todoId) {
        //TODO: 仮実装
        return Todo.builder().todoId(todoId).title("dummy").build();
    }
}
