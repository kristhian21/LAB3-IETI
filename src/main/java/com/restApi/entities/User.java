package com.restApi.entities;

import com.restApi.dto.UserDto;
import com.restApi.enums.RoleEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
public class User {

    @Id
    private String id;
    private String name;
    private String passwordHash;
    private List<RoleEnum> roles;
    @Indexed( unique = true )
    private String email;
    private String lastName;
    private Date createdAt;

    public User() {
    }

    public User(UserDto userDto){
        this.id = userDto.getId();
        this.name = userDto.getName();
        this.passwordHash = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
        this.email = userDto.getEmail();
        this.lastName = userDto.getLastName();
        this.roles = new ArrayList<>();
        this.roles.add(RoleEnum.USER);
        try {
            this.createdAt = new SimpleDateFormat("dd/MM/yyyy").parse(userDto.getCreatedAt());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public User(String id, String name, String email, String lastName, Date createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lastName = lastName;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEnum> roles) {
        this.roles = roles;
    }

    public UserDto toDto(){
        return new UserDto(id, name, passwordHash, email, lastName, createdAt.toString());
    }
    public void toEntity(UserDto userDto) {
        this.name = userDto.getName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        System.out.println("Password: "+ userDto.getPassword());
        if (userDto.getPassword() != null) {
            this.passwordHash = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
        }
    }
}
