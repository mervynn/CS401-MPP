/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.model;

import java.util.List;

/**
 *
 * @author Mingwei
 */
public class Schedule {
    private String id;
    private String date;
    private String time;
    private String movie;
    private String hall;
    private List<SectionPrice> sectionPrices;
    public Schedule(String id, String date, String time, String movie, String hall, List<SectionPrice> sectionPrices){
        this.id = id;
        this.date = date;
        this.time = time;
        this.movie = movie;
        this.hall = hall;
        this.sectionPrices = sectionPrices;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public List<SectionPrice> getSectionPrices() {
        return sectionPrices;
    }

    public void setSectionPrices(List<SectionPrice> sectionPrices) {
        this.sectionPrices = sectionPrices;
    }
    
}
