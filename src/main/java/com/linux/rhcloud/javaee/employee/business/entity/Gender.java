package com.linux.rhcloud.javaee.employee.business.entity;

/**
 * The Gender options.
 * 
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
public enum Gender {
    M("Male"),
    F("Female");
   
    private final String fullform;
    
    private Gender(String fullform){
        this.fullform = fullform;
    }
    
    public String getFullform(){
        return fullform;
    }

    public String getName() {
        return this.name();
    }
    
    
    
}
