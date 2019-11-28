package com.verum.spa.consumeREST;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.verum.spa.model.Customer;
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

public class CustomerConsumeREST {

    static Client client = ClientBuilder.newClient();
    static WebTarget target;
    static String values = "";
    static GsonBuilder builder = new GsonBuilder();
    static Gson gson = builder.create();
    static Response response = null;
    static int statusCode;

    public int addCustomer(Customer cus) {
        target = client.
                target("http://localhost:8080/VerumRESTSpa2/api/customer/add");
        response = target.
                queryParam("firstName", cus.getFirstName()).
                queryParam("lastName1", cus.getLastName1()).
                queryParam("lastName2", cus.getLastName2()).
                queryParam("gender", cus.getGender()).
                queryParam("perAddress", cus.getPerAddress()).
                queryParam("telephone", cus.getTelephone()).
                queryParam("rfc", cus.getRfc()).
                //                queryParam("cusNumber", PENDIENTE).
                //                queryParam("conName", PENDIENTE CONNAME ). 
                queryParam("email", cus.getEmail()).
                queryParam("uniqueNumber", cus.getUniqueNumber()).
                queryParam("role", cus.getConsumer().getRole()).
                queryParam("pass", cus.getConsumer().getPass()).
                request(MediaType.APPLICATION_JSON).post(null);
        statusCode = response.getStatus();
        return statusCode;
    }

    public int modifyCustomer(Customer cus) {
        cus.setUniqueNumber("");
        target = client.
                target("http://localhost:8080/VerumRESTSpa2/api/customer/modify");
        response = target.
                queryParam("firstName", cus.getFirstName()).
                queryParam("lastName1", cus.getLastName1()).
                queryParam("lastName2", cus.getLastName2()).
                queryParam("gender", cus.getGender()).
                queryParam("perAddress", cus.getPerAddress()).
                queryParam("telephone", cus.getTelephone()).
                queryParam("rfc", cus.getRfc()).
                queryParam("email", cus.getEmail()).
                queryParam("cusStatus", cus.getCusStatus()).
                queryParam("charge", cus.getConsumer().getRole()).
                queryParam("conId", cus.getConsumer().getConId()).
                queryParam("cusId", cus.getCusId()).
                queryParam("perId", cus.getIdPer()).request()
                .put(entity(cus, MediaType.APPLICATION_JSON));
        statusCode = response.getStatus();
        return statusCode;

    }

    public int logicalDeleteCustomer(Customer cus) {
        target = client.target("http://localhost:8080/VerumRESTSpa2/api/customer/logDelete");
        response = target.queryParam("cusId", cus.getCusId()).
                request().put(entity(cus, MediaType.APPLICATION_JSON));
        statusCode = response.getStatus();
        return statusCode;
    }

    public ArrayList listCustomer() throws MalformedURLException, IOException {
        String ruta = "http://localhost:8080/VerumRESTSpa2/api/customer/customerList";
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

            Type collectionType = new TypeToken<ArrayList<Customer>>() {
            }.getType();
            ArrayList<Customer> customerCollection = gson.fromJson(contenidoRespuesta, collectionType);
            return customerCollection;
        }
        return null;
    }
}
