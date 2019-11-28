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
import com.verum.spa.model.Room;
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

public class RoomConsumeREST {

    static Response response = null;
    static Client client = ClientBuilder.newClient();
    static WebTarget target;
    static GsonBuilder builder = new GsonBuilder();
    static Gson gson = builder.create();
    static int statusCode;


    public int addRoom(Room room) {
        target = client.target("http://localhost:8080/VerumRESTSpa2/api/room/add");
        response = target.queryParam("roomName", room.getRoomName()).
                          queryParam("roomDesc", room.getRoomDesc()).
                          queryParam("photo", room.getPhoto()).
                          queryParam("branchId", room.getBranch().getBranchId()).
                request(MediaType.APPLICATION_JSON).post(null);
        statusCode = response.getStatus();
        return statusCode;
    }

    public int modifyRoom(Room room) {
        target = client.target("http://localhost:8080/VerumRESTSpa2/api/room/modify");
        response = target.queryParam("roomName", room.getRoomName()).
                          queryParam("roomDesc", room.getRoomDesc()).
                          queryParam("photo", room.getPhoto()).
                          queryParam("branchId", room.getBranch().getBranchId()).
                          queryParam("roomStatus", room.getRoomStatus()).
                          queryParam("roomId", room.getRoomId()).
                request(MediaType.APPLICATION_JSON).put(entity(room, MediaType.APPLICATION_JSON));
        statusCode = response.getStatus();
        return statusCode;
    }
    
    public int logicalDeleteRoom(Room room) {
        target = client.target("http://localhost:8080/VerumRESTSpa2/api/room/logDelete");
        response = target.queryParam("roomId", room.getRoomId()).request(MediaType.APPLICATION_JSON).put(entity(room, MediaType.APPLICATION_JSON));
        statusCode = response.getStatus();
        return statusCode;
    }

    public ArrayList listRoom() throws MalformedURLException, IOException {
        String ruta = "http://localhost:8080/VerumRESTSpa2/api/room/roomList";
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

            Type collectionType = new TypeToken<ArrayList<Room>>() {}.getType();
            ArrayList<Room> roomCollection = gson.fromJson(contenidoRespuesta, collectionType);
            return roomCollection;
        }
        return null;
    }
    
}
