package com.restApi.service.impl;

import com.restApi.dto.UserDto;
import com.restApi.entities.User;
import com.restApi.repository.UserRepository;
import com.restApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceMongoDB implements UserService {

    private final UserRepository userRepository;

    public UserServiceMongoDB(@Autowired UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        userRepository.insert(user);
        return user;
    }

    @Override
    public User findById( String id )
    {
        System.out.println(userRepository.findById(id).get());
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById( String id ) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user, String id ) {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            userRepository.insert(user);
            return user;
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
