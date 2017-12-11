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
public class SectionTemplate {
    private String id;
    private String sectionLabel;
    private String layoutTemplateId;
    
    public SectionTemplate(){}
    public SectionTemplate(String id, String sectionLabel, String layoutTemplateId){
        this.id = id;
        this.sectionLabel = sectionLabel;
        this.layoutTemplateId = layoutTemplateId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSectionLabel() {
        return sectionLabel;
    }

    public void setSectionLabel(String sectionLabel) {
        this.sectionLabel = sectionLabel;
    }

    public String getLayoutTemplateId() {
        return layoutTemplateId;
    }

    public void setLayoutTemplateId(String layoutTemplateId) {
        this.layoutTemplateId = layoutTemplateId;
    }
}
