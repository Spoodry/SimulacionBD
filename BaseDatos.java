package bdd;

import java.util.ArrayList;

public class BaseDatos {
	
	protected String nombreBD;
	private int num_tablas;
	private ArrayList <Tablas> listTablas;
	
	public BaseDatos() {
	
	}
	
	public BaseDatos(String nombre, boolean esNueva) {
		
		this.nombreBD = nombre;
		num_tablas = 0;
		listTablas = new ArrayList <Tablas>();
		
		if(esNueva) {
			Datos.setBaseDatos(nombre);
		}
		
	}
	
	public void setNumTablas(int num_tablas) {
		this.num_tablas = num_tablas;
	}
	
	public String getNombreBD() {
		return nombreBD;
	}
	
	public ArrayList <Tablas> getlistTablas() {
		return listTablas;
	}
	
	public int getNumTablas() {
		return num_tablas;
	}
	
	public void agregarTabla(String tabla, String nombre, boolean esNueva) {
		listTablas.add(new Tablas(nombre));
		num_tablas++;
		if(esNueva)
			Datos.setTablas(tabla, nombre);
	}
	
	
}
