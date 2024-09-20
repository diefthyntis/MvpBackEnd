package com.diefthyntis.MinimumViableProduct.repository;

import org.springframework.stereotype.Repository;

import com.diefthyntis.MinimumViableProduct.model.Speaker;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, Integer> {

	Optional<Speaker> findByLogin(String login);
	
	Boolean existsByLogin(String login);

	Boolean existsByPseudonym(String pseudonym);

	Boolean existsByEmailaddress(String emailaddress);

	Optional<Speaker> findByEmailaddress(String emailaddress);

	
	
		

}
