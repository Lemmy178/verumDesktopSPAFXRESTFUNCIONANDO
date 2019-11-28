/*=============================================================================
 |       Author:  Ricardo Iván Ramírez Bello
 |       Course:  Spa
 |     Due Date:  11/03/2019
 |  Description:  Treatment Model
 |                
 | Deficiencies:  No detected.
 *===========================================================================*/
package com.verum.spa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Treatment {
    
    @SerializedName("treatId")
    @Expose
    private int treatId;
    @SerializedName("treatName")
    @Expose
    private String treatName;
    @SerializedName("treatDesc")
    @Expose
    private String treatDesc;
    @SerializedName("cost")
    @Expose
    private double cost;
    @SerializedName("treatStatus")
    @Expose
    private int treatStatus;
    
    public Treatment(){}

    public Treatment(int treatId, String treatName, String treatDesc, double cost, int treatStatus) {
        this.treatId = treatId;
        this.treatName = treatName;
        this.treatDesc = treatDesc;
        this.cost = cost;
        this.treatStatus = treatStatus;
    }

    public int getTreatId() {
        return treatId;
    }

    public void setTreatId(int treatId) {
        this.treatId = treatId;
    }

    public String getTreatName() {
        return treatName;
    }

    public void setTreatName(String treatName) {
        this.treatName = treatName;
    }

    public String getTreatDesc() {
        return treatDesc;
    }

    public void setTreatDesc(String treatDesc) {
        this.treatDesc = treatDesc;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getTreatStatus() {
        return treatStatus;
    }

    public void setTreatStatus(int treatStatus) {
        this.treatStatus = treatStatus;
    }

}
