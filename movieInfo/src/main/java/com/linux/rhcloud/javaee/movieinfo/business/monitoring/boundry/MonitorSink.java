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
package com.linux.rhcloud.javaee.movieinfo.business.monitoring.boundry;

import com.linux.rhcloud.javaee.movieinfo.business.monitoring.entity.CallEvent;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.json.JsonArray;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class MonitorSink {

    private CopyOnWriteArrayList<CallEvent> events;

    @PostConstruct
    public void init() {
        events = new CopyOnWriteArrayList<>();
    }

    public void onEvent(@Observes CallEvent callEvent) {
        if (events.size() > 30000) {
            events.clear();
        }
        events.add(callEvent);
    }

    public List<CallEvent> getEvents() {
        return events;
    }

    public LongSummaryStatistics getStatistics() {
        return events.stream().collect(Collectors.summarizingLong(CallEvent::getDurationInMillis));
    }

}
