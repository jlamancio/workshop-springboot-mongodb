package com.DevSuperior.workshopMongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.DevSuperior.workshopMongo.domain.Post;
import com.DevSuperior.workshopMongo.domain.User;
import com.DevSuperior.workshopMongo.dto.UserDTO;
import com.DevSuperior.workshopMongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource<T> {

	@Autowired
	private UserService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findByid(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));

	}
	
	@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getPosts());

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO) {
		User obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDTO objDTO, @PathVariable String id) {
		User obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		
	}
}
