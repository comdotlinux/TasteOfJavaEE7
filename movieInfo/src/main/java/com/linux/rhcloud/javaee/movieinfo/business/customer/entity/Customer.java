package com.linux.rhcloud.javaee.movieinfo.business.customer.entity;

import com.linux.rhcloud.javaee.movieinfo.business.address.entity.Address;
import com.linux.rhcloud.javaee.movieinfo.crosscutting.entityvalidation.CrossFieldCheck;
import com.linux.rhcloud.javaee.movieinfo.crosscutting.entityvalidation.CrossFieldEntityValidator;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Entity
@CrossFieldCheck
public class Customer implements Serializable, CrossFieldEntityValidator {

    // Run on all Databases
    // ALTER TABLE `sakila`.`customer` ADD `version` integer NOT NULL DEFAULT 1;
    // ALTER TABLE `sakila`.`customer` CHANGE `version` `version` integer NOT NULL;
    private static final long serialVersionUID = 3591581933385747274L;
    
    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private Long id;
    
    @Column(name = "store_id")
    private Long storeId;

    @NotNull
    @Column(name = "first_name")
    @Size(min = 3, max = 45)
    private String firstname;
    
    @NotNull
    @Size(min = 3, max = 45)
    @Column(name = "last_name")
    private String lastname;
    
    @Size(min = 5, max = 50)
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
                        +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
                        +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
              message="{invalid.email}")
    private String email;

    @OneToOne
    private Address address;
    
    private Integer active;
    
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
            
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Version
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
    
    /**
     * Cross field validations.
     * @return true if firstname and lastname are more than 3 characters OR email is not empty
     */
    @Override
    public boolean isValid() {
        return (StringUtils.length(firstname) > 3 && StringUtils.length(lastname) > 3) || StringUtils.isNotBlank(email);
                
    }
}
