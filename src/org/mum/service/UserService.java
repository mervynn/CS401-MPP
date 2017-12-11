/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.mum.model.User;

/**
 *
 * @author Mingwei
 */
public class UserService {
    
    static List<User> USER = new ArrayList<>();
    static {
        USER.add(new User("0", "admin", "123", "0", "first", "last", "email"));
        USER.add(new User("1", "seller", "123", "1", "", "", ""));
        for(int i = 2; i < 20; i++){
            USER.add(new User(String.valueOf(i), "seller" + String.valueOf(i - 1), "123", "1", "", "", ""));
        }
    }
    public static User getUserByUserNameAndPassword(String username, String password){
        for(User u : USER){
            if(username.equals(u.getUsername())){
                if(password.equals(u.getPassword()))
                    return u;
                break;
            }
        }
        return null;
    }
    
    public static List<User> getUserList(){
        return USER;
    }
    
    public static String addUser(User user){
        USER.add(user);
        return "Added successfully";
    }
    
    public static String updateUser(User user){
        int i = 0;
        for(User m : USER){
            if(m.getId().equals(user.getId())){
                break;
            }
            i++;
        }
        USER.remove(i);
        USER.add(user);
        return "Updated successfully";
    }
    
    public static String deleteUser(User user){
        int i = 0;
        for(User m : USER){
            if(m.getId().equals(user.getId()))
                break;
            i++;
        }
        USER.remove(i);
        return "Deleted successfully";
    }
    
    public static List<User> fuzzyQuery(String keyword){
        List<User> res = new ArrayList<User>();
        for(User m : USER)
            if(m.getId().contains(keyword) || m.getUsername().contains(keyword) 
                || m.getFirstName().contains(keyword) || m.getLastName().contains(keyword)
                || m.getRoleType().contains(keyword) || m.getEmail().contains(keyword))
                res.add(m);
        return res;
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
