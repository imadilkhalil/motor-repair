/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.repair.service;

import com.assignment.repair.model.Motor;
import com.assignment.repair.model.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 *
 * @author ADIL
 */
public class MotorService {
    private List<Motor> motors = new ArrayList<>();
    private AtomicInteger uniqueIdForMotors = new AtomicInteger(1);
    private AtomicInteger uniqueIdForTasks = new AtomicInteger(1);
    
    public List<Motor> fetchMotors(){
        return this.motors;
    }
    
    public List<Motor> addAndFetchMotors(Motor motor){
        
        motor.setId(uniqueIdForMotors.getAndIncrement());
        this.motors.add(motor);
        
        return this.motors;
    }
    
    public List<Task> addAndTaskForMotor(int motorId, Task task){
        List<Motor> motorList = this.motors.stream().filter(p -> p.id == motorId).collect(Collectors.toList());
        if(motorList == null)
            return new ArrayList<>();
        
        Motor m = motorList.get(0); //This always will have a single item since the ID is Atomic and unique
        task.setId(uniqueIdForTasks.getAndIncrement());
        m.addTask(task);
        
        return m.tasks;
    }
    
    public List<Task> fetchTasks(int motorId){
        List<Motor> motorList = this.motors.stream().filter(p -> p.id == motorId).collect(Collectors.toList());
        if(motorList == null)
            return new ArrayList<>();
        
        Motor m = motorList.get(0);
        
        return m.tasks;
    }
}