/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cinema.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import edu.mum.cinema.model.User;
import edu.mum.cinema.service.UserService;

public class WebServiceConnector {

    public enum HTTP_METHOD {
        GET, POST, PUT, DELETE
    }

   public static final String URL_PREFIX = "http://localhost:8080/cinema/";

    public static <Req, Res> Res callWebService(HTTP_METHOD httpMethod,
            String uri, //name of REST API after server url prefix.
            Req req, //request body. i.e. in createUser, req should be a User object to be saved
            Class<Res> resType) { //class type expected from response

        uri = URL_PREFIX + uri;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Res> responseEntity = null;

        System.out.println(uri);

        switch (httpMethod) {
            case GET:
                responseEntity = restTemplate.getForEntity(uri, resType);
                break;
            case POST:
                responseEntity = restTemplate.postForEntity(uri, req, resType);
                break;
            case PUT:
                restTemplate.put(uri, req);
                break;
            case DELETE:
                restTemplate.delete(uri);
                break;
            default:
                break;
        }

        return (resType == null || responseEntity == null ? null : responseEntity.getBody());
    }

    /**
     * Convert Object into JSON String.
     *
     * @param t
     * @return JsonString
     */
    protected <T> String getObjectAsString(T t) {

        ObjectMapper mapper = new ObjectMapper();
        JSONPObject jsonObj = new JSONPObject("", t);

        String requestStr = null;

        try {
            requestStr = mapper.writeValueAsString(jsonObj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return requestStr;
    }

    /*------------------------ Testing code starts ------------------------*/
	public List<User> getAllUsers() {
		
		User[] users = callWebService(HTTP_METHOD.GET, "user", null, User[].class);
		
		return Arrays.asList(users);
	}
	
	public void createUser(User user) {
		
		String response = callWebService(HTTP_METHOD.POST, 
						"user/", 
						user, 
						String.class);
		System.out.println(response);
	}
//	
//	public List<Long> toggleSeats(List<Long> seatOccupancyIds, Long userId) {
//		/*  This only works for mapped string values. Need a wrapper object -> DTO
//		//Request header
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//		//Request body. with multiple values
//		MultiValueMap<String, Object> map= new LinkedMultiValueMap<String, Object>();
//		map.add("selectedSeatIds", seatOccupancyIds);
//		map.add("userId", 1);
//
//		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);
//		*/
//		ToggleSeatReqDto reqDto = new ToggleSeatReqDto(userId, seatOccupancyIds);
//		
//		Long[] lockedList = callWebService(HTTP_METHOD.POST, "toggleSeatStatus", reqDto, Long[].class);
//		
//		return Arrays.asList(lockedList);
//	}
//	
//	public SeatOccupancy getSeatOccupancy(Long id) {
//		return callWebService(HTTP_METHOD.GET, "seatoccupancy/" + id, null, SeatOccupancy.class);
//	}
//	
//	public void releaseSeatsByUserId(Long userId) {
//		callWebService(HTTP_METHOD.PUT, "releaseSeatByUserId/" + userId, null, String.class);
//	}
//	
//	public List<Ticket> orderTickets(List<SeatOccupancy> seatOccupancies, Long userId) {
//		
//		OrderReqDto reqDto = new OrderReqDto(userId, seatOccupancies);
//		Ticket[] ticketList = callWebService(HTTP_METHOD.POST, "orderTickets/", reqDto, Ticket[].class);
//		
//		return Arrays.asList(ticketList);
//	}
	public static void main(String[] args) {
		WebServiceConnector connector = new WebServiceConnector();
		
//		//--------------- Test release seats selected by a user --------------
//		connector.releaseSeatsByUserId(1L);
		
		//--------------- Test add user -------------------
		User u = new User();
		u.setFirstName("PPP");
		u.setLastName("MMM");
		u.setUsername("admin");
                u.setRoleType("1");
                String psw = UserService.hash("admin");
                System.out.println(psw);
		u.setPassword(psw);
		
		connector.createUser(u);
		
		//--------------- Test get all users -------------------
		List<User> users = connector.getAllUsers();
		for(User user : users) {
			System.out.println(user.getFirstName());
		}
		
		
//		//--------------- Test toggleseats -------------------
//		List<Long> selectedSeatIds = new ArrayList<>();
//		selectedSeatIds.add(2L);
//		selectedSeatIds.add(3L);
//		selectedSeatIds.add(4L);
//		List<Long> lockedList = connector.toggleSeats(selectedSeatIds, 1L);
//		System.out.println("Locked ids...");
//		for(Long id : lockedList) {
//			System.out.println(id);
//		}
//		
//		//---------------- Test booking ----------------------
//		List<SeatOccupancy> seatOccupancies = new ArrayList<>();
//		seatOccupancies.add(connector.getSeatOccupancy(2L));
//		seatOccupancies.add(connector.getSeatOccupancy(3L));
//		seatOccupancies.add(connector.getSeatOccupancy(4L));
//		List<Ticket> ticketList = connector.orderTickets(seatOccupancies, 1L);
//		for(Ticket ticket : ticketList) {
//			System.out.println(ticket.getSeatLabel() + " : $" + ticket.getPrice());
//		}
		
		
	}
    /*------------------------ Testing code ends ------------------------*/
}
