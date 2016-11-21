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
package com.linux.rhcloud.javaee.movieinfo.business.monitoring.control;

import com.linux.rhcloud.javaee.movieinfo.business.monitoring.entity.CallEvent;
import java.util.concurrent.TimeUnit;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
public class InvocationsMonitor {
    @Inject
    Event<CallEvent> callEvent;
    
    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception {
        String methodName = ic.getMethod().getName();
        long startTime = System.nanoTime();
        try{
            return ic.proceed();
        } finally {
            callEvent.fire(new CallEvent(methodName, TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime)));
        }
    }
 }
