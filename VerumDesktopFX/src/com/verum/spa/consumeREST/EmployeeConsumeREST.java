package com.verum.spa.consumeREST;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.verum.spa.model.Employee;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import static javax.ws.rs.client.Entity.entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class EmployeeConsumeREST {

    static Client client = ClientBuilder.newClient();
    static WebTarget target;
    static String values = "";
    static GsonBuilder builder = new GsonBuilder();
    static Gson gson = builder.create();
    static Response response = null;
    static int statusCode;

    public int addEmployee(Employee emp) {
        target = client.
                target("http://localhost:8080/VerumRESTSpa2/api/employee/add");
        response = target.
                queryParam("firstName", emp.getFirstName()).
                queryParam("lastName1", emp.getLastName1()).
                queryParam("lastName2", emp.getLastName2()).
                queryParam("gender", emp.getGender()).
                queryParam("perAddress", emp.getPerAddress()).
                queryParam("telephone", emp.getTelephone()).
                queryParam("rfc", emp.getRfc()).
                queryParam("empNumber", emp.getEmpNumber()).
                queryParam("empPosition", emp.getEmpPosition()).
                queryParam("empStatus", emp.getEmpStatus()).
                queryParam("photo", emp.getPhoto()).
                queryParam("conName", emp.getConsumer().getConName()).
                queryParam("pass", emp.getConsumer().getPass()).
                queryParam("charge", emp.getConsumer().getRole()).
                request(MediaType.APPLICATION_JSON).post(null);
        statusCode = response.getStatus();
        return statusCode;
    }

    public int modifyEmployee(Employee emp) {
        target = client.
                target("http://localhost:8080/VerumRESTSpa2/api/employee/modify");
        response = target.
                queryParam("firstName", emp.getFirstName()).
                queryParam("lastName1", emp.getLastName1()).
                queryParam("lastName2", emp.getLastName2()).
                queryParam("gender", emp.getGender()).
                queryParam("perAddress", emp.getPerAddress()).
                queryParam("telephone", emp.getTelephone()).
                queryParam("empPosition", emp.getEmpPosition()).
                queryParam("empStatus", emp.getEmpStatus()).
                queryParam("photo", emp.getPhoto()).
                queryParam("charge", emp.getConsumer().getRole()).
                queryParam("pass", emp.getConsumer().getPass()).
                queryParam("empId", emp.getEmpId()).
                queryParam("conId", emp.getConsumer().getConId()).
                queryParam("perId", emp.getPerId()).request().
                put(entity(emp, MediaType.APPLICATION_JSON));
        statusCode = response.getStatus();
        return statusCode;
    }

    public int logicalDeleteEmployee(Employee emp) {
        emp.setFirstName("");
        emp.setLastName1("");
        emp.setLastName2("");
        emp.setGender("");
        emp.setPerAddress("");
        emp.setTelephone("");
        emp.setRfc("");
        emp.setEmpNumber("");
        emp.setEmpPosition("");
        emp.setEmpStatus(0);
        emp.setPhoto("");
        target = client.
                target("http://localhost:8080/VerumRESTSpa2/api/employee/logDelete");
        response = target.queryParam("empId", emp.getEmpId()).
                request().put(entity(emp, MediaType.APPLICATION_JSON));
        statusCode = response.getStatus();
        return statusCode;
    }

    public ArrayList<Employee> listEmployee() throws MalformedURLException, IOException {
        String ruta = "http://localhost:8080/VerumRESTSpa2/api/employee/listEmployee";
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

            Type collectionType = new TypeToken<ArrayList<Employee>>() {
            }.getType();
            ArrayList<Employee> empCollection = gson.fromJson(contenidoRespuesta, collectionType);
            return empCollection;
        }
        return null;
    }

}
