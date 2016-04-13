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
package com.linux.rhcloud.javaee.movieinfo.business.address.entity;

import com.airhacks.rulz.em.EntityManagerProvider;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
public class AddressIT {
    
     @Rule
    public EntityManagerProvider provider = EntityManagerProvider.persistenceUnit("it");
    
//    @Before
//    public void setUp() {
//    }

     @Test
     public void GetAddressById() {
         Address address = new Address();
         address.setAddress("1121 North Main Street");
         address.setAddress2("Loja Avenue");
         address.setDistrict("California");
         address.setCityId(449L);
         address.setPostalCode("17886");
         address.setPhone("838635286649");

         provider.tx().begin();
         provider.em().persist(address);
         provider.tx().commit();
     }
}
