package com.lamarrulla.bussines;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lamarrulla.api.API;
import com.lamarrulla.database.Inserts;
import com.lamarrulla.model.TbPasos;

public class SaveUsuariosW {
	
	private int IdPaso;
	private String EndLocationLat;
	private String EndLocationLng;
	private String StartLocationLat;
	private String StartLocationLng;
	Inserts ins;
	API api;
	JsonObject jso;
	
	public void generaDatosForUsers() {
		ins = new Inserts();
		ins.selectPasos();
		List<TbPasos> listaTbPasos = new ArrayList<TbPasos>();
		listaTbPasos = ins.getListPasos();
		int count = 0;
		for(TbPasos tp: listaTbPasos) {
			IdPaso = tp.getFiIdPaso();
			EndLocationLat = tp.getFdoEndLocationLat().toString();
			EndLocationLng = tp.getFdoEndLocationLng().toString();
			StartLocationLat = tp.getFdoStartLocationLat().toString();
			StartLocationLng = tp.getFdoStartLocationLng().toString();
			count ++;
			if(count == listaTbPasos.size()) {
				ins.setUltimoRegistro(true);
			}
			getUser();
		}
	}
	
	public void getUser() {
		try {
			jso = new JsonObject();
			api = new API();
			URL url = new URL("https://www.waze.com/row-rtserver/web/TGeoRSS?bottom=" + StartLocationLat + "&left=" + StartLocationLng + "&ma=0&mj=0&mu=400&right=" + EndLocationLng + "&top=" + EndLocationLat + "&types=alerts%2Ctraffic%2Cusers");
			api.setUrl(url);
			api.get();
			//System.out.println(api.getSalida().toString());
			jso = new JsonParser().parse(api.getSalida().toString()).getAsJsonObject();
			ins.setId(IdPaso);
			ins.setJso(jso);
			ins.insertUsuarios();
			//ins.guardaCSVUsuarios();
		}catch(Exception ex) {
			System.out.println("Error al salvar usuario: " + ex.getMessage());
		}
	}
}
