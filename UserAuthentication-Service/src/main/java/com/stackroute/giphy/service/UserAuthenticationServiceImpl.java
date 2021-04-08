package com.stackroute.giphy.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.giphy.exception.UserAlreadyExistsException;
import com.stackroute.giphy.exception.UserNotFoundException;
import com.stackroute.giphy.model.User;
import com.stackroute.giphy.repository.UserAutheticationRepository;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

	@Autowired
	private UserAutheticationRepository userAutheticationRepository;

	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {

		User currentUser = this.userAutheticationRepository.findByUserIdAndUserPassword(userId, password);
		if (currentUser != null) {
			return currentUser;
		} else {
			throw new UserNotFoundException("User is not found");
		}
	}

	public boolean saveUser(User user) throws UserAlreadyExistsException {
		User savedUser = null;
		boolean flag=false;
		Optional<User> userfound=userAutheticationRepository.findById(user.getUserId());
		if(userfound.isPresent())
		{
			flag=false;
			
			throw new UserAlreadyExistsException("User with userID " + user.getUserId() + " already exists");
		} else {
			savedUser = userAutheticationRepository.save(user);
			if (savedUser == null) {
				
				throw new UserAlreadyExistsException("User with userID " + user.getUserId() + " already exists");
			}
			flag=true;
			
		}
		return flag;	
	}
	
	public User updateUser(String userId,User user) throws UserNotFoundException {

		Optional<User> userexist=userAutheticationRepository.findById(userId);
		
		if(userexist.isPresent())
		{
			User userUpdate=userexist.get();
			//user.setUserAddedDate(new Date());	
			userUpdate.setUserId(user.getUserId());
			userUpdate.setUserName(user.getUserName());
			userUpdate.setUserPassword(user.getUserPassword());
			userUpdate.setPhone(user.getPhone());
			userAutheticationRepository.save(userUpdate);
	
		}
		else
			throw new UserNotFoundException("User not found");
		
	 
		return user;


	}
	public User getUserById(String userId) throws UserNotFoundException {

		Optional<User> userFound=userAutheticationRepository.findById(userId);
		
		if(userFound.isPresent())
		
		return userFound.get();
		else
		 throw new UserNotFoundException("User not found");

	}


}