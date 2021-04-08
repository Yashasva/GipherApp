package com.stackroute.giphy.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.stackroute.giphy.exception.FeedbackAlreadyExists;
import com.stackroute.giphy.exception.FeedbackNotFound;
import com.stackroute.giphy.model.Feedback;

import com.stackroute.giphy.repository.FeedbackRepository;


public class ServiceTests {


    @MockBean
    private Feedback feedback;
    
    @Mock
    private FeedbackRepository fbRepository;
   
    @InjectMocks
    private FeedbackServiceImpl fbServiceImpl;
    private List<Feedback> fbList = null;
    Optional<Feedback> options;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
         feedback = new  Feedback();
         feedback.setUserId("Jhon123");
		 feedback.setFeedback("title");
		 feedback.setSuggestion("imgurl");
		 feedback.setRating(9);
		 feedback.setUsername("username");
		 fbList=new ArrayList<>();
		 fbList.add(feedback);
		 options = Optional.of(feedback);

    }

	@Test
	public void addSuccess() throws FeedbackAlreadyExists {
		Feedback fb1 = null;
	   when(fbRepository.insert((Feedback)any())).thenReturn(feedback);
	   Feedback status = fbServiceImpl.saveFeedback(feedback);
	    Assert.assertEquals(fb1, status);
	   
	}


	  @Test
	    public void getFeedback() throws FeedbackNotFound{
	        when(fbRepository.findById("Jhon123")).thenReturn(options);
	        Feedback fb =fbServiceImpl.viewFeedback("Jhon123");
	        Assert.assertEquals(feedback, fb);
	    }
}
