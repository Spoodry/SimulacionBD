package bdd;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Datos {
	
	public Datos() {
		
	}
	
	public static void setBaseDatos(String carpeta) {
		try {
			File fileNBD = new File("Base de Datos" + File.separator + carpeta + File.separator);
			fileNBD.mkdirs();
			FileWriter datosTablas = new FileWriter("Base de Datos/" + carpeta + "/tablas.dat", true);
			datosTablas.close();
			
			datosTablas = new FileWriter("Base de Datos/datosBD.dat", true);
			PrintWriter escribir = new PrintWriter(datosTablas);
			
			escribir.println(carpeta);
			
			datosTablas.close();
			escribir.close();
			
		} catch(IOException e) {
			
		}
	}
	
	public static void setDatosTablas(String carpeta, String archivo, String nombre) {
		try {
			FileWriter archivo_escritura = new FileWriter("Base de Datos" + File.separator + carpeta + File.separator +
					archivo, true);
			PrintWriter escribir = new PrintWriter(archivo_escritura);
	
			escribir.println(nombre);
			
			archivo_escritura.close();
			escribir.close();
			
		} catch(IOException e) {
			
		}
	}
	
	public static void setTablas(String carpeta, String nombreTabla) {
		try {
			FileWriter archivo_escritura = new FileWriter("Base de Datos" + File.separator + carpeta + File.separator +
					"tablas.dat", true);
			FileWriter archivoTablas = new FileWriter("Base de Datos" + File.separator + carpeta + File.separator + nombreTabla + ".dat",
					true);
			FileWriter archivoTablasRegistros = new FileWriter("Base de Datos" + File.separator + carpeta + File.separator + nombreTabla + ".txt",
					true);
			archivoTablas.close();
			archivoTablasRegistros.close();
			PrintWriter escribir = new PrintWriter(archivo_escritura);
	
			escribir.println(nombreTabla);
			
			archivo_escritura.close();
			escribir.close();
			
		} catch(IOException e) {
			
		}
	}
	
}
