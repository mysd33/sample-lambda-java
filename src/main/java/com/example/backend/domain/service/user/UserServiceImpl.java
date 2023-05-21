package com.example.backend.domain.service.user;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.backend.domain.model.User;


/**
 * UserServiceの実装クラス
 *
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User create(User user) {
        //TODO:仮実装
        user.setUserId(UUID.randomUUID().toString());        
        return user;
    }

    @Override
    public User findOne(String userId) {
        //TODO:仮実装
        return User.builder().userId(userId).name("dummy").build();
    }
}
