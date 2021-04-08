package com.stackroute.giphy.service;

import java.util.List;

import com.stackroute.giphy.exception.GifAlreadyExistException;
import com.stackroute.giphy.exception.GifNotFoundException;
import com.stackroute.giphy.model.FavoriteGIF;
import com.stackroute.giphy.model.User;

public interface GifService {
	
	
	public boolean deleteFavGif(String userId,String id) throws GifNotFoundException;
	public List<FavoriteGIF> viewFavGifs(String userId);
	public User addFavGif(String userId, FavoriteGIF gif) throws GifAlreadyExistException;

}
