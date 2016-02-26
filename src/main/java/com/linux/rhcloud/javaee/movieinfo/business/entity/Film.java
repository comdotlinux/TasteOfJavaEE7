/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linux.rhcloud.javaee.movieinfo.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Entity(name = "film")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Film implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "film_id")
    private long id;
    
@Column(name = "title")
private String title;


@Column(name = "description")
private String description;

@Column(name = "release_year")
private String releaseYear;

@Column(name = "language_id")
private String languageId;

@Column(name = "original_language_id")
private String originalLanguageId;

@Column(name = "rental_duration")
private String rentalDuration;

@Column(name = "rental_rate")
private BigDecimal rentalRate;

@Column(name = "length")
private int lengthOfFilm;

@Column(name = "replacement_cost")
private BigDecimal replacementConst;

@Column(name = "rating")
private String rating;

@Column(name = "special_features")
private String specialFeatures;

@Column(name = "last_update")
private Date lastUpdate;
    
}
