package com.lamarrulla.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;

public class API {
	JsonObject jso;
	StringBuffer salida;
	URL url;
	public URL getUrl() {
		return url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}
	public StringBuffer getSalida() {
		return salida;
	}
	public void setSalida(StringBuffer salida) {
		this.salida = salida;
	}
	public JsonObject getJso() {
		return jso;
	}
	public void setJso(JsonObject jso) {
		this.jso = jso;
	}
	
	private static final String USER_AGENT = "Mozilla/5.0";
	
	HttpURLConnection conn;
	
	public void get() {
        try {        	
            //URL url = new URL("https://maps.googleapis.com/maps/api/js/DirectionsService.Route?5m4&1m3&1m2&1d19.4950119&2d-99.11960449999998&5m4&1m3&1m2&1d19.2800339&2d-99.17037160000001&6e0&12ses-MX&23e1&callback=_xdc_._ft28bq&key=AIzaSyAndp8rBJEaJnxjKdLJV5rfxE8guaZH3Ic&token=106168");//your url i.e fetch data from .
            //URL url = new URL("https://www.waze.com/row-rtserver/web/TGeoRSS?bottom=19.51304459775636&left=-99.28868293762208&ma=0&mj=0&mu=400&right=-99.09092903137207&top=19.55468708780126&types=alerts%2Ctraffic%2Cusers");//your url i.e fetch data from .
        	salida = new StringBuffer();
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", USER_AGENT);
            conn.setRequestProperty("Referer", "https://www.waze.com/es/livemap?utm_source=waze_website&utm_campaign=waze_website");
            //conn.setRequestProperty("Accept", "application/json");
    		int responseCode = conn.getResponseCode();
    		System.out.println("GET Response Code :: " + responseCode);            
            if (responseCode == sun.net.www.protocol.http.HttpURLConnection.HTTP_OK) {
            	BufferedReader in = new BufferedReader(new InputStreamReader(
    					conn.getInputStream()));
    			String inputLine;
    			//StringBuffer response = new StringBuffer();

    			while ((inputLine = in.readLine()) != null) {
    				salida.append(inputLine);
    			}
    			in.close();

    			// print result
    			System.out.println(salida.toString());
            }else {
    			System.out.println("GET request not worked");

            }
//            InputStreamReader in = new InputStreamReader(conn.getInputStream());
//            BufferedReader br = new BufferedReader(in);
//            String output= "";
//            salida = "";
//            while ((output = br.readLine()) != null) {            	
//            	salida += output;          
//            }
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
            conn.disconnect();
        }
    }
}
