/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import org.mum.model.User;

/**
 *
 * @author Mingwei
 */
public class UserService {
    
    static Map<String, User> USER = new HashMap<String, User>();
    static {
        User admin = new User();
        User seller = new User();
        admin.setUsername("admin");
        admin.setPassword("123");
        admin.setRoleType("0");
        admin.setFirstName("Admin");
        seller.setUsername("seller");
        seller.setPassword("123");
        seller.setRoleType("1");
        seller.setFirstName("Seller");
        USER.put("admin", admin);
        USER.put("seller", seller);
    }
    public static User getUserByUserNameAndPassword(String username, String password){
        User user = USER.get(username);
        if(user != null && password.equals(user.getPassword()))
            return user;
        return null;
    }
    
    public static String hash(String plain) {
		
		byte[] bytesOfMessage;
		MessageDigest md;
		byte[] thedigest = null;
		
		try {
			bytesOfMessage = plain.getBytes("UTF-8");
			md = MessageDigest.getInstance("MD5");
			thedigest = md.digest(bytesOfMessage);
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return thedigest.toString();
	}
}
