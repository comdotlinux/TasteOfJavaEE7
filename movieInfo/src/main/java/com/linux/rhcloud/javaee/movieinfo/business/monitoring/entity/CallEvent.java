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
package com.linux.rhcloud.javaee.movieinfo.business.monitoring.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CallEvent {
    
    private String methodName;
    
    private long durationInMillis;

    public CallEvent(String methodName, long durationInMillis) {
        this.methodName = methodName;
        this.durationInMillis = durationInMillis;
    }

    public CallEvent() {
    }

    public String getMethodName() {
        return methodName;
    }

    public long getDurationInMillis() {
        return durationInMillis;
    }

    @Override
    public String toString() {
        return "CallEvent{" + "methodName=" + methodName + ", durationInMillis=" + durationInMillis + '}';
    }
    
    
}
