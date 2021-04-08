package com.stackroute.giphy.controller;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.any;
import static org.mockito.ArgumentMatchers.any;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.giphy.exception.FeedbackAlreadyExists;
import com.stackroute.giphy.model.Feedback;

import com.stackroute.giphy.service.FeedbackService;


@RunWith(SpringRunner.class)
@WebMvcTest
public class ControllerTest {
	
	 @Autowired
	 private MockMvc mockMvc;
	 
	 @MockBean
	 Feedback feedback;
	 
	 @MockBean
	 FeedbackService fbservice;
	
	 @InjectMocks
	 FeedbackController fbcontroller;
	 
	 private List<Feedback> fblist;
	 
	 @Before
	 public void setup() {
		 MockitoAnnotations.initMocks(this);
		 mockMvc = MockMvcBuilders.standaloneSetup(fbcontroller).build();
		 
		 feedback = new Feedback();
		 feedback.setUserId("lalal");
		 feedback.setFeedback("feedback");
		 feedback.setRating(8);
		 feedback.setSuggestion("suggestion");
		 feedback.setUsername("username");
		 fblist = new ArrayList<Feedback>();
		 fblist.add(feedback);
	 }

	 @Test
	 public void addSuccess() throws Exception{
		 when(fbservice.saveFeedback(feedback)).thenReturn(feedback);
	        mockMvc.perform(post("/api/v1/add").contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(feedback)))
	                .andExpect(MockMvcResultMatchers.status().isCreated())
	                .andDo(MockMvcResultHandlers.print());
	 }
	 
	 @Test 
	  public void addFailure() throws Exception {
	        when(fbservice.saveFeedback(any())).thenThrow(FeedbackAlreadyExists.class);
	        mockMvc.perform(post("/api/v1/add")
	                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(feedback)))
	                .andExpect(status().isConflict())
	                .andDo(MockMvcResultHandlers.print());
	    }
	 
	 @Test
	    public void getAllSuccess() throws Exception {
	        when(fbservice.getAllfeedback()).thenReturn(fblist);
	        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andDo(MockMvcResultHandlers.print());
	    }
	 
	 @Test 
	  public void getAllFailure() throws Exception {
	        when(fbservice.getAllfeedback()).thenReturn(null);
	        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isNotFound())
	                .andDo(MockMvcResultHandlers.print());
	    }
	    
	
	 private static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
}
