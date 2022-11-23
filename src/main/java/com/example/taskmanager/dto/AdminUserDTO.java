package com.example.taskmanager.dto;

import com.example.taskmanager.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDTO {
    private Long id;
    private String username;
    public String name;

    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setName(name);
        return user;
    }

    public static AdminUserDTO fromUser(User user) {
        AdminUserDTO adminUserDTO = new AdminUserDTO();
        adminUserDTO.setId(user.getId());
        adminUserDTO.setUsername(user.getUsername());
        adminUserDTO.setName(user.getName());
        return adminUserDTO;
    }
}
