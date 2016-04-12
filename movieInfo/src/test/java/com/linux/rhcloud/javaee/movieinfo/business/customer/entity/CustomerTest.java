/*
 * 
 * Copyright (C) 2016 guru
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package com.linux.rhcloud.javaee.movieinfo.business.customer.entity;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
public class CustomerTest {

    private Customer ut;

    @Before
    public void setUp() {
        ut = new Customer();
    }

    @Test
    public void customerCrossFieldValidationTest_fn_ln_length_correct_Success() {
        boolean expected = true;
        ut.setFirstname("John");
        ut.setLastname("Dorian");

        boolean actual = ut.isValid();

        assertThat(ut.getEmail(), is(nullValue()));
        assertThat(actual, is(expected));
    }

    @Test
    public void customerCrossFieldValidationTest_fn_ln_length_incorrect_Failure() {
        boolean expected = false;
        ut.setFirstname("John");
        ut.setLastname("Doe");

        boolean actual = ut.isValid();

        assertThat(ut.getEmail(), is(nullValue()));
        assertThat(actual, is(expected));
    }

    @Test
    public void customerCrossFieldValidationTest_fn_ln_length_correct_email_input_Success() {
        boolean expected = true;
        ut.setFirstname("John");
        ut.setLastname("Dorian");
        ut.setEmail("john@doe.com");

        boolean actual = ut.isValid();

        assertThat(ut.getEmail(), is(notNullValue()));
        assertThat(actual, is(expected));
    }

    @Test
    public void customerCrossFieldValidationTest_fn_ln_length_incorrect_email_input_Success() {
        boolean expected = true;
        ut.setFirstname("Tod");
        ut.setLastname("Doe");
        ut.setEmail("tod@doe.com");

        boolean actual = ut.isValid();

        assertThat(ut.getEmail(), is(notNullValue()));
        assertThat(actual, is(expected));
    }
}
