/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.repair.service;

import com.assignment.repair.model.User;
import com.assignment.repair.exceptions.AuthException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ADIL
 */
public class AuthService {
    List<User> users = Arrays.asList(new User("alice", "password", "technician"), new User("bob", "password", "dataentry"));
    
    public String authAndGetRole(String username, String password) throws AuthException{
        List<User> user = 
                users.stream().filter(p -> p.getUsername().equals(username) && p.getPassword().equals(password))
                        .collect(Collectors.toList());
        
        if(user == null || user.isEmpty()){
            throw new AuthException("Username or Password not found!");
        }
        
        return user.get(0).getRole();
    }
    
}
