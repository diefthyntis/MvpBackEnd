package com.diefthyntis.MinimumViableProduct.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "speaker",
uniqueConstraints = {
       @UniqueConstraint(columnNames = "login")
})

/* @Data annotation de LOMBOK 
 * qui permet de ne pas déclarer les getters et setters qui sont générés à la compilation
 * 
 */
@Data
public class Speaker {
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
    @Column(unique = true,name = "login")
    private String login;
    
    
    
    @Size(max = 50)
    @Column(unique = true,name = "pseudonym")
    private String pseudonym;
    
    @Size(max = 50)
    @Column(name = "firstname")
    private String firstname;
    
    @Size(max = 100)
    @Column(name = "lastname")
    private String lastname;
    
    
    @NotBlank
    @Size(max = 100)
    @Column(unique = true,name = "emailaddress")
    private String emailaddress;
    
    
    
    @NotBlank
    @Size(max = 20)
    private String password;
    
    @Column(name = "creationdate")
    private java.time.LocalDateTime creationdate;
    
    @Column(name = "modificationdate")
    private java.time.LocalDateTime modificationdate;
    
    public Speaker(){
    	modificationdate=LocalDateTime.now();
    	creationdate = LocalDateTime.now();
    }
    
  
    
    
}
