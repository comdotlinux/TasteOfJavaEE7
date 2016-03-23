package com.linux.rhcloud.javaee.movieinfo.business.film.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@NamedQueries(@NamedQuery(name = Film.FIND_ALL_FILMS,query =  "SELECT f from film f"))
public class Film implements Serializable {

    private static final String PREFIX = "movieinfo.business.entity.film.";
    
    /** Named Query :: {@value #FIND_ALL_FILMS}  to get all films */
    public static final String FIND_ALL_FILMS = PREFIX + "findAll";
    
    
    @Id
    @GeneratedValue
    @Column(name = "film_id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    @Temporal(TemporalType.DATE)
    private Date releaseYear;

    @Column(name = "language_id")
    private long languageId;

    @Column(name = "original_language_id")
    private Long originalLanguageId;

    @Column(name = "rental_duration")
    private int rentalDuration;

    @Column(name = "rental_rate")
    private BigDecimal rentalRate;

    @Column(name = "length")
    private int lengthOfFilm;

    @Column(name = "replacement_cost")
    private BigDecimal replacementConst;

    @Column(name = "rating")
    private String ratingDb;
    
    private transient Rating rating; // use this

    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

     public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Date releaseYear) {
        this.releaseYear = releaseYear;
    }

    public long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(long languageId) {
        this.languageId = languageId;
    }

    public Long getOriginalLanguageId() {
        return originalLanguageId;
    }

    public void setOriginalLanguageId(Long originalLanguageId) {
        this.originalLanguageId = originalLanguageId;
    }

    public int getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    public int getLengthOfFilm() {
        return lengthOfFilm;
    }

    public void setLengthOfFilm(int lengthOfFilm) {
        this.lengthOfFilm = lengthOfFilm;
    }

    public BigDecimal getReplacementConst() {
        return replacementConst;
    }

    public void setReplacementConst(BigDecimal replacementConst) {
        this.replacementConst = replacementConst;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
//    public String getRatingDb() {
//        return ratingDb;
//    }
//
//    public void setRatingDb(String ratingDb) {
//        this.ratingDb = ratingDb;
//    }

    @PrePersist
    public void updateDbFields(){
        ratingDb = rating.getRatingCode();
    }
    
    @PostLoad
    public void updateEntityValues(){
        rating = Rating.value(ratingDb);
    }
}
