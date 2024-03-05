package org.jsp.ecommerceapp.service;

import java.util.Optional;

import org.jsp.ecommerceapp.dao.UserDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.exceptions.IdNotFoundException;
import org.jsp.ecommerceapp.exceptions.InvalidCredentialException;
import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User user){
		ResponseStructure<User> rs=new ResponseStructure<>();
		rs.setModel(userDao.saveUser(user));
		rs.setMessage("User Saved");
		rs.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.CREATED);
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
	
	public ResponseEntity<ResponseStructure<Boolean>> deleteMerchant(int id){
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
	
	
	public ResponseEntity<ResponseStructure<User>> verifyUser(String email,String password){
		Optional<User> u=userDao.verify(email, password);
		ResponseStructure<User> rs=new ResponseStructure<>();
		if(u.isPresent()) {
			rs.setModel(u.get());
			rs.setMessage("User verified");
			rs.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.OK);
		}
		throw new InvalidCredentialException("Invalid email or password");
	}

}
