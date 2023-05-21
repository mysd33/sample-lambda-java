package com.example.backend.infra.repository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.example.backend.domain.model.Todo;
import com.example.backend.domain.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

/**
 * 
 * DynamoDBにアクセスするTodoRepository実装クラス
 *
 */
@Repository
@RequiredArgsConstructor
public class TodoRepositoryImplByDynamoDB implements TodoRepository {
    private final DynamoDbEnhancedClient enhancedClient;

    @Value("${aws.dynamodb.todo-tablename}")
    private String todoTableName;

    // （参考）DynamoDbEnhancedClientの実装例
    // https://docs.aws.amazon.com/ja_jp/sdk-for-java/latest/developer-guide/examples-dynamodb-enhanced.html
    // https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/javav2/example_code/dynamodb/src/main/java/com/example/dynamodb

    @Override
    public boolean insert(Todo todo) {        
        todo.setTodoId(UUID.randomUUID().toString());
        DynamoDbTable<TodoTableItem> dynamoDb = createDynamoDBClient();
        TodoTableItem todoItem = TodoTableItem.builder()//
                .todoId(todo.getTodoId())//
                .title(todo.getTitle())//
                .build();
        dynamoDb.putItem(todoItem);

        return true;
    }

    @Override
    public Todo findById(String todoId) {
        DynamoDbTable<TodoTableItem> dynamoDb = createDynamoDBClient();
        Key key = Key.builder().partitionValue(todoId).build();
        TodoTableItem todoItem = dynamoDb.getItem(r -> r.key(key));
        // TODO: Mapstructでのオブジェクトコピー
        return Todo.builder()//
                .todoId(todoItem.getTodoId())//
                .title(todoItem.getTitle())//
                .build();
    }

    private DynamoDbTable<TodoTableItem> createDynamoDBClient() {
        return enhancedClient.table(todoTableName, TableSchema.fromBean(TodoTableItem.class));
    }

}
