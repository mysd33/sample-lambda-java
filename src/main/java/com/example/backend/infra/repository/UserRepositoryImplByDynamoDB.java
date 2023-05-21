package com.example.backend.infra.repository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.example.backend.domain.model.User;
import com.example.backend.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;


/**
 * DynamoDBにアクセスするUserRepository実装クラス
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImplByDynamoDB implements UserRepository {
    private final DynamoDbEnhancedClient enhancedClient;

    @Value("${aws.dynamodb.user-tablename}")
    private String userTableName;
    
    // （参考）DynamoDbEnhancedClientの実装例
    // https://docs.aws.amazon.com/ja_jp/sdk-for-java/latest/developer-guide/examples-dynamodb-enhanced.html
    // https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/javav2/example_code/dynamodb/src/main/java/com/example/dynamodb

    
    @Override
    public boolean insert(User user) {
        user.setUserId(UUID.randomUUID().toString());
        DynamoDbTable<UserTableItem> dynamoDb = createDynamoDBClient();
        UserTableItem userItem = UserTableItem.builder()//
                .userId(user.getUserId())
                .name(user.getName())
                .build();
        dynamoDb.putItem(userItem);
        
        return true;
    }
    
    @Override
    public User findOne(String userId) {
        DynamoDbTable<UserTableItem> dynamoDb = createDynamoDBClient();
        Key key = Key.builder().partitionValue(userId).build();
        UserTableItem userItem = dynamoDb.getItem(r -> r.key(key));
        // TODO: Mapstructでのオブジェクトコピー
        return User.builder()//
                .userId(userItem.getUserId())
                .name(userItem.getName())
                .build();
    }
    private DynamoDbTable<UserTableItem> createDynamoDBClient() {
        return enhancedClient.table(userTableName, TableSchema.fromBean(UserTableItem.class));
    }
}
