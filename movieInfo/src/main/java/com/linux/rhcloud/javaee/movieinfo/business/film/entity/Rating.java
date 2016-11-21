package com.linux.rhcloud.javaee.movieinfo.business.film.entity;

import org.apache.commons.lang3.StringUtils;

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

    @Override
    public String toString() {
        return ratingCode;
    }
    
    public static Rating value(String value){
        Rating rating = null;
        for (Rating r : Rating.values()) {
            if(StringUtils.equals(r.getRatingCode(), value)){
                rating = r;
                break;
            }
        }
        
        return rating;
    }
}
