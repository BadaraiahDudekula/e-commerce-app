package org.jsp.ecommerceapp.service;

import java.util.Optional;

import org.jsp.ecommerceapp.dao.MerchantDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.exceptions.IdNotFoundException;
import org.jsp.ecommerceapp.exceptions.InvalidCredentialException;
import org.jsp.ecommerceapp.exceptions.MerchantNotFoundException;
import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.util.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private EcommerceAppEmailSevice emailService;

	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(Merchant merchant, HttpServletRequest request) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		merchant.setStatus(AccountStatus.IN_ACTIVE.toString());
		merchant.setToken(RandomString.make(45));
		structure.setModel(merchantDao.saveMerchant(merchant));
		String message = emailService.sendWelcomeMail(merchant, request);
		structure.setMessage("Merchant saved" + "," + message);
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<>(structure, HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(Merchant merchant){
		Optional<Merchant> m=merchantDao.findById(merchant.getId());
		ResponseStructure<Merchant> rs=new ResponseStructure<>();
		if(m.isPresent()) {
			rs.setModel(merchantDao.saveMerchant(merchant));
			rs.setMessage("Merchant Updated");
			rs.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(rs,HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Boolean>> deleteMerchant(int id){
		Optional<Merchant> m=merchantDao.findById(id);
		ResponseStructure<Boolean> rs=new ResponseStructure<>();
		if(m.isPresent()) {
			rs.setModel(merchantDao.delete(id));
			rs.setMessage("Merchant Deleted");
			rs.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Boolean>>(rs, HttpStatus.OK);
			
		}
		throw new IdNotFoundException();
	}
	
	
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(String email, String password) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.verify(email, password);
		if (recMerchant.isPresent()) {
			Merchant merchant = recMerchant.get();
			if (merchant.getStatus().equals(AccountStatus.IN_ACTIVE.toString())) {
				throw new IllegalStateException("Account is Not Activated");
			}
			structure.setModel(merchant);
			structure.setMessage("Merchant Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new MerchantNotFoundException("Invalid Email Id or password");
	}

	public ResponseEntity<ResponseStructure<String>> activate(String token) {
		Optional<Merchant> recMerchant = merchantDao.findByToken(token);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			Merchant merchant = recMerchant.get();
			merchant.setStatus(AccountStatus.ACTIVE.toString());
			merchant.setToken(null);
			merchantDao.saveMerchant(merchant);
			structure.setModel("Merchant Found");
			structure.setMessage("Account Verified and Activated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.ACCEPTED);

		}
		throw new MerchantNotFoundException("Invalid URL");
	}
	


}
