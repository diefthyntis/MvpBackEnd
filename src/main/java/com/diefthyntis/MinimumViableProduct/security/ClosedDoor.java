package com.diefthyntis.MinimumViableProduct.security;

/*
 Pour gérer les exceptions d'entrée/sortie et de servlet.
 */
import java.io.IOException;
import jakarta.servlet.ServletException;

/*
 Pour manipuler les requêtes et réponses HTTP.
 */
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 Pour la journalisation des messages d'erreur.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Pour gérer les exceptions d'authentification.
 */
import org.springframework.security.core.AuthenticationException;

/*
 Interface que la classe AuthEntryPointJwt implémente.
 */
import org.springframework.security.web.AuthenticationEntryPoint;

/*
  Indique que cette classe est un composant Spring.
 */
import org.springframework.stereotype.Component;


/*
 ================================================================================
 La classe AuthEntryPointJwt est un point d'entrée d'authentification personnalisé 
 qui gère les erreurs d'authentification en :

    Enregistrant un message d'erreur avec les détails de l'exception d'authentification.
    Envoyant une réponse HTTP 401 (Non autorisé) au client avec un message d'erreur.

Ce mécanisme est utile pour intercepter les tentatives d'accès non autorisées 
et fournir des réponses appropriées aux clients, tout en permettant de journaliser 
ces événements pour une analyse ultérieure.
================================================================================
 */

/*
 @Component : Cette annotation indique que AuthEntryPointJwt est un composant Spring géré 
 par le conteneur Spring. Cela permet à cette classe d'être automatiquement détectée 
 et enregistrée comme un bean dans le contexte Spring.
 */
@Component
public class ClosedDoor implements AuthenticationEntryPoint {

  private static final Logger logger = LoggerFactory.getLogger(ClosedDoor.class);

  /*
   Cette méthode est invoquée chaque fois qu'une exception d'authentification est levée. 
   Elle prend en paramètres la requête HTTP, la réponse HTTP, et l'exception d'authentification.
   */
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {
    logger.error("Unauthorized error: {}", authException.getMessage());
    
    
    /*
     Cette ligne envoie une réponse HTTP 401 (Non autorisé) avec un message d'erreur "Error: Unauthorized". 
     Cela signifie que la requête n'a pas pu être authentifiée et que l'accès à la ressource demandée est refusé.
     */
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
  }
}