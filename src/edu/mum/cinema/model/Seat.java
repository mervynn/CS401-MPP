/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cinema.model;

/**
 *
 * @author Mingwei
 */
public class Seat {
    // this is occupied seat's primary key
    private String id;
    private String rowNum;
    private String columnNum;
    private String status;
    private String price;
    // these date use to show
    private String movieTitle;
    private String date;
    private String time;
    public Seat(){}
    public Seat(String id, String rowNum, String columnNum, String status, String price, String movieTitle, String date, String time){
        this.id = id;
        this.rowNum = rowNum;
        this.columnNum = columnNum;
        this.status = status;
        this.price = price;
        this.movieTitle = movieTitle;
        this.date = date;
        this.time = time;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRowNum() {
        return rowNum;
    }

    public void setRowNum(String rowNum) {
        this.rowNum = rowNum;
    }

    public String getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(String columnNum) {
        this.columnNum = columnNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
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
    @Override
    public String toString(){
        return this.rowNum + this.columnNum;
    }

}
