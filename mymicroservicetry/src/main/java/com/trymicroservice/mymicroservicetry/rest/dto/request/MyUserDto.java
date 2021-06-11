package com.trymicroservice.mymicroservicetry.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyUserDto implements Serializable {

    private String username;

    private String password;

    private String name;

    private String email;
}
