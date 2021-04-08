package com.stackroute.giphy.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.giphy.exception.FeedbackAlreadyExists;
import com.stackroute.giphy.exception.FeedbackNotFound;
import com.stackroute.giphy.model.Feedback;
import com.stackroute.giphy.service.FeedbackService;

/*
 * As in this assignment, we are working on creating RESTful web service, hence annotate
 * the class with @RestController annotation. A class annotated with the @Controller annotation
 * has handler methods which return a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */

@CrossOrigin
@RestController
public class FeedbackController {
	@Autowired
private FeedbackService feedbackService;

public FeedbackController(FeedbackService feedbackService) {
		this.feedbackService = feedbackService;
	}
	@PostMapping( "/api/v1/add")
	public ResponseEntity<?> saveHandler(@RequestBody Feedback feedback) throws FeedbackAlreadyExists{
		try {
			Feedback feedbacksaved = this.feedbackService.saveFeedback(feedback);
			return new ResponseEntity<Feedback>(feedbacksaved, HttpStatus.CREATED);
		}
		catch(FeedbackAlreadyExists e){
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("error","Feedback already exist");
			return new ResponseEntity<HashMap>(map, HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/api/v1/{userId}")
	public ResponseEntity<?> viewHandler(@PathVariable("userId") String userId) throws FeedbackNotFound{
		HashMap<String, String> map = new HashMap<String,String>();
		try {
			Feedback feedback = this.feedbackService.viewFeedback(userId);
			return new ResponseEntity<Feedback>(feedback, HttpStatus.OK);
		}
		catch(FeedbackNotFound e){
			map.put("error","Feedback does not exist");
			return new ResponseEntity<HashMap>(map, HttpStatus.CONFLICT);	
		}
		
	}
	
	
	@GetMapping("/api/v1")
	public ResponseEntity<?> getAllfeedbacks()
	{
	  List<Feedback> feedbacklist=feedbackService.getAllfeedback();
	  HashMap<String,String> map = new HashMap<String, String>();
	  if(feedbacklist == null || feedbacklist.isEmpty()) {
		  map.put("error", "List Empty");
		  return new ResponseEntity<HashMap>(map, HttpStatus.NOT_FOUND);
	  }
	  return new ResponseEntity<List>(feedbacklist,HttpStatus.OK);
	}
	
}