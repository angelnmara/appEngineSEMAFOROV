package com.lamarrulla;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lamarrulla.bussines.SaveRutasPasos;
import com.lamarrulla.model.TbParametrosGeneraRutas;
import com.lamarrulla.utils.LaMarrullaUtils;

@WebServlet(
	    name = "GuardaRutaAppEngine",
	    urlPatterns = {"/guardaruta"}
	)

public class GuardaRutaAppEngine extends HttpServlet {
	
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
			  
			  //Setea parametros
			  TbParametrosGeneraRutas tbdr = new TbParametrosGeneraRutas();
			  tbdr.setFdoStartLocationLatDR(jso.get("fdoStartLocationLatDR").getAsDouble());
			  tbdr.setFdoStartLocationLngDR(jso.get("fdoStartLocationLngDR").getAsDouble());
			  tbdr.setFdoEndLocationLatDR(jso.get("fdoEndLocationLatDR").getAsDouble());
			  tbdr.setFdoEndLocationLngDR(jso.get("fdoEndLocationLngDR").getAsDouble());
			  
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
