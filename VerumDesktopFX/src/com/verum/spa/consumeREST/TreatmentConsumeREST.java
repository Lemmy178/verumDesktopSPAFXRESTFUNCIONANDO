/*=============================================================================
 |       Author:  Ricardo Iván Ramírez Bello
 |       Course:  Spa
 |     Due Date:  10/18/2019
 |  Description:  Product Model
 |                
 | Deficiencies:  Funcionado. Valores se repiten en BD despues de tiempo.
                  Solo basta con crear otra clase y todo se soluciona.
                  Preguntar si producto va a llevar cantidad.
 *===========================================================================*/
package com.verum.spa.consumeREST;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.verum.spa.model.Treatment;
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
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TreatmentConsumeREST {

    static Response response = null;
    static Client client = ClientBuilder.newClient();
    static WebTarget target;
    static GsonBuilder builder = new GsonBuilder();
    static Gson gson = builder.create();
    static int statusCode;
    
    public int addTreatment(Treatment treat) {
        target = client.target("http://localhost:8080/VerumRESTSpa2/api/treatment/add");
        response = target.queryParam("treatName", treat.getTreatName()).
                          queryParam("treatDesc", treat.getTreatDesc()).
                          queryParam("cost", treat.getCost()).
                request(MediaType.APPLICATION_JSON).post(null);
        statusCode = response.getStatus();
        return statusCode;
    }

    public int modifyTreatment(Treatment treat) {
        target = client.target("http://localhost:8080/VerumRESTSpa2/api/treatment/modify");
        response = target.queryParam("treatName", treat.getTreatName()).
                          queryParam("treatDesc", treat.getTreatDesc()).
                          queryParam("cost", treat.getCost()).
                          queryParam("treatStatus", treat.getTreatStatus()).
                          queryParam("treatId", treat.getTreatId()).
                request(MediaType.APPLICATION_JSON).post(null);
        statusCode = response.getStatus();
        return statusCode;
    }

    public int logicalDeleteTreatment(Treatment treat) {
        target = client.target("http://localhost:8080/VerumRESTSpa2/api/treatment/logDelete");
        response = target.queryParam("treatId", treat.getTreatId()).
                request(MediaType.APPLICATION_JSON).post(null);
        statusCode = response.getStatus();
        return statusCode;
    }

    public ArrayList listRoom() throws MalformedURLException, IOException {
        String ruta = "http://localhost:8080/VerumRESTSpa2/api/treatment/treatmentList";
        URL url = new URL(ruta);
        HttpURLConnection connHttp = (HttpURLConnection) url.openConnection();
        int respuestaServidor;
        InputStreamReader isr;
        BufferedReader br;
        String lineaActual;
        String contenidoRespuesta;

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

            Type collectionType = new TypeToken<ArrayList<Treatment>>() {}.getType();
            ArrayList<Treatment> treatmentCollection = gson.fromJson(contenidoRespuesta, collectionType);
            return treatmentCollection;
        }
        return null;
    }
}
