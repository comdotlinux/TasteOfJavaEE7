package com.linux.rhcloud.javaee.movieinfo.business.film.entity;

/**
 *
 * @author guru
 */
public enum Rating {
    G("G"),
    PG("PG"),
    PG13("PG-13"),
    R("R"),
    NC17("NC-17");
    
    private final String ratingCode;
    
    private Rating(String ratingCode){
        this.ratingCode = ratingCode;
    }
    
    public String getRatingCode(){
        return ratingCode;
    }
    
}
