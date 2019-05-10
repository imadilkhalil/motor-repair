/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.repair.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADIL
 */
public class Motor {
    public int id;
    public String ownerName;
    public String make;
    public String model;
    public String date;
    
    public List<Task> tasks;
    
    public Motor(){
        this.tasks = new ArrayList<>();
    }
    
    public Motor(String ownerName, String make, String model, String date){
        this();
        
        this.ownerName = ownerName;
        this.make = make;
        this.model = model;
        this.date = date;
    }
    
    public Motor(int id, String ownerName, String make, String model, String date){
        this();
        this.id = id;
        this.ownerName = ownerName;
        this.make = make;
        this.model = model;
        this.date = date;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void addTask(Task task){
        this.tasks.add(task);
    }
}
