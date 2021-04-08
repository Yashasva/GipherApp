package com.stackroute.giphy.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.giphy.exception.GifAlreadyExistException;
import com.stackroute.giphy.exception.GifNotFoundException;
import com.stackroute.giphy.model.FavoriteGIF;
import com.stackroute.giphy.model.User;
import com.stackroute.giphy.repository.GifRepository;

@Service
public class GifServiceImpl implements GifService{
	
	@Autowired
	GifRepository gifRepo;
	
	@Override
	public User addFavGif(String userId, FavoriteGIF gif) throws GifAlreadyExistException  {
		
		Optional<User> user1=gifRepo.findById(userId);
		User user;
		List<FavoriteGIF> list;
		if(user1.isPresent())
			user=user1.get();
		else {
			user = new User();
			list = new ArrayList<>();
			user.setUserId(userId);
			user.setFavgif(list);
		}
		list = user.getFavgif();
		if(list != null && !list.isEmpty()) {
			for(FavoriteGIF gifIterator : list) {
					if(gif.getGifId().equals(gifIterator.getGifId()) )
						throw new GifAlreadyExistException("GIF already exists");
			}
		}
				list.add(gif);
				user.setFavgif(list);
				return this.gifRepo.save(user);
	}

	@Override
	 public boolean deleteFavGif(String userId, String Id) throws GifNotFoundException {
		boolean status = true;
		int flag=1;
        User gifuser = new User();
        List<FavoriteGIF> gifs = gifRepo.findById(userId).get().getFavgif();
        if (!gifs.isEmpty()) {
            Iterator iterator = gifs.listIterator();
            while (iterator.hasNext()) {
                FavoriteGIF gif = (FavoriteGIF) iterator.next();
                if(Id.equals(gif.getGifId()) ) {
                    iterator.remove();
                    flag=2;
                }
            }
            gifuser.setUserId(userId);
            gifuser.setFavgif(gifs);
            gifRepo.save(gifuser);
            if(flag==1)
            {	
            	throw new GifNotFoundException("GIF not found");
            }
         
        }
        	return status;
	}
	@Override
	public List<FavoriteGIF> viewFavGifs(String userId) {
		User favUser = gifRepo.findById(userId).orElse(null);
        if (favUser == null)
        	return null;
        else
            return favUser.getFavgif();
	
	}
}
