/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verum.spa.consume.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.verum.spa.consumeREST.BranchConsumeREST;
import com.verum.spa.model.Branch;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 * @author moi_3
 */
public class BranchController {
    
    private static Branch branch = new Branch();
    private static BranchConsumeREST braREST = new BranchConsumeREST();
    
    public String addBranch(String branchName, String branchAddress, double latitude, double longitude, boolean branchStatus) {        
            branch.setBranchName(branchName);
            branch.setBranchAddress(branchAddress);
            branch.setLatitude(latitude);
            branch.setLongitude(longitude);
            branch.setBranchStatus(branchStatus);
            return braREST.addBranch(branch);
    }
    

    public String modifyBranch(int branchId, String branchName, String branchAddress, double latitude, double longitude, boolean branchStatus) {        
            branch.setBranchId(branchId);
            branch.setBranchName(branchName);
            branch.setBranchAddress(branchAddress);
            branch.setLatitude(latitude);
            branch.setLongitude(longitude);
            branch.setBranchStatus(branchStatus);            
            return braREST.modifyBranch(branch);
    }
    

    public String logicalDelte(int branchId) {
        branch.setBranchId(branchId);
        return braREST.logicalDeleteBranch(branch.getBranchId());
    }

    public ArrayList<Branch> branchList() {

        String serverResponse = braREST.listBranch();
        
        ArrayList<Branch> branchList = null;
        
        if(!serverResponse.equals("false")){
            Gson gson = new Gson();
            Type branchListType = new TypeToken<ArrayList<Branch>>(){}.getType();
            branchList = gson.fromJson(serverResponse, branchListType);        
        }        
        
        return branchList;
        
    }
    
    
}
