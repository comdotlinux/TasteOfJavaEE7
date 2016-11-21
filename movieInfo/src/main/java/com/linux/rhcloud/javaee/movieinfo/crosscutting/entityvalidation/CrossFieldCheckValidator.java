/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linux.rhcloud.javaee.movieinfo.crosscutting.entityvalidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
public class CrossFieldCheckValidator implements ConstraintValidator<CrossFieldCheck, CrossFieldEntityValidator> {
    
    @Override
    public void initialize(CrossFieldCheck crossFieldCheck) {
        // Nothing yet
    }
    
    @Override
    public boolean isValid(CrossFieldEntityValidator entityValidator, ConstraintValidatorContext context) {
        return entityValidator.isValid();
    }
}
