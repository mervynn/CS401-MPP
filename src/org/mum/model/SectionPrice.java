/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.model;

/**
 *
 * @author Mingwei
 */
public class SectionPrice {
    private String id;
    private String sectionId;
    private String sectionName;
    private Double price;
    public SectionPrice(){}
    public SectionPrice(String sectionName, Double price){
        this.sectionName = sectionName;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
}
