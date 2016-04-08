package com.linux.rhcloud.javaee.movieinfo.crosscutting.entityvalidation;

/**
 * The Interface to validate cross field validation or Complex validations.
 * This is supposed to be shared by all entities and used for constraint validations.
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
public interface CrossFieldEntityValidator {
    boolean isValid();
}
