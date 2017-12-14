/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cinema.utilities;

import java.text.SimpleDateFormat;

/**
 *
 * @author Mingwei
 */
public class UtilitiesFactory {
    private static SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy");
    public static SimpleDateFormat getSimpleDateFormatInstance(){
        return sdf;
    }
}
