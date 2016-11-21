/*
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
 */
package com.linux.rhcloud.javaee.movieinfo.presentation;

import com.linux.rhcloud.javaee.movieinfo.business.film.boundry.FilmManager;
import com.linux.rhcloud.javaee.movieinfo.business.film.entity.Film;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author guru
 */
@Model
public class Films {

    private Film film;
    
    @Inject
    private FilmManager fm;
    
    @PostConstruct
    public void init(){
        this.film = new Film();
    }
    
    public List<Film> getAllFilms(){
        return fm.all();
    }
}
