package com.trymicroservice.mymicroservicetry.rest.services;

import com.trymicroservice.mymicroservicetry.rest.dto.request.MyUserDto;
import com.trymicroservice.mymicroservicetry.rest.entities.User;
import com.trymicroservice.mymicroservicetry.rest.exception.InvalidUserDataException;
import com.trymicroservice.mymicroservicetry.rest.exception.InvalidUserIdentifierException;
import com.trymicroservice.mymicroservicetry.rest.exception.UserNotFoundException;
import com.trymicroservice.mymicroservicetry.rest.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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

    public User getUserById(Long id) {
        if (id == null) {
            throw new InvalidUserIdentifierException("User Id cannot be null");
        }
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            return userOpt.get();
        }
        throw new UserNotFoundException(String.format("User not found for Id = %s", id));
    }

}
