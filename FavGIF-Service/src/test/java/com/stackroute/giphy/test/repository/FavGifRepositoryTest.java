package com.stackroute.giphy.test.repository;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.giphy.model.FavoriteGIF;
import com.stackroute.giphy.model.User;
import com.stackroute.giphy.repository.GifRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class FavGifRepositoryTest {

	@Autowired
	 private GifRepository gifRepository;
	
	 private FavoriteGIF favgif;
	 private List<FavoriteGIF> gifList = null;
	 private User user;
	
	 @Before
	    public void setUp() {
		 
		 favgif= new  FavoriteGIF();
		 favgif.setGifId("Jhon123");
		 favgif.setTitle("title");
		 favgif.setUrlToimage("imgurl");
		 gifList=new ArrayList<>();
		 gifList.add(favgif);
		 
		 user=new User();
		 user.setFavgif(gifList);
		 user.setUserId("Jhon123");
		 
	 }
	  @After
	    public void tearDown() throws Exception {

	        gifRepository.deleteAll();
	    }
	  @Test
	    public void gifDatasuccessTest() {
	        gifRepository.insert(user);
	        List<FavoriteGIF> allgifs = gifRepository.findById("Jhon123").get().getFavgif();
	        Assert.assertEquals(gifList.get(0).getGifId(), allgifs.get(0).getGifId());
	    }
	  @Test
	    public void getAllGifs() {

	       gifRepository.insert(user);
	        List<FavoriteGIF> allgifs = gifRepository.findById("Jhon123").get().getFavgif();
	        Assert.assertEquals(1, allgifs.size());
	    }
}













