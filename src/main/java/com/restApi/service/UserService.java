package com.restApi.service;

import com.restApi.entities.User;

import java.util.List;

public interface UserService
{
    User create(User user );

    User findById( String id );

    List<User> getAll();

    void deleteById( String id );

    User update( User user, String userId );
}