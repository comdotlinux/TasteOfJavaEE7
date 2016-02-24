package com.linux.rhcloud.javaee.employee.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "hire_date")
    @Temporal(TemporalType.DATE)
    private Date doj;

    /**
     *
     * @param birthdate
     * @param firstname
     * @param lastname
     * @param gender
     * @param doj
     */
    public Employee(Date birthdate, String firstname, String lastname, Gender gender, Date doj) {
        this.birthdate = (null != birthdate) ? (Date) birthdate.clone() : null;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.doj = (null != doj) ? (Date) doj.clone() : null;
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
    public Date getBirthdate() {
        return (null != birthdate) ? (Date) birthdate.clone() : null;
    }

    /**
     * Setter for Birth date
     *
     * @param birthdate set the birth date for this instance of
     * {@linkplain Employee}
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = (null != birthdate) ? (Date) birthdate.clone() : null;
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
     * @param lastname set the last name for this instance of
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
    public Date getDoj() {
        return (null != doj) ? (Date) doj.clone() : null;
    }

    /**
     * Setter for Date Of Joining
     *
     * @param doj set the date of joining of this instance of
     * {@linkplain Employee}
     */
    public void setDoj(Date doj) {
        this.doj = (null != doj) ? (Date) doj.clone() : null;
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
