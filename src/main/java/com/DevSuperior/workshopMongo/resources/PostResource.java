package com.DevSuperior.workshopMongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.DevSuperior.workshopMongo.domain.Post;
import com.DevSuperior.workshopMongo.dto.UserDTO;
import com.DevSuperior.workshopMongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource<T> {

	@Autowired
	private PostService service;

	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findByid(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);

	}
	
	
}
