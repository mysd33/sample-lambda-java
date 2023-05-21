package com.example.backend.infra.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.backend.domain.model.Todo;
import com.example.backend.domain.repository.TodoRepository;

/**
 * 
 * DynamoDBにアクセスするTodoRepository実装クラス
 *
 */
@Repository
public class TodoRepositoryImplByDynamoDB implements TodoRepository {

    @Override
    public boolean insert(Todo todo) {
        //TODO: 仮実装
        todo.setTodoId(UUID.randomUUID().toString());
        return true;
    }

    @Override
    public Todo findById(String todoId) {
        //TODO: 仮実装
        return Todo.builder().todoId(todoId).title("dummy").build();
        
    }


}
