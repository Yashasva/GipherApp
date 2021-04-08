package com.stackroute.giphy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.giphy.model.User;

@Repository
public interface GifRepository extends MongoRepository<User, String> {
	

}
