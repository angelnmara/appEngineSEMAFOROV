package com.lamarrulla;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lamarrulla.datosrutas.SaveRutasPasos;
import com.lamarrulla.model.TbParametrosGeneraRutas;
import com.lamarrulla.utils.LaMarrullaUtils;

@WebServlet(
	    name = "generaDatosForRutasAppEngine",
	    urlPatterns = {"/generaDatosForRutas"}
	)

public class ObtenDatosAppEngine extends HttpServlet {
	
	SaveRutasPasos srp = new SaveRutasPasos();
	LaMarrullaUtils utils = new LaMarrullaUtils();
	JsonObject jso = new JsonObject();
	
	  public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
		  
		  String salida;
		  
		  try {
			  //lee parametros
			  String parametrosEntrada = utils.recoverParams(request);
			  jso = new JsonParser().parse(parametrosEntrada).getAsJsonObject();	
			  
			  //old
//			  srp.setNombreRuta(jso.get("nombreRuta").getAsString());
//			  srp.setStartLocationLat(jso.get("p1Lat").getAsBigDecimal());
//			  srp.setStartLocationLng(jso.get("p1Lng").getAsBigDecimal());
//			  srp.setEndLocationLat(jso.get("p2Lat").getAsBigDecimal());
//			  srp.setEndLocationLng(jso.get("p2Lng").getAsBigDecimal());
			  
			  //new
			  TbParametrosGeneraRutas tbdr = new TbParametrosGeneraRutas();
//			  tbdr.setFcNombreDR(jso.get("nombreRuta").getAsString());
			  tbdr.setFdoStartLocationLatDR(jso.get("p1Lat").getAsDouble());
			  tbdr.setFdoStartLocationLngDR(jso.get("p1Lng").getAsDouble());
			  tbdr.setFdoEndLocationLatDR(jso.get("p2Lat").getAsDouble());
			  tbdr.setFdoEndLocationLngDR(jso.get("p2Lng").getAsDouble());
			  
			  srp.setTbdr(tbdr);
			  srp.generaDatosForRutas();

			  response.setContentType("text/json");
			  response.setCharacterEncoding("UTF-8");
			  salida = "Información porcesada correctamente";
		  }catch(Exception ex) {
			  salida = "ocurrio un error al porcesar la información " + ex;
		  }		 
	    response.getWriter().print("{\"salida\":\"" + salida + "\"}");
	  }
	  
	  
}
