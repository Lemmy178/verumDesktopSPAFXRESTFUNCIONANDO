/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verum.spa.consumeREST;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.verum.spa.commons.MySPACommons;
import com.verum.spa.model.Branch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
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
    public String addBranch(Branch branch){        
        target = client.target("http://localhost:8080/VerumRESTSpa2/api/branch/add");
        values = target.queryParam("branchName",branch.getBranchName()).
                queryParam("branchAddress",branch.getBranchAddress()).
                queryParam("latitude",branch.getLatitude()).
                queryParam("longitude",branch.getLongitude()).
                queryParam("branchStatus",branch.isBranchStatus()==true?"1":"2").
                request()
                .post(Entity.entity(branch, MediaType.APPLICATION_JSON),
                        String.class);
        return values;     
    }

    public String modifyBranch(Branch branch) {        
        target = client.target("http://localhost:8080/VerumRESTSpa2/api/branch/modify");
        values = target.queryParam("branchId",branch.getBranchId()).
                queryParam("branchName",branch.getBranchName()).
                queryParam("branchAddress",branch.getBranchAddress()).
                queryParam("latitude",branch.getLatitude()).
                queryParam("longitude",branch.getLongitude()).
                queryParam("branchStatus",branch.isBranchStatus()==true?"1":"2").
                request().put(Entity.entity(branch, MediaType.APPLICATION_JSON), String.class);
        return values;
    }

    public String logicalDeleteBranch(int id) {
        target = client.target("http://localhost:8080/VerumRESTSpa2/api/branch/logDelete?idBranch="+id);
        values = target.request().put(Entity.entity(id, MediaType.APPLICATION_JSON), String.class);
        return values;
    }

    public ArrayList<Branch> listBranch() throws Exception {
        String ruta = "http://localhost:8080/VerumRESTSpa2/api/branch/branchList";
        URL url = new URL(ruta);
        HttpURLConnection connHttp = (HttpURLConnection) url.openConnection();
        int respuestaServidor = 0;
        InputStreamReader isr = null;
        BufferedReader br = null;
        String lineaActual = null;
        String contenidoRespuesta = null;

        connHttp.setRequestMethod("GET");
        connHttp.setRequestProperty("dataType", "json");
        respuestaServidor = connHttp.getResponseCode();

        if (respuestaServidor == HttpURLConnection.HTTP_OK) {
            isr = new InputStreamReader(connHttp.getInputStream());
            br = new BufferedReader(isr);
            contenidoRespuesta = "";
            while ((lineaActual = br.readLine()) != null) {
                contenidoRespuesta += lineaActual;
            }
            br.close();
            connHttp.disconnect();

            Type collectionType = new TypeToken<ArrayList<Branch>>() {
            }.getType();
            ArrayList<Branch> branchCollection = gson.fromJson(contenidoRespuesta, collectionType);
            return branchCollection;
        }
        return null;
    }

}
