package com.DevSuperior.workshopMongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
