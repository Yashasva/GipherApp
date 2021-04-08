package com.stackroute.giphy.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class User {
	@Id
    private String userId;
	
	private List<FavoriteGIF> favgif;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<FavoriteGIF> getFavgif() {
		return favgif;
	}
	public void setFavgif(List<FavoriteGIF> favgif) {
		this.favgif = favgif;
	}
}
