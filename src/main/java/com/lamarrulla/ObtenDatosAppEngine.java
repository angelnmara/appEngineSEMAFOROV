package com.lamarrulla;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lamarrulla.datosrutas.SaveRutasPasos;

@WebServlet(
	    name = "ObtenDatosAppEngine",
	    urlPatterns = {"/obtendatos"}
	)

public class ObtenDatosAppEngine extends HttpServlet {
	
	SaveRutasPasos srp = new SaveRutasPasos();
	
	  public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {

		  srp.generaDatosForRutas();
		  
	    response.setContentType("text/json");
	    response.setCharacterEncoding("UTF-8");

	    response.getWriter().print("{\"valor\":\"Obten Datos!\"}");

	  }
}
