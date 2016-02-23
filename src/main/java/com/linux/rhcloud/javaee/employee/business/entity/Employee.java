package com.linux.rhcloud.javaee.employee.business.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Entity
public class Employee {
    
    @Id
    @GeneratedValue
    @Column(name = "emp_no")
    private long id;
    
    @Column(name = "birth_date")
    private Timestamp date;
    
    @Column(name = "first_name")
    private String firstName;
    
    
}
