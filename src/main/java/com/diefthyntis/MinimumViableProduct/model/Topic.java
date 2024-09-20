package com.diefthyntis.MinimumViableProduct.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/*
 * L'ordre des annotations n'a pas d'importance
 * L'interpréteur d'annotation les traites dans l'ordre qui vient sans importance
 */
@Data
@Entity
@Table(name="topic")
public class Topic {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer id;
	
    
    @Column(name = "description")
    private String description;
    
    @Column(name="title")
    private String title;
    
    
  }
