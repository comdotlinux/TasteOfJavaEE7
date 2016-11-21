package com.linux.rhcloud.javaee.movieinfo.business.customer.boundry;

import com.linux.rhcloud.javaee.movieinfo.business.customer.CustomerControl;
import com.linux.rhcloud.javaee.movieinfo.business.customer.entity.Customer;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Stateless
public class CustomerManager {
    
    @Inject
    CustomerControl control;
    
    public Customer getCustomer() {
        return control.getCustomerById(0);
    }
    
}
