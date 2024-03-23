package org.jsp.ecommerceapp.controller;

import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.model.User;
import org.jsp.ecommerceapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user, HttpServletRequest request) {
		return userService.saveUser(user, request)	;
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<User>> updateuser(@RequestBody User user){
		return userService.updateuser(user);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Boolean>> deleteUser(@PathVariable int id){
		return userService.deleteUser(id);
	}
	@PostMapping("/verify")
	public ResponseEntity<ResponseStructure<User>> verifyUser(@RequestParam String email,@RequestParam String password){
		return userService.verifyUser(email, password);
	}
	@PostMapping("/verify-by-phone-password")
	public ResponseEntity<ResponseStructure<User>> findByPhoneAndPassword(@RequestParam long phone,@RequestParam String password) {
		return userService.findByPhoneAndPassword(phone, password);
	}
	@GetMapping("/activate")
	public ResponseEntity<ResponseStructure<String>> activate(@RequestParam String token) {
		return userService.activate(token);

	}
}
