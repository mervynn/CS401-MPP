/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.service;

import java.util.ArrayList;
import java.util.List;
import org.mum.model.LayoutTemplate;

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
        return TEMPLATE;
    }
}
