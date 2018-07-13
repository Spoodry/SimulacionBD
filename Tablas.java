package bdd;

import java.util.ArrayList;

public class Tablas extends BaseDatos{
	
	private String nombreTablas;
	private ArrayList <String> atributosNombresAL;
	private ArrayList <ArrayList <String>> registros;
	private int num_atributos;
	
	
	public Tablas(String nombre) {
		this.nombreTablas = nombre;
		atributosNombresAL = new ArrayList <String>();
		registros = new ArrayList <ArrayList <String>>();
		num_atributos = 0;
	}
	
	public String getNombreTablas() {
		return nombreTablas;
	}
	
	public int getNumAtributos() {
		return num_atributos;
	}
	
	public void setNumAtributos(int num_atributos) {
		this.num_atributos = num_atributos;
	}
	
	public void setAtributo(String nombre, boolean esNuevo) {
		
		atributosNombresAL.add(nombre);
		num_atributos++;
		ArrayList<String> registro = new ArrayList<String>();
		registros.add(registro);
		if(esNuevo)
			Datos.setDatosTablas(nombreBD, nombreTablas, nombre);
		
	}
	
	public ArrayList <String> getAtributosNombresAL() {
		return atributosNombresAL;
	}
	
	public ArrayList <ArrayList <String>> getRegistros() {
		return registros;
	}
	
}
