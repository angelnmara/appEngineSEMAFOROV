package com.lamarrulla.model;

public class TbParametrosGeneraRutas {	
	
//	public String getQryStringInsert() {
//		return qryStringInsert = String.format("insert into tbDatosRutas (fcNombreDR, fdoStartLocationLatDR, fdoStartLocationLngDR, fdoEndLocationLatDR, fdoEndLocationLngDR)values('%s', %f, %f, %f, %f) returning fiIdDR; ",
//				fcNombreDR,
//				fdoStartLocationLatDR,
//				fdoStartLocationLngDR, 
//				fdoEndLocationLatDR,
//				fdoEndLocationLngDR);
//	}
//	public void setFcNombreDR(String fcNombreDR) {
//		this.fcNombreDR = fcNombreDR;
//	}
	public void setFdoStartLocationLatDR(double fdoStartLocationLatDR) {
		this.fdoStartLocationLatDR = fdoStartLocationLatDR;
	}
	public void setFdoStartLocationLngDR(double fdoStartLocationLngDR) {
		this.fdoStartLocationLngDR = fdoStartLocationLngDR;
	}
	public void setFdoEndLocationLatDR(double fdoEndLocationLatDR) {
		this.fdoEndLocationLatDR = fdoEndLocationLatDR;
	}
	public void setFdoEndLocationLngDR(double fdoEndLocationLngDR) {
		this.fdoEndLocationLngDR = fdoEndLocationLngDR;
	}	
//	public String getFcNombreDR() {
//		return fcNombreDR;
//	}
	public double getFdoStartLocationLatDR() {
		return fdoStartLocationLatDR;
	}
	public double getFdoStartLocationLngDR() {
		return fdoStartLocationLngDR;
	}
	public double getFdoEndLocationLatDR() {
		return fdoEndLocationLatDR;
	}
	public double getFdoEndLocationLngDR() {
		return fdoEndLocationLngDR;
	}



//	String fcNombreDR; 
	double fdoStartLocationLatDR; 
	double fdoStartLocationLngDR; 
	double fdoEndLocationLatDR; 
	double fdoEndLocationLngDR;
	String qryStringInsert;	
}
