package com.stackroute.giphy.repository;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.giphy.model.Feedback;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest

public class RepositoryTests {
	@Autowired
	 private FeedbackRespository fbRepository;
	
	private Feedback feedback;
	 private List<Feedback> fbList = null;
	
	 @Before
	    public void setUp() {
		 
		 feedback= new  Feedback();
		 feedback.setUserId("Jhon123");
		 feedback.setFeedback("title");
		 feedback.setSuggestion("imgurl");
		 feedback.setRating(9);
		 feedback.setUsername("username");
		 fbList=new ArrayList<>();
		 fbList.add(feedback);
		 
	 }
	  @After
	    public void tearDown() throws Exception {

	        fbRepository.deleteAll();
	    }
	  @Test
	    public void gifDatasuccessTest() {
	        fbRepository.insert(feedback);
	        Feedback fb = fbRepository.findById("Jhon123").get();
	        Assert.assertEquals(feedback.getUserId(), fb.getUserId());
	    }
	  @Test
	    public void getAllFeedback() {

	       fbRepository.insert(feedback);
	        List<Feedback> allfeedback = fbRepository.findAll();
	        Assert.assertEquals(1, allfeedback.size());
	    }
}



