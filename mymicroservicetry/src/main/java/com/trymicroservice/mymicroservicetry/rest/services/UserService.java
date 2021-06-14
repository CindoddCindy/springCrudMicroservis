package com.trymicroservice.mymicroservicetry.rest.services;

import com.trymicroservice.mymicroservicetry.rest.dto.request.MyUserDto;
import com.trymicroservice.mymicroservicetry.rest.entities.User;
import com.trymicroservice.mymicroservicetry.rest.exception.*;
import com.trymicroservice.mymicroservicetry.rest.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public User getUserByUsername(String username) {
        if (username == null) {
            throw new InvalidUsernameException("username cannot be null");
        }
        return userRepository.findByUsername(username);
    }

    @Transactional
    public User updateUser(Long id, MyUserDto myUserDto) {
        if (id == null) {
            throw new InvalidUserIdentifierException("Id cannot be null");
        }
        if (myUserDto == null) {
            throw new InvalidUserDataException("User account data cannot be null");
        }

        Optional<User> userOpt = userRepository.findById(id);
        if (!userOpt.isPresent()) {
            throw new UserNotFoundException(String.format("The user with Id = %s doesn't exists", id));
        }
        User user = userOpt.get();

        // check if the username has not been registered
        User userByUsername = getUserByUsername(myUserDto.getUsername());
        if (userByUsername != null) {
            // check if the user's id is different than the actual user
            if (!user.getId().equals(userByUsername.getId())) {
                String msg = String.format("The username %s it's already in use from another user with ID = %s",
                        myUserDto.getUsername(), userByUsername.getId());
                log.error(msg);
                throw new InvalidUserDataException(msg);
            }
        }


        // update the user
        user.setUsername(myUserDto.getUsername());


        User userUpdated = userRepository.save(user);
        log.info(String.format("User %s has been updated.", user.getId()));

        return userUpdated;
    }


    /*

    @Transactional
    public void deleteUserById(Long id) {
        if (id == null) {
            throw new InvalidUserIdentifierException("Id cannot be null");
        }

        Optional<User> userOpt = userRepository.findById(id);
        if (!userOpt.isPresent()) {
            throw new UserNotFoundException(String.format("User not found with Id = %s", id));
        }

        // only not secured users can be deleted
        User user = userOpt.get();
        if (user.isSecured()) {
            throw new UserIsSecuredException(String.format("User %s is secured and cannot be deleted.", id));
        }

        userRepository.deleteById(id);
        log.info(String.format("User %s has been deleted.", id));
    }

     */

//cobs pske metod ini
    public ResponseEntity<Object> deleteRole(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            if (userRepository.findById(id).isPresent()) {
                return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
            } else return ResponseEntity.ok().body("Successfully deleted specified record");
        } else
        return ResponseEntity.unprocessableEntity().body("No Records Found");

    }



}
