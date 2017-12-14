/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cinema.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import edu.mum.cinema.model.SectionTemplate;
import edu.mum.cinema.utilities.WebServiceConnector;
import edu.mum.cinema.utilities.WebServiceConnector.HTTP_METHOD;

/**
 *
 * @author Mingwei
 */
public class SectionTemplateService {
    public static List<SectionTemplate> SECTION_TEMPLATES = new ArrayList<SectionTemplate>();
    static {
        SECTION_TEMPLATES.add(new SectionTemplate("1", "Section1", "1"));
        SECTION_TEMPLATES.add(new SectionTemplate("2", "Section2", "1"));
        SECTION_TEMPLATES.add(new SectionTemplate("3", "Section3", "1"));
        SECTION_TEMPLATES.add(new SectionTemplate("4", "Section1", "2"));
        SECTION_TEMPLATES.add(new SectionTemplate("5", "Section2", "2"));
        SECTION_TEMPLATES.add(new SectionTemplate("6", "Section3", "2"));
        SECTION_TEMPLATES.add(new SectionTemplate("7", "Section1", "3"));
        SECTION_TEMPLATES.add(new SectionTemplate("8", "Section2", "3"));
        SECTION_TEMPLATES.add(new SectionTemplate("9", "Section3", "2"));
        SECTION_TEMPLATES.add(new SectionTemplate("10", "Section4", "1"));
        SECTION_TEMPLATES.add(new SectionTemplate("11", "Section1", "4"));
        SECTION_TEMPLATES.add(new SectionTemplate("12", "Section2", "4"));
        SECTION_TEMPLATES.add(new SectionTemplate("13", "Section3", "4"));
        
    }
    
    // used for adding a new schedule when selecting a template
    public static List<SectionTemplate> getSectionsByTemplateId(String templateId){
//        List<SectionTemplate> sections = new ArrayList<>();
//        for(SectionTemplate st : SECTION_TEMPLATES)
//            if(templateId.equals(st.getLayoutTemplateId()))
//                sections.add(st);
//        return sections;

        SectionTemplate[] layouts = WebServiceConnector.callWebService(HTTP_METHOD.GET, "sectiontemplatebylayoutid/" + templateId, null, SectionTemplate[].class);
        return Arrays.asList(layouts);
    }
}
