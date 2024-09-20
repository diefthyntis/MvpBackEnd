package com.diefthyntis.MinimumViableProduct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diefthyntis.MinimumViableProduct.model.Speaker;
import com.diefthyntis.MinimumViableProduct.model.Subscription;



@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

	List<Subscription> findBySpeaker(Speaker speaker);
	
	
}
