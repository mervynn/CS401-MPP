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
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.mum.model.User;
import org.mum.utilities.WebServiceConnector;
import org.mum.utilities.WebServiceConnector.HTTP_METHOD;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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
//        User user = USER.get(username);
//        if(user != null && password.equals(user.getPassword()))
//            return user;
//        return null;

        //Request header
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //Request body. with multiple values
        MultiValueMap<String, Object> map= new LinkedMultiValueMap<String, Object>();
        map.add("username", username);
        String psw = UserService.hash(password);
        map.add("password", psw);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);

        User user = WebServiceConnector.callWebService(HTTP_METHOD.POST, "authenticate", request, User.class);
        return user;
    }
    
    public static List<User> getUserList(){
        User[] users = WebServiceConnector.callWebService(HTTP_METHOD.GET, "user", null, User[].class);
        return Arrays.asList(users);
    }
    
    public static String addUser(User user){
        
        return WebServiceConnector.callWebService(HTTP_METHOD.POST, "user", user, String.class);
    }
    
    public static String updateUser(User user){
        
        return WebServiceConnector.callWebService(HTTP_METHOD.PUT, "user/" + user.getId(), user, String.class);
    }
    
    public static String deleteUser(User user){
        return WebServiceConnector.callWebService(HTTP_METHOD.DELETE, "user/" + user.getId(), user, String.class);
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
    
    public static String changePassword(User user){
   
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, Object> map= new LinkedMultiValueMap<String, Object>();
        map.add("id", user.getId());
        map.add("password", UserService.hash(user.getPassword()));

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);

        User u = WebServiceConnector.callWebService(HTTP_METHOD.POST, "changePassword", request, User.class);
        return (u==null)?"Fail":"Success";
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
        
        StringBuilder hexString = new StringBuilder();
        for (byte aMessageDigest : thedigest) {
            String h = Integer.toHexString(0xFF & aMessageDigest);
            while (h.length() < 2)
                h = "0" + h;
            hexString.append(h);
        }
        return hexString.toString().toUpperCase(Locale.US);

       // return thedigest.toString();
	}
}
