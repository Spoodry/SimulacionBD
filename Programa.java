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
	
	private static final int MENU_PRINCIPAL = 1;
	private static final int MENU_TABLAS = 3;
	private static final int MENU_TABLAS_PROPIEDADES = 4;
	private static final int MENU_REGISTROS = 5;
	private static final int MENU_REGISTROS_ACTUALIZAR = 6;
	private static final int MENU_CREAR_BASE_DE_DATOS = 7;
	private static ArrayList <BaseDatos> listaBD;
	
	public static void main(String[] args) throws IOException {
		
		final boolean estado;
		final String DATOS = "datos.txt";
		Scanner leer = null;
		FileWriter datos_archivo = null;
		PrintWriter escribir = null;
		
		try {
			leer = new Scanner(new FileReader(DATOS));
			datos_archivo = new FileWriter(DATOS, true);
			escribir = new PrintWriter(datos_archivo);
			
			estado = leer.nextBoolean();
			leer.close();
			
			listaBD = new ArrayList <BaseDatos>();
			
			Scanner scanner = new Scanner(System.in);
			
			if(estado) {
				menu(Programa.MENU_PRINCIPAL);
				cargarDatos();
				leer = new Scanner(new FileReader("Base de Datos/datosBD.dat"));
				ArrayList <String> baseDatos = new ArrayList <String>();
				
				while(leer.hasNext()) {
					baseDatos.add(leer.next());
				}
				
				Iterator <String> iterator = baseDatos.iterator();
				
				while(iterator.hasNext()) {
					System.out.println("\t" + iterator.next());
				}
				
			}
			
			else {
				menu(Programa.MENU_PRINCIPAL);
				System.out.println("\n\tNo hay bases de datos existentes");
				datos_archivo = new FileWriter(DATOS);
				escribir.println("true");
				escribir.close();
				File fileBD = new File("Base de Datos" + File.separator);
				fileBD.mkdirs();
				
			}
			
			System.out.print("\n¿Desea entrar a una base de datos (S/N)? ");
			
			String decision = scanner.next();
			
			if(decision.equalsIgnoreCase("S")) {
				System.out.print("\n¿A cual? ");
				
				String BaseDatos = scanner.next();
				
				leer = new Scanner(new FileReader("Base de Datos/" + BaseDatos + "/tablas.dat"));
				
				menu(Programa.MENU_TABLAS);
				
				System.out.println("\nBase de datos: " + BaseDatos);
				
				ArrayList <String> listaTablas = new ArrayList <String>();
				
				while(leer.hasNext()) {
					listaTablas.add(leer.next());
				}
				
				Iterator <String> iteratorTablas = listaTablas.iterator();
				
				System.out.print(iteratorTablas.hasNext() ? "" : "\n\tNo hay tablas creadas");
				
				while(iteratorTablas.hasNext()) {
					System.out.println(iteratorTablas.next());
				}
				
				
				
			}
			else {
				System.out.println("\n1) Crear nueva base de datos");
				System.out.println("2) Salir");
				System.out.print("\nOpción: ");
				
				try {
					
					int opcion = scanner.nextInt();
					
					switch(opcion) {
					case 1:
						menu(Programa.MENU_CREAR_BASE_DE_DATOS);
						
						String NBD = scanner.next();
						
						File fileNBD = new File("Base de Datos" + File.separator + NBD);
						fileNBD.mkdirs();
						
						FileWriter datosTablas = new FileWriter("Base de Datos/" + NBD + "/tablas.dat", true);
						datosTablas.close();
						
						datos_archivo = new FileWriter("Base de Datos/datosBD.dat", true);
						escribir = new PrintWriter(datos_archivo);
						
						escribir.println(NBD);
						
						System.out.println("\n\t¡¡¡Nueva base de datos creada!!!");
						
						break;
					case 2:
						System.exit(0);
						break;
					}
				
				} catch(Exception e) {
					
				}
				
			}
			
			scanner.close();
			
		} catch(IOException e) {
			
			System.out.println("No se pudo abrir el archivo");
			
		}
		finally {
			
			if(leer != null && datos_archivo != null) {
				leer.close();
				datos_archivo.close();
			}
			
		}
		
	}
	
	private static void menu(int opcion) {
		
		switch(opcion) {
			case MENU_PRINCIPAL:
				System.out.println("==================Sistema Gestor de Base de Datos==================");
				System.out.println("\nBases de datos:");
			break;
			case MENU_CREAR_BASE_DE_DATOS:
				System.out.println("=================Crear Base de Datos=================");
				System.out.print("\nNombre de la base de datos: ");
				break;
			case MENU_TABLAS:
				System.out.println("==================Tablas==================");
				break;
			case MENU_TABLAS_PROPIEDADES:
				System.out.println("==================Propiedades==================");
				System.out.println("\n1) Añadir atributo");
				System.out.println("2) Eliminar atributo");
				System.out.println("3) Salir");
				System.out.print("\nOpción: ");
			break;
			case MENU_REGISTROS:
				System.out.println("==================Registros==================");
				System.out.println("\nTablas");
				break;
			case MENU_REGISTROS_ACTUALIZAR:
				System.out.println("==================Actualizar==================");
				System.out.println("\n1) Insertar");
				System.out.println("2) Mostrar");
				System.out.println("3) Eliminar");
				System.out.println("4) Salir");
				System.out.print("\nOpción: ");
				break;
		}
	}
	
	private static void cargarDatos() {
		
		try {
			Scanner leer = new Scanner(new FileReader("Base de datos/datosBD.dat"));
			
			while(leer.hasNext()) {
				listaBD.add(new BaseDatos(leer.next()));
			}
			
			leer.close();
		} catch(FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
		} 
		
	}
	
}
