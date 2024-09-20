package com.diefthyntis.MinimumViableProduct.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.diefthyntis.MinimumViableProduct.exception.EmailaddressNotFoundException;
import com.diefthyntis.MinimumViableProduct.exception.SpeakerNotFoundException;
import com.diefthyntis.MinimumViableProduct.model.Speaker;
import com.diefthyntis.MinimumViableProduct.repository.SpeakerRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpeakerService {
	private final SpeakerRepository speakerRepository;
	
	
	public Boolean existsBylogin(final String login){
		return speakerRepository.existsByLogin(login);
		
	}
	
	public Speaker getSpeakerById(Integer id) {
		return speakerRepository.findById(id).orElseThrow(() -> new SpeakerNotFoundException("Speaker Not Found"));
	}

	public Speaker findBylogin(String login) {
		final Speaker oneSpeaker =speakerRepository.findByLogin(login).orElseThrow(() -> new SpeakerNotFoundException("Speaker Not Found"));
		return oneSpeaker;
	}
	
	public Speaker save(Speaker speaker) {
		speaker.setLogin(speaker.getEmailaddress());
		return speakerRepository.save(speaker);
	}

	public Boolean existsByPseudonym(final String login){
		return speakerRepository.existsByPseudonym(login);
		
	}

	public boolean existsByEmailaddress(String emailaddress) {
		return speakerRepository.existsByEmailaddress(emailaddress);
	}
	
	public Speaker findByEmailaddress(String emailaddress) {
		final Speaker oneSpeaker = speakerRepository.findByEmailaddress(emailaddress).orElseThrow(() -> new EmailaddressNotFoundException("Email address Not Found"));
		return oneSpeaker;
	}
}
