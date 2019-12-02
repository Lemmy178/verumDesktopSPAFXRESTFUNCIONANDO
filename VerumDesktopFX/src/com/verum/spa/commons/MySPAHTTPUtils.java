package com.verum.spa.commons;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class MySPAHTTPUtils {

    public static String read(HttpURLConnection httpCon) throws IOException {
        InputStreamReader isr = new InputStreamReader(httpCon.getInputStream(), "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String contenido = "";
        String lineaActual = "";

        while ((lineaActual = br.readLine()) != null) {
            contenido += lineaActual;
        }
        return contenido;
    }

    public static void write(HttpURLConnection httpCon, String contenido) throws IOException {
        byte[] bytes = contenido.getBytes();
        DataOutputStream dos = new DataOutputStream(httpCon.getOutputStream());
        dos.write(bytes);
        dos.close();
    }

}
