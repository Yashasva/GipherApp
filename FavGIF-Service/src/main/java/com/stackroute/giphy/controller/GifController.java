package com.stackroute.giphy.controller;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.stackroute.giphy.exception.GifAlreadyExistException;
import com.stackroute.giphy.exception.GifNotFoundException;
import com.stackroute.giphy.model.FavoriteGIF;
import com.stackroute.giphy.model.User;
import com.stackroute.giphy.service.GifService;
@RestController
public class GifController {
	@Autowired
	GifService gifService;
	@PostMapping("api/v1/add/{userId}")
	public ResponseEntity<?> addFav(@PathVariable("userId") String userId,@RequestBody FavoriteGIF favgif) throws GifAlreadyExistException {
		try
		{
			User user = gifService.addFavGif(userId,favgif);
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("CREATED", "GIF is added");
			return new ResponseEntity<HashMap>(map, HttpStatus.CREATED);
		}
		catch(GifAlreadyExistException e)
		{
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("CONFLICT", "GIF is Already added");
			return new ResponseEntity<HashMap>(map, HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("api/v1/{userId}/{id}")
	public ResponseEntity<?> deleteFav(@PathVariable("userId") String userId, @PathVariable("id") String id) throws GifNotFoundException{
		try
		{
			boolean status = gifService.deleteFavGif(userId,id);
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("OK", "GIF is deleted");
			return new ResponseEntity<HashMap>(map, HttpStatus.OK);
		}
		catch(GifNotFoundException e)
		{
		return new ResponseEntity<String>("NOT FOUND",HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("api/v1/{userId}")
	public ResponseEntity<?> viewFavGifs(@PathVariable String userId)  throws GifNotFoundException{

		List<FavoriteGIF> gifs = gifService.viewFavGifs(userId);
		if (gifs != null) {
			
			return new ResponseEntity<List<FavoriteGIF>>(gifs, HttpStatus.OK);
		} else {
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("NOT_FOUND", "GIF Not Found");
			return new ResponseEntity<HashMap>(map, HttpStatus.NOT_FOUND);
		}
	}
}