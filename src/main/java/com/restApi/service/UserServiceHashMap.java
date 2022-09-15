package com.restApi.service;

import com.restApi.entities.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserServiceHashMap implements UserService{
    private final HashMap<String, User> userHashMap = new HashMap<>();

    @Override
    public User create(User user) {
        userHashMap.put(user.getId(), user);
        return user;
    }

    @Override
    public User findById(String id) {
        return userHashMap.get(id);
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        for (String id : userHashMap.keySet()) {
            userList.add(userHashMap.get(id));
        }
        return userList;
    }

    @Override
    public void deleteById(String id) {
        userHashMap.remove(id);
    }

    @Override
    public User update(User user, String userId) {
        userHashMap.replace(userId, user);
        return user;
    }
}
