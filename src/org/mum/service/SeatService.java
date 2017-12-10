/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.service;

import java.util.ArrayList;
import java.util.List;
import org.mum.model.Seat;
import org.mum.utilities.Constant;

/**
 *
 * @author Mingwei
 */
public class SeatService {
    public static List<Seat> seats = new ArrayList<Seat>();
    static {
        seats.add(new Seat("A", "8", Constant.SEATSTATUS_FREE, "20"));
        seats.add(new Seat("A", "2", Constant.SEATSTATUS_FREE, "20"));
        seats.add(new Seat("A1", "1", Constant.SEATSTATUS_OCCUPIED, "20"));
        seats.add(new Seat("D","2", Constant.SEATSTATUS_LOCKED_BYOTHERONE, "20"));
        seats.add(new Seat("D","1", Constant.SEATSTATUS_FREE, "25"));
        seats.add(new Seat("D","6", Constant.SEATSTATUS_FREE, "20"));
        seats.add(new Seat("A", "3", Constant.SEATSTATUS_OCCUPIED, "20"));
        seats.add(new Seat("A","12", Constant.SEATSTATUS_LOCKED_BYME, "20"));
        seats.add(new Seat("e","10", Constant.SEATSTATUS_OCCUPIED, "30"));
        seats.add(new Seat("G","100", Constant.SEATSTATUS_OCCUPIED, "30"));
        seats.add(new Seat("A", "9", Constant.SEATSTATUS_FREE, "20"));
        seats.add(new Seat("A","6", Constant.SEATSTATUS_OCCUPIED, "20"));
        seats.add(new Seat("D","3", Constant.SEATSTATUS_LOCKED_BYME, "35"));
        seats.add(new Seat("D","15", Constant.SEATSTATUS_FREE, "30"));
        seats.add(new Seat("E","3", Constant.SEATSTATUS_OCCUPIED, "30"));
        seats.add(new Seat("F","3", Constant.SEATSTATUS_OCCUPIED, "32"));
        seats.add(new Seat("A","5", Constant.SEATSTATUS_LOCKED_BYOTHERONE, "32"));
        seats.add(new Seat("A","4", Constant.SEATSTATUS_FREE, "30"));
        seats.add(new Seat("B","3", Constant.SEATSTATUS_OCCUPIED, "30"));
        seats.add(new Seat("C","3", Constant.SEATSTATUS_OCCUPIED, "31"));
        seats.add(new Seat("D","3", Constant.SEATSTATUS_FREE, "30"));
        seats.add(new Seat("F","100", Constant.SEATSTATUS_LOCKED_BYME, "30"));
        seats.add(new Seat("A","100", Constant.SEATSTATUS_FREE, "30"));
        seats.add(new Seat("e","100", Constant.SEATSTATUS_OCCUPIED, "30"));
        seats.add(new Seat("A","22", Constant.SEATSTATUS_FREE, "30"));

    }
    public static List<Seat> getSeatListByScheduleId(String scheduleId){
        seats.sort((Object o1, Object o2) -> {
            Seat s1 = (Seat) o1;
            Seat s2 = (Seat) o2;
            String row1 = s1.getRowNum().toUpperCase();
            String row2 = s2.getRowNum().toUpperCase();
            if(row1.compareTo(row2) > 0) return 1;
            if(row1.compareTo(row2) < 0) return -1;
            int col1 = Integer.valueOf(s1.getColumnNum());
            int col2 = Integer.valueOf(s2.getColumnNum());
            if(col1 > col2) return 1;
            if(col1 == col2) return 0;
            return -1;
        });
        return seats;
    }

}
