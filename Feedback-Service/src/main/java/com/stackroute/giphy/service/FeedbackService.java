package com.stackroute.giphy.service;

import java.util.List;

import com.stackroute.giphy.exception.FeedbackAlreadyExists;
import com.stackroute.giphy.exception.FeedbackNotFound;
import com.stackroute.giphy.model.Feedback;

public interface FeedbackService {
	Feedback saveFeedback(Feedback feedback) throws FeedbackAlreadyExists ;
	Feedback viewFeedback(String userId) throws FeedbackNotFound;
	List<Feedback> getAllfeedback();
}
