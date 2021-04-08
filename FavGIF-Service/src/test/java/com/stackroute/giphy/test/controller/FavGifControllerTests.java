package com.stackroute.giphy.test.controller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;


import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.giphy.controller.GifController;
import com.stackroute.giphy.exception.GifAlreadyExistException;
import com.stackroute.giphy.exception.GifNotFoundException;
import com.stackroute.giphy.model.FavoriteGIF;
import com.stackroute.giphy.model.User;
import com.stackroute.giphy.service.GifService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class FavGifControllerTests {
	 @Autowired
	 private MockMvc mockMvc;
	    
	 @MockBean
	 private FavoriteGIF favgif;
	 @MockBean
	 private User user;
	 @MockBean
	 private GifService gifservice;
	 
	 @InjectMocks
	 private GifController gifcontroller;
	 
	 private List<FavoriteGIF> favlist;
	  @Before
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(gifcontroller).build();
	       // user.setUserId(1);
	        favgif=new FavoriteGIF();
	        favgif.setGifId("gifid");
	        favgif.setTitle("giftitle");
	        favgif.setUrlToimage("happy.gif");
	      
	        favlist=new ArrayList<>();
	        favlist.add(favgif);
	  }	  
	  @Test
	  public void addFavGifSuccess() throws Exception {
	      
			when(gifservice.addFavGif("id", favgif)).thenReturn(user);
	        mockMvc.perform(post("/api/v1/add/1").contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(favgif)))
	                .andExpect(status().isCreated())
	                .andDo(MockMvcResultHandlers.print());

	  }
	  @Test 
	  public void addFavGifFailure() throws Exception {
	        when(gifservice.addFavGif(any(),any())).thenThrow(GifAlreadyExistException.class);
	        mockMvc.perform(post("/api/v1/add/1")
	                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(favgif)))
	                .andExpect(status().isConflict())
	                .andDo(MockMvcResultHandlers.print());
	    }
	  @Test
	    public void deleteGifSuccess() throws Exception {

	        when(gifservice.deleteFavGif("id", favgif.getGifId())).thenReturn(true);
	        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/id/gifid")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andDo(MockMvcResultHandlers.print());
	    }
	    @Test
	    public void deleteGifFailure() throws Exception {

	        when(gifservice.deleteFavGif("id", favgif.getGifId())).thenThrow(GifNotFoundException.class);
	        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/id/gifid")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isNotFound())
	                .andDo(MockMvcResultHandlers.print());
	    }
	    @Test
	    public void getAllgifsByUserIdSuccess() throws Exception {
	        when(gifservice.viewFavGifs("id")).thenReturn(favlist);
	        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/id")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andDo(MockMvcResultHandlers.print());
	    }
	    @Test
	    public void getAllgifsByUserIdFailure() throws Exception {

	        when(gifservice.viewFavGifs("id")).thenReturn(null);
	        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/id")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isNotFound())
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
