package com.stackroute.giphy.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.giphy.exception.FeedbackAlreadyExists;
import com.stackroute.giphy.exception.FeedbackNotFound;
import com.stackroute.giphy.model.Feedback;
import com.stackroute.giphy.repository.FeedbackRepository;

@Service
public  class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Override
	public Feedback saveFeedback(Feedback feedback) throws FeedbackAlreadyExists{
		Optional<?> feedbackfound = this.feedbackRepository.findById(feedback.getUserId());
		if(feedbackfound.isPresent())
			throw new FeedbackAlreadyExists("Feedback already Exists");
		return this.feedbackRepository.save(feedback);
	}
	

	@Override
	public List<Feedback> getAllfeedback()
	{
		return feedbackRepository.findAll();
	}
	
	@Override
	public Feedback viewFeedback(String userId) throws FeedbackNotFound {
		Optional<?> feedbackfound = this.feedbackRepository.findById(userId);
		if(!feedbackfound.isPresent())
			throw new FeedbackNotFound("Feedback not found");
		Feedback feedback = (Feedback) feedbackfound.get();
		return feedback;
	}


	
}

