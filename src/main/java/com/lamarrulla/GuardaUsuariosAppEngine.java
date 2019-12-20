package com.lamarrulla;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
	static SaveUsuariosW suw;
	ScheduledExecutorService executor;
	
	@Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {

		try {
			response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		    
		    executor.shutdown();
		    
		    System.out.println("Se termino el executor?" + executor.isShutdown());
		    
		}catch(Exception e) {
			response.getWriter().print("Ocurrio un error: " + e.getMessage());
		}
	    

	    response.getWriter().print("El servicio fue terminado?!\r\n" + executor.isShutdown());

	  }
	
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
			  
			  executor = Executors.newScheduledThreadPool(1);
			  executor.scheduleAtFixedRate(helloRunnable, 0, 60, TimeUnit.SECONDS);
			  					  
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
	
	static Runnable helloRunnable = new Runnable() {
	    public void run() {
	        System.out.println("GetDatos");
	        suw = new SaveUsuariosW();
	        suw.generaDatosForUsers();
	    }
	};
}
