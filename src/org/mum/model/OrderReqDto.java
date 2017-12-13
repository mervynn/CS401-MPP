/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.model;

import java.util.List;

public class OrderReqDto {

	private String userId;
	private List<Seat> seatList;
	
	public OrderReqDto() {
	}
	
	public OrderReqDto(String userId, List<Seat> seatList) {
		super();
		this.userId = userId;
		this.seatList = seatList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Seat> getSeatList() {
		return seatList;
	}

	public void setSeatList(List<Seat> seatList) {
		this.seatList = seatList;
	}
}