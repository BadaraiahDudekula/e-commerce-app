package org.jsp.ecommerceapp.dao;

import java.util.Optional;

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
	public Optional<Merchant> findById(int id) {
		return merchantRepository.findById(id);
	}

	public Boolean delete(int id) {
		Optional<Merchant> m=merchantRepository.findById(id);
		if(m.isPresent()) {
			merchantRepository.delete(m.get());
			return true;
		}

		return false;
	}
	
	public Optional<Merchant> verify(String email,String password) {
		return merchantRepository.verify(email,password);
	}
	
}
