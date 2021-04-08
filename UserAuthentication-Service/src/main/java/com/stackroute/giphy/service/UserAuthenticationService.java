package com.stackroute.giphy.service;

import com.stackroute.giphy.exception.UserAlreadyExistsException;
import com.stackroute.giphy.exception.UserNotFoundException;
import com.stackroute.giphy.model.User;

public interface UserAuthenticationService {

    public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;

    boolean saveUser(User user) throws UserAlreadyExistsException;
    User updateUser(String userId,User user) throws UserNotFoundException;
    User getUserById(String userId) throws UserNotFoundException;

}
