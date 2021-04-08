package com.stackroute.giphy.test.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.stackroute.giphy.exception.GifAlreadyExistException;
import com.stackroute.giphy.exception.GifNotFoundException;
import com.stackroute.giphy.model.FavoriteGIF;
import com.stackroute.giphy.model.User;
import com.stackroute.giphy.repository.GifRepository;
import com.stackroute.giphy.service.GifServiceImpl;

import java.util.*;
public class FavGifServiceTests {


    @MockBean
    private FavoriteGIF favgif;
    @MockBean
    private User gifUser;
    
    @Mock
    private GifRepository gifRepository;
   
    @InjectMocks
    private GifServiceImpl gifServiceImpl;
    private List<FavoriteGIF> gifList = null;
    Optional<User> options;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        favgif= new  FavoriteGIF();
		 favgif.setGifId("Jhon123");
		 favgif.setTitle("title");
		 favgif.setUrlToimage("imgurl");
		 gifList=new ArrayList<>();
		 gifList.add(favgif);
		 
		 
		 gifUser=new User();
		 gifUser.setFavgif(gifList);
		 gifUser.setUserId("Jhon123");
		  options = Optional.of(gifUser);

    }

	@Test
	public void addGifSuccess() throws GifAlreadyExistException {
		User user1 = null;
	   when(gifRepository.insert((User) any())).thenReturn(gifUser);
	   User status = gifServiceImpl.addFavGif("id", favgif);
	    Assert.assertEquals(user1, status);
	   
	}
	
	    @Test(expected =  NullPointerException.class)
	    public void deleteGifFailure() throws GifNotFoundException {
	        when(gifRepository.findById(gifUser.getUserId())).thenReturn(null);
	        when(gifRepository.save(gifUser)).thenReturn(gifUser);
	        boolean flag = gifServiceImpl.deleteFavGif("Jhon123", favgif.getGifId());
	        Assert.assertEquals(true, flag);
	    }
	    @Test
	    public void deleteGifSuccess() throws GifNotFoundException
	    {
	    	   when(gifRepository.findById(gifUser.getUserId())).thenReturn(options);
		        when(gifRepository.save(gifUser)).thenReturn(gifUser);
		        boolean flag = gifServiceImpl.deleteFavGif("Jhon123", favgif.getGifId());
		        Assert.assertEquals(true, flag);
	    }
	     	
	    
	  @Test
	    public void getAllGifsByUserId() throws GifNotFoundException {
	        when(gifRepository.findById("Jhon123")).thenReturn(options);
	        List<FavoriteGIF> gifs =gifServiceImpl.viewFavGifs("Jhon123");
	        Assert.assertEquals(gifList, gifs);
	    }
}
