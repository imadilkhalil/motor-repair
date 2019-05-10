/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.repair.model;

import java.util.Date;

/**
 *
 * @author ADIL
 */
public class Task {
    public int id;
    public String name;
    public String type;
    public String duration;
    public String addedOn;
    public String status;
    public String inspectionResult;
    public Date completionDate;
    
    public Task(){}
    
    public Task(String name, String type, String duration, String addedOn){
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.addedOn = addedOn;
    }
    public Task(int id, String name, String type, String duration, String addedOn, String status, String inspectionResult, Date completionDate){
        this.id = id;
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.addedOn = addedOn;
        this.status = status;
        this.inspectionResult = inspectionResult;
        this.completionDate = completionDate;
    }
    
    public void setId(int id){
        this.id = id;
    }
}
