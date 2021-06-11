package com.trymicroservice.mymicroservicetry.rest.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class UserListDto implements Serializable {
    private ArrayList<UserDTO> userList;

    public UserListDto() {
        userList = new ArrayList<>();
    }
}
