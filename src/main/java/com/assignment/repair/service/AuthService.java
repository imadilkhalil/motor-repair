/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.repair.service;

import com.assignment.repair.model.User;
import com.assignment.repair.exceptions.AuthException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author ADIL
 */
public class AuthService {
    public static String LOGGED_IN_USER = null;
    private MysqlConnect mysqlConnection = new MysqlConnect();
    List<User> users = Arrays.asList(new User("alice", "password", "technician"), new User("bob", "password", "dataentry"));
    
    public String authAndGetRole(String username) throws AuthException{
        String selectSql = "select role from user where username = ?";        
        try (Connection conn = mysqlConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(selectSql);) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                LOGGED_IN_USER = username;
                return rs.getString("role");
            }
            else{
                throw new AuthException("Username not found!");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MotorService.class.getName()).log(Level.SEVERE, null, ex);
            throw new AuthException("Could not authenticate!");
        }
        finally{
            mysqlConnection.disconnect();
        }
    }
}
