package com.example.backend.domain.service.user;

import org.springframework.stereotype.Service;

import com.example.backend.domain.model.User;
import com.example.backend.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;


/**
 * UserServiceの実装クラス
 *
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    
    @Override
    public User create(User user) {
        userRepository.insert(user);        
        return user;
    }

    @Override
    public User findOne(String userId) {
        return userRepository.findOne(userId);
    }
}
