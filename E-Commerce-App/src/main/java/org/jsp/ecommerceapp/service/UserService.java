package org.jsp.ecommerceapp.service;

import java.util.Optional;

import org.jsp.ecommerceapp.dao.UserDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.exceptions.IdNotFoundException;
import org.jsp.ecommerceapp.exceptions.InvalidCredentialException;
import org.jsp.ecommerceapp.exceptions.UserNotFoundException;
import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.model.User;
import org.jsp.ecommerceapp.util.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private EcommerceAppEmailSevice emailService;
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User user, HttpServletRequest request) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		user.setStatus(AccountStatus.IN_ACTIVE.toString());
		user.setToken(RandomString.make(45));
		structure.setModel(userDao.saveUser(user));
		String message = emailService.sendWelcomeMail(user, request);
		structure.setMessage("User saved" + "," + message);
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<>(structure, HttpStatus.CREATED);
	}


	public ResponseEntity<ResponseStructure<User>> updateuser(User user){
		Optional<User> u=userDao.findById(user.getId());
		ResponseStructure<User> rs=new ResponseStructure<>();
		if(u.isPresent()) {
			rs.setModel(userDao.saveUser(user));
			rs.setMessage("User Updated");
			rs.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Boolean>> deleteUser(int id){
		Optional<User> u=userDao.findById(id);
		ResponseStructure<Boolean> rs=new ResponseStructure<>();
		if(u.isPresent()) {
			rs.setModel(userDao.delete(id));
			rs.setMessage("User Deleted");
			rs.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Boolean>>(rs, HttpStatus.OK);
			
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<User>> findByPhoneAndPassword(long phone, String password) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.findByPhoneAndPassword(phone, password);
		if (recUser.isPresent()) {
			User user = recUser.get();
			if (user.getStatus().equals(AccountStatus.IN_ACTIVE.toString())) {
				throw new IllegalStateException("Account is Not Activated");
			}
			structure.setModel(recUser.get());
			structure.setMessage("User Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new UserNotFoundException("Invalid Phone Number or password");
	}

	public ResponseEntity<ResponseStructure<User>> verifyUser(String email, String password) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.verify(email, password);
		if (recUser.isPresent()) {
			User user = recUser.get();
			if (user.getStatus().equals(AccountStatus.IN_ACTIVE.toString())) {
				throw new IllegalStateException("Account is Not Activated");
			}
			structure.setModel(recUser.get());
			structure.setMessage("User Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new UserNotFoundException("Invalid Phone Number or password");
	}

	public ResponseEntity<ResponseStructure<String>> activate(String token) {
		Optional<User> recUser = userDao.findByToken(token);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			User user = recUser.get();
			user.setStatus(AccountStatus.ACTIVE.toString());
			user.setToken(null);
			userDao.saveUser(user);
			structure.setModel("User Found");
			structure.setMessage("Account Verified and Activated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.ACCEPTED);

		}
		throw new UserNotFoundException("Invalid URL");
	}
	
	
	
	

}
