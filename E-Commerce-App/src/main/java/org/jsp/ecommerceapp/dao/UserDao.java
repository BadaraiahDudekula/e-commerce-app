package org.jsp.ecommerceapp.dao;

import java.util.Optional;

import org.jsp.ecommerceapp.model.User;
import org.jsp.ecommerceapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDao {
	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}

	public Boolean delete(int id) {
		Optional<User> u=userRepository.findById(id);
		if(u.isPresent()) {
			userRepository.delete(u.get());
			return true;
		}

		return false;
	}
	
	public Optional<User> verify(String email,String password) {
		return userRepository.verify(email,password);
	}

	public Optional<User> findByToken(String token) {
		
		return userRepository.findByToken(token);
	}

	public Optional<User> findByPhoneAndPassword(long phone, String password) {
		
		return userRepository.findByPhoneAndPassword(phone,password);
	}

}
