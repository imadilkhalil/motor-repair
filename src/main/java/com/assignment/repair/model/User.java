/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.repair.model;

/**
 *
 * @author ADIL
 */
public class User {
    private String username;
    private String password;
    private String role;
    
    public User(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getRole(){
        return this.role;
    }
    public void setRole(String role){
        this.role = role;
    }
}
