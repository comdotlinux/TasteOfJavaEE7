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
package com.linux.rhcloud.javaee.movieinfo.business.customer;

import com.linux.rhcloud.javaee.movieinfo.business.customer.entity.Customer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
public class CustomerControl {

    private static final Logger LOG = getLogger(CustomerControl.class);

    @PersistenceContext
    EntityManager em;

    public Customer getCustomerById(long id) {
        return em.find(Customer.class, id);
    }

    public Customer saveOrUpdateCustomer(Customer customer) {
        return em.merge(customer);
    }

    public boolean deleteCustomer(Customer customer) {
        boolean deleted = false;
        if (customer != null) {
            try {
                Customer c = getCustomerById(customer.getId());
                em.remove(customer);
                deleted = true;
            } catch (IllegalArgumentException e) {
                LOG.warn("Could not remove customer {}", customer);
            }
        }

        return deleted;
    }
}
