package com.stackroute.giphy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class FavoriteGIF {
	
		private String gifId;
		private String urlToImage;
		private String title;
		public String getGifId() {
			return gifId;
		}
		public void setGifId(String gifId) {
			this.gifId = gifId;
		}
		public String getUrlToImage() {
			return urlToImage;
		}
		public void setUrlToImage(String urlToImage) {
			this.urlToImage = urlToImage;
		}
		public String getUrlToimage() {
			return urlToImage;
		}
		public void setUrlToimage(String urlToimage) {
			this.urlToImage = urlToimage;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}	
}