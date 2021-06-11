package com.trymicroservice.mymicroservicetry.rest.dto;

import com.trymicroservice.mymicroservicetry.rest.entities.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    private Long id;
    private String username;

    private String password;

    private String name;

    private String email;

    public UserDTO(User user) {
        if (user != null) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.password=user.getPassword();
            this.name = user.getName();
            this.email=user.getEmail();

        }
    }
}
