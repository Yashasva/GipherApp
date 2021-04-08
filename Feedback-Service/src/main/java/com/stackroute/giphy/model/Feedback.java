package com.stackroute.giphy.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class Feedback {
		@Id
		private String userId; 
		private String suggestion;
		private String username;
	    private String feedback;
	    private int rating;    
		
	    public String getSuggestion() {
			return suggestion;
		}
		public void setSuggestion(String suggestion) {
			this.suggestion = suggestion;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getFeedback() {
			return feedback;
		}
		public void setFeedback(String feedback) {
			this.feedback = feedback;
		}
		public int getRating() {
			return rating;
		}
		public void setRating(int rating) {
			this.rating = rating;
		}     
}