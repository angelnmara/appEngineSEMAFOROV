package com.lamarrulla.database;

import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lamarrulla.model.TbCredenciales;
import com.lamarrulla.model.TbDatosGeneraRutas;

public class Selects extends DbAcces {	
	
	List<TbDatosGeneraRutas> listDatosGeneraRutas;
	List<TbCredenciales> listCredenciales;	
	
	public List<TbDatosGeneraRutas> getListDatosGeneraRutas() {
		return listDatosGeneraRutas;
	}

	public List<TbCredenciales> getListCredenciales() {
		return listCredenciales;
	}

	public void selectCredenciales() {
		try {
			listCredenciales = new ArrayList<TbCredenciales>();
			connectDatabase();
			setStrQuery("select * from tbCredenciales");
			execQry();
			if (rs != null) {
				boolean isL = false;
				while (!isL) {
					TbCredenciales tbc = new TbCredenciales(rs.getString(2));
					listCredenciales.add(tbc);
					if (rs.isLast()) {
						isL = true;
					} else {
						rs.next();
					}					
				}
			}
		}catch(SQLException ex) {
			System.out.println("Ocurrio un error en select credenciales " + ex.getMessage());
		}		
	}
	
	public void selectGeneraDatosForRutas() {
		try {
			listDatosGeneraRutas = new ArrayList<TbDatosGeneraRutas>();
			connectDatabase();
			setStrQuery("select * from tbDatosGeneraRutas");
			execQry();			
			if (rs != null) {
				boolean isL = false;
				while (!isL) {
					TbDatosGeneraRutas dgr = new TbDatosGeneraRutas(rs.getString(2),
							rs.getBigDecimal(3).setScale(7, RoundingMode.HALF_UP),
							rs.getBigDecimal(4).setScale(14, RoundingMode.HALF_UP),
							rs.getBigDecimal(5).setScale(7, RoundingMode.HALF_UP),
							rs.getBigDecimal(6).setScale(14, RoundingMode.HALF_UP), rs.getString(7), rs.getString(8));
					listDatosGeneraRutas.add(dgr);
					if (rs.isLast()) {
						isL = true;
					} else {
						rs.next();
					}
					;
					// System.out.println();
				}
			}
			disconnectDatabase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
