/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linux.rhcloud.javaee.movieinfo.business.entity;

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
    
    private String ratingCode;
    
    private Rating(String ratingCode){
        this.ratingCode = this.ratingCode;
    }
    
    public String getRatingCode(){
        return ratingCode;
    }
    
}
