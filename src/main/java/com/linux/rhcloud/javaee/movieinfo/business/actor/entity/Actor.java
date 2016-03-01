package com.linux.rhcloud.javaee.movieinfo.business.actor.entity;

import com.linux.rhcloud.javaee.movieinfo.crosscutting.entityvalidation.CrossFieldCheck;
import com.linux.rhcloud.javaee.movieinfo.crosscutting.entityvalidation.CrossFieldEntityValidator;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

/**
 * Actor Entity
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Entity(name = "actor")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries(@NamedQuery(name = Actor.FIND_ALL_ACTORS, query = "SELECT a FROM actor a"))
@CrossFieldCheck(message = "If Actor firstname is 3 characters then lastname must be greater than 3 characters.")
public class Actor implements Serializable, CrossFieldEntityValidator {

    private static final long serialVersionUID = -8357357239161127772L;
    
    private static final String PREFIX = "movieinfo.business.entity.actor.";
    
    /** Named Query :: {@value #FIND_ALL_ACTORS}  to get all actors */
    public static final String FIND_ALL_ACTORS = PREFIX + "findAll";

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "actor_id")
    private long id;
    
    @Column(name = "first_name")
    @Size(min = 3, max = 45)
    private String firstname;
    
    @Column(name = "last_name")
    @Size(min = 3, max = 45)
    private String lastname;
    
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;

    public Actor(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Actor() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE).append(id).append(firstname).append(lastname).append(lastUpdateDate).toString();
    }

    @Override
    public boolean isValid() {
        return StringUtils.length(firstname) >= 4 || StringUtils.length(lastname) > 3;
    }
    
}
