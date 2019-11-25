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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.verum.spa.model.Room;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class RoomConsumeREST {

    static Client client = ClientBuilder.newClient();
    static WebTarget target;
    static String values = "";
    static GsonBuilder builder = new GsonBuilder();
    static Gson gson = builder.create();
//    Product pro = new Product();

    //ADD
    public String addRoom(Room room) {
        target = client.target("http://localhost:8080/VerumRESTSpa/api/roomR/addRoom");
        values = target.request().post(Entity.entity(room, MediaType.APPLICATION_JSON), String.class);
        return values;
    }

    public String modifyRooom(Room room) {
        target = client.target("http://localhost:8080/VerumRESTSpa/api/pruebas/modifyRoom");
        values = target.request().put(Entity.entity(room, MediaType.APPLICATION_JSON), String.class);
        return values;
    }

    public String logicalDeleteRoom(Room room) {
        target = client.target("http://localhost:8080/VerumRESTSpa/api/roomR/deleteRoom");
        values = target.request().put(Entity.entity(room, MediaType.APPLICATION_JSON), String.class);
        return values;
    }

    public JsonArray listRoom() throws MalformedURLException, IOException {
        JsonParser parser = new JsonParser();
        JsonElement tradeElement = null;
        JsonObject json = null;
        JsonArray jsonAr = null;

        Gson g = new Gson();

        String ruta = "http://localhost:8080/VerumRESTSpa/api/roomR/roomList";
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

            Type collectionType = new TypeToken<Collection<Room>>() {
            }.getType();
            Collection<Room> enums = gson.fromJson(contenidoRespuesta, collectionType);
            enums.toString();

            System.out.println(enums);
            return null;
        }
        return null;
    }

    public void consultarPrecio() throws IOException {
        /* double precio = 0;
//        JsonObject json = listPro();
        JsonObject jsonRates = null;

        if (json != null) {
            jsonRates = json.getAsJsonObject("0").getAsJsonObject();
            precio = jsonRates.get("useCost").getAsDouble();
        }
        System.out.println(precio);
//        return precio;*/

    }
}
