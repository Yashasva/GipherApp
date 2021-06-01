package com.stackroute.giphy.controller;
import java.util.Date;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.stackroute.giphy.exception.UserAlreadyExistsException;
import com.stackroute.giphy.exception.UserNotFoundException;
import com.stackroute.giphy.model.User;
import com.stackroute.giphy.service.UserAuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@CrossOrigin
@RestController
public class UserAuthenticationController {
	
	@Autowired
	private UserAuthenticationService authicationService;
	public UserAuthenticationController(UserAuthenticationService authicationService) {
		this.authicationService = authicationService;
	}
	@PostMapping( "/api/v1/auth/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		try {
			System.out.println("This is working");
			this.authicationService.saveUser(user);
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("CREATED","User Added");
			
			return new ResponseEntity<HashMap>(map,HttpStatus.CREATED);
		}
		catch (UserAlreadyExistsException e) {
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("CONFLICT","User Already Exists");
			
			return new ResponseEntity<HashMap>(map,HttpStatus.CONFLICT);
		}
		
	}
	
	@PostMapping("/api/v1/auth/login")
	public ResponseEntity<?> getUser(@RequestBody User user) {
		User returnuser = null;
		try {
			returnuser = this.authicationService.findByUserIdAndPassword(user.getUserId(), user.getUserPassword());
			if (returnuser != null) {
				
				String token;
				try {
					token = this.getToken(user.getUserId(), user.getUserPassword());
					HashMap<String,String> map = new HashMap<String, String>();
					map.put("token", token);
					return new ResponseEntity<HashMap>(map, HttpStatus.OK);
				} catch (Exception e) {
					e.printStackTrace();
					HashMap<String,String> map = new HashMap<String, String>();
					map.put("UNAUTHORIZED", "Invalid UserID/Password");
					return new ResponseEntity<HashMap>(map, HttpStatus.UNAUTHORIZED);
				}
				
			} else {
				HashMap<String,String> map = new HashMap<String, String>();
				map.put("UNAUTHORIZED", "Invalid UserID/Password");
				return new ResponseEntity<HashMap>(map, HttpStatus.UNAUTHORIZED);

			}
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("UNAUTHORIZED", "Invalid UserID/Password");
			return new ResponseEntity<HashMap>(map, HttpStatus.UNAUTHORIZED);

		}
	}
	@PutMapping("/api/v1/auth/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable("userId") String userId,@RequestBody User user)
	{
		
		try {
			User userUpdate=authicationService.updateUser(user.getUserId(),user);
			return new ResponseEntity<User>(userUpdate,HttpStatus.OK);
			
		}
		
		catch (UserNotFoundException e) {
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("NOT_FOUND", "User Record not found");
			return new ResponseEntity<HashMap>(map, HttpStatus.NOT_FOUND);

//			 return new ResponseEntity<String>("User Record not found" , HttpStatus.NOT_FOUND);
				
			}
	}
	@GetMapping("/api/v1/auth/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") String userId)
	{
              try {
            	 User user=authicationService.getUserById(userId);
			
              return new ResponseEntity<User>(user,HttpStatus.OK);
  			
              } 
              
              catch (UserNotFoundException e) 
              {
            	  HashMap<String,String> map = new HashMap<String, String>();
      			map.put("NOT_FOUND", "User Record not found");
      			return new ResponseEntity<HashMap>(map, HttpStatus.NOT_FOUND);

//				return new ResponseEntity<String>("User Not Found",HttpStatus.NOT_FOUND); 
							
              }
              
	}
	
	// Generate JWT token
	public String getToken(String username, String password) throws Exception {
		String ibmkey = "SecretKeyToGenJWTs";
		long EXPIRATION_TIME = 864_000_000; // 10 days
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("UserId", username + "");
		claims.put("password", password);
		return Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, "ibmkey").compact();
	}
	
	
}

