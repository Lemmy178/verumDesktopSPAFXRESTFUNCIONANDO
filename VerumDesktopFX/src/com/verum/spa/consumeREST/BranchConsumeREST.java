/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verum.spa.consumeREST;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.verum.spa.model.Branch;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author moi_3
 */
public class BranchConsumeREST {
    
    Client client = ClientBuilder.newClient();
    WebTarget target;
    String values = "";
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
//    Product pro = new Product();

    //ADD
    public String addBranch(Branch branch) {
        target = client.
                target("http://localhost:8080/verumSpaREST/api/branch/add");
        values = target.request()
                .post(Entity.entity(branch, MediaType.APPLICATION_JSON),
                        String.class);
        return values;
    }

    public String modifyBranch(Branch branch) {        
        target = client.target("http://localhost:8080/verumSpaREST/api/branch/updateBranch");
        values = target.request().put(Entity.entity(branch, MediaType.APPLICATION_JSON), String.class);
        return values;
    }

    public String logicalDeleteBranch(int id) {
        target = client.target("http://localhost:8080/verumSpaREST/api/branch/logicalDelete?idPro="+id);
        values = target.request().put(Entity.entity(id, MediaType.APPLICATION_JSON), String.class);
        return values;
    }

    public String listBranch() {
        target = client.
                target("http://localhost:8080/verumSpaREST/api/branch/branchList");
        values = target.request().get(String.class);
        return values;
    }

}
