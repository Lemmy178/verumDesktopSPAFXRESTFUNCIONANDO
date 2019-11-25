/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verum.spa.model;

import com.verum.spa.consume.controller.BranchController;
import com.verum.spa.consumeREST.BranchConsumeREST;
import java.util.ArrayList;

/**
 *
 * @author moi_3
 */
public class branchtest2 {
   
        public static void main(String[] args) {        
        BranchConsumeREST access = new BranchConsumeREST();
        
        BranchController ctrl = new BranchController();
        
        ArrayList<Branch> response = ctrl.branchList();                
           
    }
        
}
