/*=============================================================================
 |       Author:  Erick Ruben Ramos Vazquez
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
import com.verum.spa.model.Product;
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

public class ProductConsumeREST {

    static Client client = ClientBuilder.newClient();
    static WebTarget target;
    static String values = "";
    static GsonBuilder builder = new GsonBuilder();
    static Gson gson = builder.create();
    static Response response = null;
    static int statusCode;

    public int addProduct(Product pro) {
        target = client.
                target("http://localhost:8080/VerumRESTSpa2/api/product/add");
        response = target.queryParam("prodName", pro.getProdName()).
                queryParam("brand", pro.getBrand()).queryParam("useCost", pro.getUseCost()).
                request(MediaType.APPLICATION_JSON).post(null);
        statusCode = response.getStatus();
        return statusCode;
    }

    public int modifyProduct(Product pro) {
        target = client.
                target("http://localhost:8080/VerumRESTSpa2/api/product/modify");
        response = target.queryParam("prodName", pro.getProdName()).
                queryParam("brand", pro.getBrand()).
                queryParam("useCost", pro.getUseCost()).queryParam("prodStatus", pro.getProdStatus()).queryParam("prodId", pro.getProdId()).request()
                .put(entity(pro, MediaType.APPLICATION_JSON));
        statusCode = response.getStatus();
        return statusCode;

    }

    public int logicalDeleteProduct(Product pro) {
        pro.setBrand("");
        pro.setProdName("");
        pro.setUseCost(0.0);
        target = client.target("http://localhost:8080/VerumRESTSpa2/api/product/logDelete");
        response = target.queryParam("prodId", pro.getProdId()).
                request().put(entity(pro, MediaType.APPLICATION_JSON));
        statusCode = response.getStatus();
        return statusCode;
    }

    public ArrayList listProduct() throws MalformedURLException, IOException {
        String ruta = "http://localhost:8080/VerumRESTSpa2/api/product/productList";
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

            Type collectionType = new TypeToken<ArrayList<Product>>() {
            }.getType();
            ArrayList<Product> productCollection = gson.fromJson(contenidoRespuesta, collectionType);
            return productCollection;
        }
        return null;
    }

    /* 
    Metodo largo
        public void send() throws MalformedURLException, IOException {
        URL url = new URL(null, "http://localhost:8080/VerumRESTSpa2/api/product/add", new sun.net.www.protocol.https.Handler());
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setReadTimeout(20000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        List<Product> params = new ArrayList<Product>();
        params.add(new Product("Productooooo", "marcA", 10.5));

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(getQuery(params));
        writer.flush();
        writer.close();
        os.close();
        conn.connect();

    }

    public String getQuery(List<Product> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Product pair : params) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }

            result.append(URLEncoder.encode("prodName", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getProdName(), "UTF-8"));
            result.append(URLEncoder.encode("brand", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getBrand(), "UTF-8"));
            result.append(URLEncoder.encode("useCost", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(String.valueOf(pair.getUseCost()), "UTF-8"));
        }
        return result.toString();
    }

    
     */
}
