package com.stackroute.giphy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.giphy.model.Feedback;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, String> {

}
