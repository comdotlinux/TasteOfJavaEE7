package com.linux.rhcloud.javaee.movieinfo.crosscutting.entityvalidation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Documented
@Constraint(validatedBy = CrossFieldCheckValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CrossFieldCheck {

    String message() default "Cross Field Check Failed!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
