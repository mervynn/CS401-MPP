/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.mum.model.Movie;
import org.mum.model.Schedule;
import org.mum.model.SectionPrice;
import org.mum.utilities.WebServiceConnector;
import org.mum.utilities.WebServiceConnector.HTTP_METHOD;

/**
 *
 * @author Mingwei
 */
public class ScheduleService {
    public static List<Schedule> SCHEDULES = new ArrayList<Schedule>();
    static {
        List<SectionPrice> spl = new ArrayList<SectionPrice>();
        spl.add(new SectionPrice("section1", 20d));
        spl.add(new SectionPrice("section2", 25d));
        spl.add(new SectionPrice("section3", 15d));
        for(int i = 0; i < 6; i++)
            SCHEDULES.add(new Schedule(String.valueOf(i), "DEC-10-2017", String.valueOf(i) + ":00", String.valueOf(i), "", String.valueOf(i), "",  spl));
    }
    
    // happens on load admin schedule list
    public static List<Schedule> getScheduleWithSectionPrice(){
        Schedule[] schedule = WebServiceConnector.callWebService(HTTP_METHOD.GET, "schedule", null, Schedule[].class);
        return Arrays.asList(schedule);
    }
    
    public static List<Movie> getMovieWithSchedule(String date){
//        for(int i = 0; i < MovieService.MOVIE.size(); i++){
//            MovieService.MOVIE.get(i).setSchedules(SCHEDULES);
//        }
//        return MovieService.MOVIE;
        Movie[] movie = WebServiceConnector.callWebService(HTTP_METHOD.GET, "moviewithschedule/" + date, null, Movie[].class);
        return Arrays.asList(movie);
    }
    
    public static String addSchedule(Schedule schedule){
        SCHEDULES.add(schedule);
        return "Added successfully";
    }
    
    public static String updateSchedule(Schedule schedule){
        int i = 0;
        for(Schedule m : SCHEDULES){
            if(m.getId().equals(schedule.getId()))
                break;
            i++;
        }
        SCHEDULES.remove(i);
        SCHEDULES.add(schedule);
        return "Updated successfully";
    }
    
    public static String deleteSchedule(Schedule schedule){
        int i = 0;
        for(Schedule m : SCHEDULES){
            if(m.getId().equals(schedule.getId()))
                break;
            i++;
        }
        SCHEDULES.remove(i);
        return "Deleted successfully";
    }
    
    public static List<Schedule> fuzzyQuery(String keyword){
        List<Schedule> res = new ArrayList<Schedule>();
        for(Schedule m : SCHEDULES)
            if(m.getDate().contains(keyword) || m.getTime().contains(keyword)|| m.getMovieId().contains(keyword)
                    || m.getTemplateId().contains(keyword)|| m.getId().contains(keyword))
                res.add(m);
        return res;
    }
    
}
