package com.lamarrulla.bussines;

import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lamarrulla.api.API;
import com.lamarrulla.database.Inserts;
import com.lamarrulla.database.Selects;
import com.lamarrulla.model.TbParametrosGeneraRutas;

public class SaveRutasPasos {
static API api = new API();
	
	static JsonObject jso = new JsonObject();

	public void setTbdr(TbParametrosGeneraRutas tbdr) {
		this.tbdr = tbdr;
	}

	private String yEk;
		
	private TbParametrosGeneraRutas tbdr;
	
	static Inserts ins = new Inserts();		
	static Selects sel = new Selects();
	
	public void generaDatosForRutas() {
		//lee credenciales
		sel.selectCredenciales();
		sel.getListCredenciales().forEach(listcredenciales->yEk = listcredenciales.getFcYeK());
		System.out.println(yEk);
		getRutas();		
	}
	
//	public void generaDatosForUsers() {
//		ins.selectPasos();
//		List<TbPasos> listaTbPasos = new ArrayList<TbPasos>();
//		listaTbPasos = ins.getListPasos();
//		int count = 0;
//		for(TbPasos tp: listaTbPasos) {
//			IdPaso = tp.getFiIdPaso();
//			//EndLocationLat = tp.getFdoEndLocationLat().toString();
//			//EndLocationLng = tp.getFdoEndLocationLng().toString();
//			//StartLocationLat = tp.getFdoStartLocationLat().toString();
//			//StartLocationLng = tp.getFdoStartLocationLng().toString();
//			count ++;
//			if(count == listaTbPasos.size()) {
//				ins.setUltimoRegistro(true);
//			}
//			getUser();
//		}
//	}
	
//	public void getUser() {
//		try {
//			URL url = new URL("https://www.waze.com/row-rtserver/web/TGeoRSS?bottom=" + tbdr.getFdoStartLocationLatDR() + "&left=" + tbdr.getFdoStartLocationLngDR() + "&ma=0&mj=0&mu=400&right=" + tbdr.getFdoEndLocationLatDR() + "&top=" + tbdr.getFdoEndLocationLngDR() + "&types=alerts%2Ctraffic%2Cusers");
//			api.setUrl(url);
//			api.get();
//			//System.out.println(api.getSalida().toString());
//			jso = new JsonParser().parse(api.getSalida().toString()).getAsJsonObject();
//			ins.setId(IdPaso);
//			ins.setJso(jso);
//			ins.insertUsuarios();
//			//ins.guardaCSVUsuarios();
//		}catch(Exception ex) {
//			System.out.println("Error al salvar usuario: " + ex.getMessage());
//		}
//	}
	
	public void getRutas() {
		
		try {
			URL url = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=" + tbdr.getFdoStartLocationLatDR() + "," + tbdr.getFdoStartLocationLngDR() + "&destination=" + tbdr.getFdoEndLocationLatDR() + "," + tbdr.getFdoEndLocationLngDR() + "&key=" + yEk);
			api.setUrl(url);
			api.get();
			arreglaSalidaMaps(api.getSalida());
		} catch (MalformedURLException e) {
			System.out.println("Error al obtener rutas:" + e.getMessage());
			e.printStackTrace();
		}		
	} 
	
	private static void arreglaSalidaMaps(StringBuffer salida) {
    	if(salida.length()>0) {
    			jso = new JsonParser().parse(salida.toString()).getAsJsonObject();
    			System.out.println(jso.toString());    			
    			ins.setJso(jso);
    			ins.insertRutas();
    	}
	}
	
//	public void getDatosPasosForWaze() {
//		ins.selectPasos();
//		generaDatosForUsers();
//	}
}
