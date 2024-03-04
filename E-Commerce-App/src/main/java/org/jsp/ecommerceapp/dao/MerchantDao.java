package org.jsp.ecommerceapp.dao;

import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantDao {
	@Autowired
	private MerchantRepository merchantRepository;
	
	public Merchant saveMerchant(Merchant merchant) {
		return merchantRepository.save(merchant);
	}

}
