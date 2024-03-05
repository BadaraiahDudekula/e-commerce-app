package org.jsp.ecommerceapp.service;

import org.jsp.ecommerceapp.dao.MerchantDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao merchantDao;
	 
	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(Merchant merchant){
		ResponseStructure<Merchant> rs=new ResponseStructure<>();
		rs.setModel(merchantDao.saveMerchant(merchant));
		rs.setMessage("Merchant Saved");
		rs.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(rs,HttpStatus.CREATED);
	}
}
