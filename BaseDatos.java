package bdd;

import java.util.ArrayList;

public class BaseDatos {
	
	private String nombre;
	private int num_tablas;
	private ArrayList <Tablas> listTablas;
	
	public BaseDatos(String nombre) {
		
		this.nombre = nombre;
		num_tablas = 0;
		listTablas = new ArrayList <Tablas>();
		
	}
	
	public void agregarTabla(String nombre) {
		
	}
	
	
}
