/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cinema.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import edu.mum.cinema.context.ApplicationContext;
import edu.mum.cinema.model.OrderReqDto;
import edu.mum.cinema.model.Seat;
import edu.mum.cinema.utilities.Constant;
import edu.mum.cinema.utilities.WebServiceConnector;
import edu.mum.cinema.utilities.WebServiceConnector.HTTP_METHOD;

/**
 *
 * @author Mingwei
 */
public class SeatService {
    public static List<Seat> seats = new ArrayList<Seat>();
    static {
        seats.add(new Seat("1", "A", "8", Constant.SEATSTATUS_FREE, "20", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("2", "A", "2", Constant.SEATSTATUS_FREE, "20", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("3", "A1", "1", Constant.SEATSTATUS_OCCUPIED, "20", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("4", "D","2", Constant.SEATSTATUS_LOCKED_BYOTHERONE, "20", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("5", "D","1", Constant.SEATSTATUS_FREE, "25", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("6", "D","6", Constant.SEATSTATUS_FREE, "20", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("7", "A", "3", Constant.SEATSTATUS_OCCUPIED, "20", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("8", "A","12", Constant.SEATSTATUS_LOCKED_BYME, "20", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("9", "e","10", Constant.SEATSTATUS_OCCUPIED, "30", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("10", "G","100", Constant.SEATSTATUS_OCCUPIED, "30", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("11", "A", "9", Constant.SEATSTATUS_FREE, "20", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("12", "A","6", Constant.SEATSTATUS_OCCUPIED, "20", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("13", "D","3", Constant.SEATSTATUS_LOCKED_BYME, "35", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("13", "D","15", Constant.SEATSTATUS_FREE, "30", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("15", "E","3", Constant.SEATSTATUS_OCCUPIED, "30", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("16", "F","3", Constant.SEATSTATUS_OCCUPIED, "32", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("17", "A","5", Constant.SEATSTATUS_LOCKED_BYOTHERONE, "32", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("18", "A","4", Constant.SEATSTATUS_FREE, "30", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("19", "B","3", Constant.SEATSTATUS_OCCUPIED, "30", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("20", "C","3", Constant.SEATSTATUS_OCCUPIED, "31", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("21", "D","3", Constant.SEATSTATUS_FREE, "30", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("22", "F","100", Constant.SEATSTATUS_LOCKED_BYME, "30", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("23", "A","100", Constant.SEATSTATUS_FREE, "30", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("24", "e","100", Constant.SEATSTATUS_OCCUPIED, "30", "AVATAR", "DEC-12-2017", "14:00"));
        seats.add(new Seat("25", "A","22", Constant.SEATSTATUS_FREE, "30", "AVATAR", "DEC-12-2017", "14:00"));

    }
    public static List<Seat> getSeatListByScheduleId(String scheduleId){
        Seat[] seatAry = WebServiceConnector.callWebService(HTTP_METHOD.GET, "seatsByScheduleId/" + scheduleId, null, Seat[].class);
        seats = Arrays.asList(seatAry);
        
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
    
    public static List<Seat> getSeatList(){
        return seats;
    }
    
    public static String addSeat(Seat seat){
        seats.add(seat);
        return "Added successfully";
    }
    
    // only update status
    public static String updateSeat(Seat seat){
        int i = 0;
        for(Seat m : seats){
            if(m.getId().equals(seat.getId())){
                // only update status
                m.setStatus(seat.getStatus());
                break;
            }  
            i++;
        }
        seats.get(i).setStatus(seat.getStatus());
        return "Updated successfully";
    }
    
    public static String deleteSeat(Seat seat){
        int i = 0;
        for(Seat m : seats){
            if(m.getId().equals(seat.getId()))
                break;
            i++;
        }
        seats.remove(i);
        return "Deleted successfully";
    }
    
    public static String submitSeats(List<Seat> seatP){
        for(Seat m : seats){
            for(Seat s : seatP){
                if(m.getId().equals(s.getId())){
                    m.setStatus(s.getStatus());
                }
            }
        }
        
        OrderReqDto orderReq = new OrderReqDto();
        orderReq.setUserId(ApplicationContext.currentUser.getId());
        orderReq.setSeatList(seatP); 
        
        String msg = WebServiceConnector.callWebService(HTTP_METHOD.POST, "orderTickets", orderReq, String.class);
        
        return msg;
    }
    
    public static List<Seat> fuzzyQuery(String keyword){
        List<Seat> res = new ArrayList<Seat>();
        for(Seat m : seats)
            if(m.getId().contains(keyword) || m.getColumnNum().contains(keyword) 
                || m.getRowNum().contains(keyword) || m.getStatus().contains(keyword)
                || m.getStatus().contains(keyword) || m.getDate().contains(keyword) 
                || m.getTime().contains(keyword))
                res.add(m);
        return res;
    }

}
