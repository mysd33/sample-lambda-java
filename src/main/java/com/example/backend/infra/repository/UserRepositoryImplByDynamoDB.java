package com.example.backend.infra.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.backend.domain.model.User;
import com.example.backend.domain.repository.UserRepository;


/**
 * DynamoDBにアクセスするUserRepository実装クラス
 */
@Repository
public class UserRepositoryImplByDynamoDB implements UserRepository {
    @Override
    public boolean insert(User user) {
        //TODO: 仮実装
        user.setUserId(UUID.randomUUID().toString());    
        return true;
    }
    
    @Override
    public User findOne(String userId) {
        //TODO:仮実装
        return User.builder().userId(userId).name("dummy").build();
    }

}
