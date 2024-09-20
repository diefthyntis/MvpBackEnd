package com.diefthyntis.MinimumViableProduct.security;




/*
 * WebSecurityConfig is the crux of our security implementation. 
 * It configures cors, csrf, session management, rules for protected resources. 
 * We can also extend and customize the default configuration that contains the elements below.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



/*
 – @EnableWebSecurity allows Spring to find and automatically apply the class to the global Web Security.

 - For Spring Boot 2: @EnableGlobalMethodSecurity provides AOP security on methods. It enables @PreAuthorize, @PostAuthorize, it also supports JSR-250. You can find more parameters in configuration in Method Security Expressions.

– @EnableGlobalMethodSecurity is deprecated in Spring Boot 3. You can use @EnableMethodSecurity instead. 
For more details, please visit Method Security.

– We override the configure(HttpSecurity http) method from WebSecurityConfigurerAdapter interface. 
It tells Spring Security how we configure CORS and CSRF, when we want to require all users to be authenticated or not, which filter (AuthTokenFilter) and when we want it to work (filter before UsernamePasswordAuthenticationFilter), which Exception Handler is chosen (AuthEntryPointJwt).

– Spring Security will load User details to perform authentication & authorization. 
So it has UserDetailsService interface that we need to implement.

 The implementation of UserDetailsService will be used for configuring DaoAuthenticationProvider by AuthenticationManagerBuilder.userDetailsService() method.

– We also need a PasswordEncoder for the DaoAuthenticationProvider. If we don’t specify, it will use plain text.
 */


/*
 En résumé, ce code configure Spring Security pour :

    Utiliser JWT pour l'authentification.
    Exclure certaines routes de la nécessité d'être authentifié.
    Gérer les exceptions d'authentification.
    Configurer un encodeur de mot de passe et un fournisseur d'authentification personnalisé.
    Désactiver la gestion des sessions côté serveur pour les requêtes REST stateless.
 */

@Configuration
@EnableMethodSecurity
// (securedEnabled = true,
// jsr250Enabled = true,
// prePostEnabled = true) // by default
public class Brain {

	/*
	 * userDetailsService : Injecte un service personnalisé (UserDetailsServiceImpl)
	 * qui est utilisé pour charger les détails de l'utilisateur lors de
	 * l'authentification.
	 */
	@Autowired
	InternautService internautService;

	/*
	 * unauthorizedHandler : Injecte un composant qui gère les exceptions
	 * d'authentification (comme les tentatives d'accès non autorisées).
	 */
	@Autowired
	private ClosedDoor closedDoor;

	/*
	 * authenticationJsonWebTokenFilter : Crée un filtre de token JWT personnalisé
	 * (AuthTokenFilter). Ce filtre est utilisé pour intercepter les requêtes HTTP
	 * et vérifier la présence et la validité des tokens JWT.
	 */
	@Bean
	public Watchdog authenticationJsonWebTokenFilter() {
		return new Watchdog();
	}

	/*
	 * authenticationProvider : Crée un fournisseur d'authentification
	 * (DaoAuthenticationProvider) qui utilise le userDetailsService pour charger
	 * les détails de l'utilisateur et le passwordEncoder pour encoder et vérifier
	 * les mots de passe.
	 */
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(internautService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	/*
	 * authenticationManager : Configure un gestionnaire d'authentification
	 * (AuthenticationManager) en utilisant la configuration d'authentification
	 * fournie par Spring Security.
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	/*
	 * passwordEncoder : Crée un encodeur de mot de passe (BCryptPasswordEncoder)
	 * qui est utilisé pour hacher les mots de passe des utilisateurs.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * csrf.disable() : Désactive la protection CSRF. Ceci est souvent fait pour les
	 * API REST, car les tokens JWT sont utilisés pour sécuriser les requêtes.
	 * 
	 * exceptionHandling().authenticationEntryPoint(unauthorizedHandler) : Configure
	 * un point d'entrée d'authentification personnalisé (unauthorizedHandler) pour
	 * gérer les erreurs d'authentification, comme les tentatives d'accès non
	 * autorisées.
	 * 
	 * sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) :
	 * Configure la gestion des sessions pour ne pas créer de sessions côté serveur.
	 * Cela convient aux API REST stateless où les tokens JWT sont utilisés pour
	 * maintenir l'état de l'utilisateur.
	 * 
	 * authorizeHttpRequests(auth -> ...) :
	 * 
	 * Permet l'accès à toutes les requêtes correspondant aux chemins /api/auth/**
	 * et /api/test/** sans authentification. Exige une authentification pour toutes
	 * les autres requêtes (anyRequest().authenticated()).
	 * 
	 * authenticationProvider(authenticationProvider()) : Intègre le fournisseur
	 * d'authentification personnalisé dans la configuration de Spring Security.
	 * 
	 * addFilterBefore(authenticationJsonWebTokenFilter(),
	 * UsernamePasswordAuthenticationFilter.class) : Ajoute le filtre JWT
	 * (AuthTokenFilter) avant le filtre d'authentification par nom d'utilisateur et
	 * mot de passe standard (UsernamePasswordAuthenticationFilter). Cela permet au
	 * filtre JWT de traiter les requêtes avant le traitement d'authentification
	 * standard.
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.exceptionHandling(exception -> exception.authenticationEntryPoint(closedDoor))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/**").permitAll()
						.anyRequest().authenticated());

		http.authenticationProvider(authenticationProvider());

		http.addFilterBefore(authenticationJsonWebTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}