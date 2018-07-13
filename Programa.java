package bdd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Programa {
	
	private static ArrayList <BaseDatos> listaBD;
	
	public static void main(String[] args) throws IOException {
			
		listaBD = new ArrayList <BaseDatos>();
		cargarDatos();
		Interfaz interfaz = new Interfaz(listaBD);
		
	}
	
	private static void cargarDatos() {
		
		try {
			Scanner leer = new Scanner(new FileReader("Base de datos/datosBD.dat"));
			
			while(leer.hasNext()) {
				listaBD.add(new BaseDatos(leer.nextLine(), false));
			}
			
			for(int i = 0; i < listaBD.size(); i++) {
				
				String nombreBD = listaBD.get(i).getNombreBD();
				leer = new Scanner(new FileReader("Base de datos" + File.separator + nombreBD + File.separator + "tablas.dat"));
				int cont  = 0;
				while(leer.hasNext()) {
					
					String tabla = leer.nextLine();
					listaBD.get(i).agregarTabla(nombreBD, tabla,false);
					
					ArrayList <Tablas> listaTablas= listaBD.get(i).getlistTablas();
					
					Scanner leerAtrib = new Scanner(new FileReader("Base de datos" + File.separator + nombreBD + File.separator + tabla
							+ ".dat"));
					
					Scanner leerRegistro = new Scanner(new FileReader("Base de datos" + File.separator + nombreBD + File.separator + tabla
							+ ".txt"));
					
					boolean hayAtri = false;
					while(leerAtrib.hasNext()) {
						String nombreAtributo = leerAtrib.nextLine();
						listaTablas.get(cont).setAtributo(nombreAtributo, false);
						hayAtri = true;
					}
					
					if(hayAtri) {
					ArrayList <ArrayList <String>> registro = listaTablas.get(cont).getRegistros();
					
						while(leerRegistro.hasNext()) {
							for(int j = 0; j < registro.size();j++) {
								registro.get(j).add(leerRegistro.nextLine());
							}
						}
					}
					
					cont++;
					
					leerAtrib.close();
					leerRegistro.close();
				}
				
			}
			
			leer.close();
		} catch(FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
		} 
		
	}
	
	public static void vaciarDatos() {
		
		File fileContenido = new File("Base de datos");
		borrarTodo(fileContenido);
		fileContenido.delete();
		
		File fileNuevoContenido = new File("Base de datos" + File.separator);
		fileNuevoContenido.mkdirs();
		
		FileWriter archivoDatosBD = null;
		PrintWriter escribirDatos = null;
		FileWriter archivoDatosTablas = null;
		PrintWriter escribirTablas = null;
		try {
			archivoDatosBD = new FileWriter("Base de datos" + File.separator + "datosBD.dat");
			escribirDatos = new PrintWriter(archivoDatosBD);
			for(int i = 0; i < listaBD.size(); i++) {
				File rutaBaseDatos = new File("Base de datos" + File.separator + listaBD.get(i).nombreBD);
				rutaBaseDatos.mkdirs();
				archivoDatosTablas = new FileWriter("Base de datos" + File.separator + listaBD.get(i).nombreBD + File.separator
						+ "tablas.dat");
				archivoDatosTablas.close();
				escribirDatos.println(listaBD.get(i).nombreBD);
				
				ArrayList <Tablas> listaTablas = listaBD.get(i).getlistTablas();
				escribirTablas = new PrintWriter(new FileWriter("Base de datos" + File.separator + listaBD.get(i).nombreBD + File.separator
						+ "tablas.dat"));
				for(int k = 0; k < listaTablas.size(); k++) {
					String nombreTabla = listaTablas.get(k).getNombreTablas();
					archivoDatosTablas = new FileWriter("Base de datos" + File.separator + listaBD.get(i).nombreBD + File.separator
							+ nombreTabla + ".txt");
					archivoDatosTablas.close();
					archivoDatosTablas = new FileWriter("Base de datos" + File.separator + listaBD.get(i).nombreBD + File.separator
							+ nombreTabla + ".dat");
					archivoDatosTablas.close();
					escribirTablas.println(nombreTabla);
					ArrayList <ArrayList <String>> listaRegistro = listaTablas.get(k).getRegistros();
					System.out.println("Hola");
					PrintWriter escribirAtributos = new PrintWriter(new FileWriter("Base de datos" + File.separator + listaBD.get(i).nombreBD + File.separator
							+ nombreTabla + ".dat"));
					
					ArrayList <String> atributosAL = listaTablas.get(k).getAtributosNombresAL();
					System.out.println(atributosAL.size());
					for(int j = 0; j < atributosAL.size(); j++) {
						escribirAtributos.println(atributosAL.get(j));
					}
					
					escribirAtributos.close();
					
					PrintWriter escribirRegistros = new PrintWriter(new FileWriter("Base de datos" + File.separator + 
							listaBD.get(i).nombreBD + File.separator + nombreTabla + ".txt"));
					
					for(int j = 0; j < listaRegistro.get(0).size(); j++) {
						for(int l = 0; l < listaRegistro.size(); l++) {
							escribirRegistros.println(listaRegistro.get(l).get(j));
						}
					}
					escribirRegistros.close();
				}
				escribirTablas.close();
			}
			
			archivoDatosBD.close();
			escribirDatos.close();
			
		} catch(IOException e) {
			
		}
		
	}
	
	public static void borrarTodo(File file) {
		
		File[] contenido = file.listFiles();
		
		for(int i = 0; i < contenido.length; i++) {
			if(contenido[i].isDirectory()) {
				borrarTodo(contenido[i]);
				contenido[i].delete();
			} else {
				contenido[i].delete();
			}
		}
		
	}
	
}
