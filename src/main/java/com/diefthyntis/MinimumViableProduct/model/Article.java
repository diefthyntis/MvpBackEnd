package com.diefthyntis.MinimumViableProduct.model;

import java.time.LocalDateTime;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "article")
@Data
public class Article {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@NotBlank
    @Size(max = 120)
    private String title;
	
	
	@NotBlank
    @Size(max = 5000)
    private String sentence;
	
	
	
	
	@ManyToOne
    @JoinColumn(name = "speakerid", referencedColumnName = "id")
    private Speaker speaker;
	
	@ManyToOne
    @JoinColumn(name = "topicid", referencedColumnName = "id")
    private Topic topic;
	
	
	 @Column(name = "creationdate")
	    private java.time.LocalDateTime creationdate;
	    
	    @Column(name = "modificationdate")
	    private java.time.LocalDateTime modificationdate;
	    
	    public Article(){
	    	modificationdate=LocalDateTime.now();
	    	creationdate = LocalDateTime.now();
	    }
}
