package com.trymicroservice.mymicroservicetry.rest.services;

import com.trymicroservice.mymicroservicetry.rest.dto.request.MyUserDto;
import com.trymicroservice.mymicroservicetry.rest.entities.User;
import com.trymicroservice.mymicroservicetry.rest.exception.InvalidUserDataException;
import com.trymicroservice.mymicroservicetry.rest.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public User createUser(MyUserDto myUserDto) {
        if (myUserDto == null) {
            throw new InvalidUserDataException("User account data cannot be null");
        }


        // create the user
        User user = new User();
        user.setUsername(myUserDto.getUsername());
        user.setPassword(myUserDto.getPassword());
        user.setName(myUserDto.getName());
        user.setEmail(myUserDto.getEmail());


        User userCreated = userRepository.save(user);
        userCreated = userRepository.save(userCreated);

        log.info(String.format("User %s has been created.", userCreated.getId()));
        return userCreated;
    }
}
