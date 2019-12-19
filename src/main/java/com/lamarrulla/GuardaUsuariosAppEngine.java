package com.lamarrulla;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lamarrulla.bussines.SaveRutasPasos;
import com.lamarrulla.bussines.SaveUsuariosW;
import com.lamarrulla.utils.LaMarrullaUtils;

@WebServlet(
	    name = "GuardaUsuariosAppEngine",
	    urlPatterns = {"/guardausuarios"}
	)

public class GuardaUsuariosAppEngine extends HttpServlet {	
	SaveRutasPasos srp = new SaveRutasPasos();
	LaMarrullaUtils utils = new LaMarrullaUtils();
	JsonObject jso = new JsonObject();
	SaveUsuariosW suw = new SaveUsuariosW();
	
	@Override
	  public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
		  
		  String salida;
		  
		  try {
			  //lee parametros
			  String parametrosEntrada = utils.recoverParams(request);
			  jso = new JsonParser().parse(parametrosEntrada).getAsJsonObject();				  
			  
			  //Setea parametros
			  int idRuta = jso.get("idRuta").getAsInt();
			  			  
			  suw.generaDatosForUsers();
//			  srp.setTbdr(tbdr);
//			  srp.generaDatosForRutas();

			  response.setContentType("text/json");
			  response.setCharacterEncoding("UTF-8");
			  salida = "Información porcesada correctamente";
		  }catch(Exception ex) {
			  salida = "ocurrio un error al porcesar la información " + ex;
		  }		 
	    response.getWriter().print("{\"salida\":\"" + salida + "\"}");
	  }
}
