package com.linux.rhcloud.javaee.movieinfo.crosscutting.entityvalidation;

/**
 * <b>The Interface to validate cross field validation or Complex
 * validations.</b>
 * <br>This is supposed to be shared by all entities and used for constraint
 * validations.
 * <br>An Entity Class should typically;
 * <ol>
 * <li>Implement this interface</li>
 * <li>Add the cross field validations in the method
 * {@linkplain #isValid()}</li>
 * <li>Annotate the Entity with {@linkplain CrossFieldCheck}</li>
 * </ol>
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
public interface CrossFieldEntityValidator {

    boolean isValid();
}
