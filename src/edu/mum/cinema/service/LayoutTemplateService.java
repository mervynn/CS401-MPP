/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cinema.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import edu.mum.cinema.model.LayoutTemplate;
import edu.mum.cinema.model.Movie;
import edu.mum.cinema.utilities.WebServiceConnector;
import edu.mum.cinema.utilities.WebServiceConnector.HTTP_METHOD;

/**
 *
 * @author Mingwei
 */
public class LayoutTemplateService {
    public static List<LayoutTemplate> TEMPLATE = new ArrayList<LayoutTemplate>();
    static {
        TEMPLATE.add(new LayoutTemplate("1", "Dalby Hall"));
        TEMPLATE.add(new LayoutTemplate("2", "Vereil Hall"));
        TEMPLATE.add(new LayoutTemplate("3", "Argirel Center"));
        TEMPLATE.add(new LayoutTemplate("4", "Dreier Hall"));
        TEMPLATE.add(new LayoutTemplate("5", "McLaughLin"));
    }
    
    public static List<LayoutTemplate> getLayoutTemplateList(){
        LayoutTemplate[] layouts = WebServiceConnector.callWebService(HTTP_METHOD.GET, "layouttemplate", null, LayoutTemplate[].class);
        return Arrays.asList(layouts);
    }
}
