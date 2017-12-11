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
    private String movieId;
    private String templateId;
    private String dateTime;
    private List<SectionPrice> sectionPrices;
    public Schedule() {}
    public Schedule(String id, String date, String time, String movieId, String templateId, List<SectionPrice> sectionPrices){
        this.id = id;
        this.date = date;
        this.time = time;
        this.movieId = movieId;
        this.templateId = templateId;
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

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public List<SectionPrice> getSectionPrices() {
        return sectionPrices;
    }

    public void setSectionPrices(List<SectionPrice> sectionPrices) {
        this.sectionPrices = sectionPrices;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
    
}
