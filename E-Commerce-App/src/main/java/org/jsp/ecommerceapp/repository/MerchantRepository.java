package org.jsp.ecommerceapp.repository;

import java.util.Optional;

import org.jsp.ecommerceapp.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
	
	@Query("select m from Merchant m where m.email=?1 and m.password=?2")
	Optional<Merchant> verify(String email, String password);

	Optional<Merchant> findByToken(String token);


}
