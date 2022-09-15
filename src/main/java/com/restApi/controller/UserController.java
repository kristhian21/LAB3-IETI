package com.restApi.controller;

import com.restApi.dto.UserDto;
import com.restApi.entities.User;
import com.restApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> result = new ArrayList<>();
        for (User user: userService.getAll()) {
            result.add(user.toDto());
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<UserDto> findById( @PathVariable String id ) {
        return ResponseEntity.ok(userService.findById(id).toDto());
    }


    @PostMapping
    public ResponseEntity<UserDto> create( @RequestBody UserDto userDto ) {
        return ResponseEntity.ok(userService.create(userDto.toEntity()).toDto());
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<UserDto> update( @RequestBody UserDto user, @PathVariable String id ) {
        return ResponseEntity.ok(userService.update(user.toEntity(), id).toDto());
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete(@PathVariable String id ) {
        userService.deleteById(id);
        return ResponseEntity.ok(true);
    }
}
