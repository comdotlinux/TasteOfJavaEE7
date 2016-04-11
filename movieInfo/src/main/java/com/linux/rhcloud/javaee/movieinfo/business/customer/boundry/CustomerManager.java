package com.linux.rhcloud.javaee.movieinfo.business.customer.boundry;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Stateless
public class CustomerManager {
    
    @PersistenceContext
    EntityManager em;
    
    
    
}
