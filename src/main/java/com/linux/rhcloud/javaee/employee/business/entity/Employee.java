package com.linux.rhcloud.javaee.employee.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Employee entity object
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "emp_no")
    private long id;

    @Column(name = "birth_date")
    private Timestamp birthdate;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "hire_date")
    private Timestamp doj;

    /**
     *
     * @param birthdate
     * @param firstname
     * @param lastname
     * @param gender
     * @param doj
     */
    public Employee(Timestamp birthdate, String firstname, String lastname, Gender gender, Timestamp doj) {
        this.birthdate = (null != birthdate) ? new Timestamp(birthdate.getTime()) : null;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.doj = (null != doj) ? new Timestamp(doj.getTime()) : null;
    }

    /**
     * Default constructor
     */
    public Employee() {
        // Default constructor
    }

    /**
     * Getter for Id
     *
     * @return the id of this instance of {@linkplain Employee}
     */
    public long getId() {
        return id;
    }

    /**
     * Setter for ID
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter for birth date
     *
     * @return of this instance of {@linkplain Employee}
     */
    public Timestamp getBirthdate() {
        return (null != birthdate) ? new Timestamp(birthdate.getTime()) : null;
    }

    /**
     * Setter for Birth date
     *
     * @param birthdate set the birth date for this instance of
     * {@linkplain Employee}
     */
    public void setBirthdate(Timestamp birthdate) {
        this.birthdate = (null != birthdate) ? new Timestamp(birthdate.getTime()) : null;
    }

    /**
     * Getter for first name
     *
     * @return of this instance of {@linkplain Employee}
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Setter for First name
     *
     * @param firstname set the first name for this instance of
     * {@linkplain Employee}
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Getter for last name
     *
     * @return of this instance of {@linkplain Employee}
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Setter for Last Name
     *
     * @param lastname set the lastname for this instance of
     * {@linkplain Employee}
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Getter for Gender
     *
     * @return of this instance of {@linkplain Employee}
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Setter for Gender
     *
     * @param gender set the gender for this instance of {@linkplain Employee}
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Getter for Date of Joining
     *
     * @return of this instance of {@linkplain Employee}
     */
    public Timestamp getDoj() {
        return (null != doj) ? new Timestamp(doj.getTime()) : null;
    }

    /**
     * Setter for Date Of Joining
     *
     * @param doj set the date of joining of this instance of
     * {@linkplain Employee}
     */
    public void setDoj(Timestamp doj) {
        this.doj = (null != doj) ? new Timestamp(doj.getTime()) : null;
    }

    /**
     * Get the Json representation of this Employee
     * @return instance of {@linkplain JsonObject} with this instance data.
     */
    public JsonObject getJsonObject(){
        
        JsonObjectBuilder employeeBuilder = Json.createObjectBuilder()
                .add("id", id)
                .add("firstname", firstname)
                .add("lastname",lastname)
                .add("birthdate", new SimpleDateFormat("MM/dd/yyyy").format(birthdate))
                .add("gender", gender.getFullform())
                .add("dateOfJoining", new SimpleDateFormat("MM/dd/yyyy").format(doj));
        
        return employeeBuilder.build();
    }
}
