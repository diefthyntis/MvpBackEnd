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
@Table(name = "comment")
@Data
public class Comment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@NotBlank
    @Size(max = 120)
    private String sentence;
	
	@ManyToOne
    @JoinColumn(name = "speakerid", referencedColumnName = "id")
    private Speaker speaker;
	
	@ManyToOne
    @JoinColumn(name = "articleid", referencedColumnName = "id")
    private Article article;
	
	
	 @Column(name = "creationdate")
	    private java.time.LocalDateTime creationdate;
	    
	    @Column(name = "modificationdate")
	    private java.time.LocalDateTime modificationdate;
	    
	    public Comment(){
	    	modificationdate=LocalDateTime.now();
	    	creationdate = LocalDateTime.now();
	    }
}
