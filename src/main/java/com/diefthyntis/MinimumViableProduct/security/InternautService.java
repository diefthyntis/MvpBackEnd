package com.diefthyntis.MinimumViableProduct.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diefthyntis.MinimumViableProduct.model.Speaker;
import com.diefthyntis.MinimumViableProduct.repository.SpeakerRepository;




/*
 * In the code, we get full custom User object using UserRepository, 
 * then we build a UserDetails object using static build() method.
 */

@Service
public class InternautService implements UserDetailsService {
  @Autowired
  SpeakerRepository speakerRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    Speaker speaker = speakerRepository.findByLogin(login)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with login: " + login));

    /*
     * Internaut.build est un méthode statique donc il n'y a pas besoin d'instancier
     * la classe pour appeler la méthode
     * Dans l'environnement JAVA,tout ce qui est statique est écrit en italique 
     */
    return Internaut.build(speaker);
  }

}