/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.repair.service;

import com.assignment.repair.model.Motor;
import com.assignment.repair.model.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADIL
 */
public class MotorService {
    private final MysqlConnect mysqlConnection = new MysqlConnect();
    
    private List<Motor> motors = new ArrayList<>();
    
    public List<Motor> fetchMotors(){
        return this.motors;
    }
    
    public List<Motor> addAndFetchMotors(Motor motor){
        String insertSql = "insert into motor(owner_name, make, model, date) values(?, ?, ?, ?)";
        
        try(Connection conn = mysqlConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setString(1, motor.ownerName);
            pstmt.setString(2, motor.make);
            pstmt.setString(3, motor.model);
            pstmt.setString(4, motor.date);
            
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(MotorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            mysqlConnection.disconnect();
        }
        
        return this.fetchAllMotors();
    }
    
    public List<Motor> fetchAllMotors(){
        String selectSql = "select id, owner_name, make, model, date from motor order by id asc";        
        List<Motor> motorsDTOs = new ArrayList<>();
        
        try (Connection conn = mysqlConnection.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(selectSql)) {
            
            while(rs.next()){
                motorsDTOs.add(new Motor(rs.getInt("id"), rs.getString("owner_name"), rs.getString("make"), rs.getString("model"), rs.getString("date")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MotorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            mysqlConnection.disconnect();
        }
        
        return motorsDTOs;
    }
    
    public List<Task> addAndFetchTasksForMotor(int motorId, Task task){
        String insertSql = "insert into task(motor_id, name, type, duration, added_on) values (?, ?, ?, ?, ?)";
        try(Connection conn = mysqlConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setInt(1, motorId);
            pstmt.setString(2, task.name);
            pstmt.setString(3, task.type);
            pstmt.setString(4, task.duration);
            pstmt.setString(5, task.addedOn);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MotorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            mysqlConnection.disconnect();
        }
        
        return this.fetchTasks(motorId);
    }
    
    public List<Task> fetchTasks(int motorId){
        String selectSql = "select id, name, type, duration, added_on, status, inspection_result, completion_date from task where motor_id = ? order by id asc";        
        List<Task> taskDtos = new ArrayList<>();
        try(Connection conn = mysqlConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(selectSql)){
            pstmt.setInt(1, motorId);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                taskDtos.add(new Task(rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getString("duration"), rs.getString("added_on"), rs.getString("status"), rs.getString("inspection_result"), rs.getDate("completion_date")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MotorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            mysqlConnection.disconnect();
        }
        
        return taskDtos;
    }
    
    public List<Task> updateTaskStatusAndFetchTasks(int motorId, int taskId, String status, String inspectionResult){
        String updateSql = "update task set status = ?, inspection_result = ?, completion_date = ? where id = ?";
        
        try(Connection conn = mysqlConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(updateSql)){
            pstmt.setString(1, status);
            pstmt.setString(2, inspectionResult);
            pstmt.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
            pstmt.setInt(4, taskId);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MotorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            mysqlConnection.disconnect();
        }
        
        return this.fetchTasks(motorId);
    }
}