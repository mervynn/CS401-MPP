/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.service;

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
}
