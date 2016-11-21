/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhcloud.javaee.movieinfo.business.actor.entity;

import com.linux.rhcloud.javaee.movieinfo.business.actor.entity.Actor;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;


/**
 * Unit tests for {@linkplain Actor}
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
public class ActorValidationTest {

    private Actor underTest;
    
    @Before
    public void setUp() {
        underTest = new Actor();
    }
    

     @Test
     public void checkCrossFieldValidation_firstname_four_characters_Success() {
         // Arrange
         underTest.setFirstname("John");
         underTest.setLastname("Doe");
         
        //Act
        boolean actual = underTest.isValid();
        
        //Assert
        assertThat(actual,is(equalTo(true)));
     }
     
     @Test
     public void checkCrossFieldValidation_firstname_less_than_three_characters_Failure() {
         // Arrange
         underTest.setFirstname("Joh");
         underTest.setLastname("Doe");
         
        //Act
        boolean actual = underTest.isValid();
        
        //Assert
        assertThat(actual,is(equalTo(false)));
     }
     
      @Test
     public void checkCrossFieldValidation_firstname_more_than_four_characters_Success() {
         // Arrange
         underTest.setFirstname("Jonny");
         underTest.setLastname("Doe");
         
        //Act
        boolean actual = underTest.isValid();
        
        //Assert
        assertThat(actual,is(equalTo(true)));
     }

}
