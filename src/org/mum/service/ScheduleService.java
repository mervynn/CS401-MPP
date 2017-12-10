/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.service;

import java.util.ArrayList;
import java.util.List;
import org.mum.model.Movie;

/**
 *
 * @author Mingwei
 */
public class ScheduleService {
    public static List<Movie> getSchedule(String date){
        List<String> sc = new ArrayList<String>();
        sc.add("12:00");
        sc.add("15:00");
        sc.add("18:00");
        sc.add("19:00");
        List<String> sc1 = new ArrayList<String>();
        sc1.add("18:00");
        sc1.add("19:00");
        sc1.add("21:00");
        for(int i = 0; i < MovieService.MOVIE.size(); i++){
            if(i%2 == 0) MovieService.MOVIE.get(i).setSchedules(sc);
            else MovieService.MOVIE.get(i).setSchedules(sc1);
        }
        return MovieService.MOVIE;
    }
}
