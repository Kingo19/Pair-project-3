package com.techelevator.services;

import org.springframework.stereotype.Component;

import com.techelevator.model.CatPic;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatPicService implements CatPicService {

	private final String catPictureUrl = "https://cat-data.netlify.app/api/pictures/random";
	private final RestTemplate restTemplate = new RestTemplate();

	@Override
	public CatPic getPic() {
		return restTemplate.getForObject(catPictureUrl, CatPic.class);
	}

}	
