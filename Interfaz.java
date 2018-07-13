package bdd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Interfaz {
	
	public Interfaz(ArrayList <BaseDatos> BDAL) {
		Ventana frame = new Ventana(BDAL);
		frame.setVisible(true);
	}
	
	
}

class Ventana extends JFrame {
	
	private ArrayList <BaseDatos> BDAL;
	
	public Ventana(ArrayList <BaseDatos> BDAL) {
		setSize(800, 600);
		setTitle("Sistema Gestor de Base de Datos");
		setLocationRelativeTo(null);
		this.BDAL = BDAL;
		
		laminaInicio lamina = new laminaInicio();
		
		add(lamina);
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
	}
	
	private class laminaInicio extends JPanel {
		
		public laminaInicio() {
			setLayout(new BorderLayout());
			setBackground(Color.WHITE);
			JLabel titulo = new JLabel("Sistema Gestor de Base de Datos");
			titulo.setFont(new Font("Century Gothic", Font.PLAIN, 20));
			add(titulo,BorderLayout.NORTH);
			
			JPanel laminaBD = new JPanel();
			laminaBD.setLayout(new GridLayout(BDAL.size() + 1, 1));
			JLabel label1 = new JLabel("Bases de datos: ");
			label1.setFont(new Font("Century Gothic", Font.PLAIN, 16));
			laminaBD.add(label1);
			add(laminaBD, BorderLayout.CENTER);
			
			Iterator <BaseDatos> iterator = BDAL.iterator();
			
			while(iterator.hasNext()) {
				JPanel laminaBotones = new JPanel();
				JButton botonBD = new JButton(iterator.next().getNombreBD());
				
				botonBD.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						laminaInicio.this.invalidate();
						laminaInicio.this.setVisible(false);
						laminaInicio.this.removeAll();
						Ventana.this.remove(laminaInicio.this);
						Ventana.this.add(new laminaTablas(botonBD.getActionCommand()));
					}
					
				});
				
				laminaBotones.add(botonBD);
				laminaBD.add(laminaBotones);
			}
			
			JPanel laminaInferior = new JPanel();
			
			JButton botonCrear = new JButton("Crear BD");
			JButton botonEliminarBD = new JButton("Eliminar BD");
			JButton botonSalir = new JButton("Salir");
			
			laminaInferior.add(botonCrear);
			laminaInferior.add(botonEliminarBD);
			laminaInferior.add(botonSalir);
			add(laminaInferior, BorderLayout.SOUTH);
			
			botonCrear.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					laminaInicio.this.invalidate();
					laminaInicio.this.setVisible(false);
					laminaInicio.this.removeAll();
					Ventana.this.remove(laminaInicio.this);
					Ventana.this.add(new laminaCrearBD());
				}
				
			});
			
			botonEliminarBD.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String baseAEliminar = JOptionPane.showInputDialog(Ventana.this, "Ingrese nombre de BD", "Eliminar BD", 
							JOptionPane.INFORMATION_MESSAGE);
					if(baseAEliminar != null) {
						if(baseAEliminar.equals("")) {
							JOptionPane.showMessageDialog(Ventana.this, "Ingrese nombre valido", "Aviso", JOptionPane.WARNING_MESSAGE);
						} else {
							for(int i = 0; i < BDAL.size(); i++) {
								if(BDAL.get(i).nombreBD.equals(baseAEliminar)) {
									BDAL.remove(i);
									JOptionPane.showMessageDialog(Ventana.this, "Base de datos borrada", "Aviso", 
											JOptionPane.INFORMATION_MESSAGE);
									break;
								}
							}
						}
					}
					laminaInicio.this.invalidate();
					laminaInicio.this.setVisible(false);
					laminaInicio.this.removeAll();
					Ventana.this.remove(laminaInicio.this);
					Ventana.this.add(new laminaInicio());
				}
				
			});
			
			botonSalir.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int opcionSalir = JOptionPane.showConfirmDialog(Ventana.this, "¿Desea Salir?", "Salir", JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
	
					if(opcionSalir == 0) {
						Programa.vaciarDatos();
						System.exit(0);
					}
				}
				
			});
		}
	}
	private class laminaCrearBD extends JPanel{
		
		public laminaCrearBD() {
			setLayout(new BorderLayout());
			
			JLabel titulo = new JLabel("Crear base de datos");
			
			titulo.setFont(new Font("Century Gothic", Font.PLAIN, 20));
			
			add(titulo, BorderLayout.NORTH);
			
			JPanel lamina = new JPanel();
			
			lamina.add(new JLabel("Nombre: "));
			
			JTextField nombreTF = new JTextField(20);
			
			lamina.add(nombreTF);
			
			add(lamina,BorderLayout.CENTER);
			
			JPanel laminaInferior = new JPanel();
			
			JButton botonCrear = new JButton("Crear");
			
			JButton botonCancelar = new JButton("Cancelar");
			
			botonCrear.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(nombreTF.getText().equals(""))
					{
						JOptionPane.showMessageDialog(Ventana.this, "Por favor, ingresar el nombre", "Aviso", 
								JOptionPane.WARNING_MESSAGE);
					} else {
						BDAL.add(new BaseDatos(nombreTF.getText(), true));
						JOptionPane.showMessageDialog(Ventana.this, "¡¡¡Nueva base de datos creada!!!", "Aviso", 
								JOptionPane.INFORMATION_MESSAGE);
						laminaCrearBD.this.invalidate();
						laminaCrearBD.this.setVisible(false);
						laminaCrearBD.this.removeAll();
						Ventana.this.remove(laminaCrearBD.this);
						Ventana.this.add(new laminaInicio());
					}
				}
				
			});
			
			botonCancelar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					laminaCrearBD.this.invalidate();
					laminaCrearBD.this.setVisible(false);
					laminaCrearBD.this.removeAll();
					Ventana.this.remove(laminaCrearBD.this);
					Ventana.this.add(new laminaInicio());
				}
				
			});
			
			laminaInferior.add(botonCrear);
			
			laminaInferior.add(botonCancelar);
			
			add(laminaInferior, BorderLayout.SOUTH);
			
		}
		
	}
	
	private class laminaTablas extends JPanel{
		
		public laminaTablas(String nombreBD) {
			
			setLayout(new BorderLayout());
			
			JLabel titulo = new JLabel("Base de Datos: " + nombreBD);
			
			titulo.setFont(new Font("Century Gothic", Font.PLAIN, 20));
			
			add(titulo, BorderLayout.NORTH);
			
			JPanel laminaContenido = new JPanel();
			
			laminaContenido.setLayout(new BorderLayout());
			
			BaseDatos bd = obtenerBaseActual(nombreBD);
			
			System.out.println(bd);
			JPanel laminaTablas = new JPanel(new GridLayout(bd.getNumTablas() + 1, 1));
	
			JLabel labelTablas = new JLabel("Tablas: ");
			JPanel laminaLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			labelTablas.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			laminaLabel.add(labelTablas);
			laminaTablas.add(laminaLabel, BorderLayout.NORTH);
			
			for(int i = 0; i < bd.getNumTablas(); i++) {
				JPanel laminaBotonesTabla = new JPanel();
				JButton botonTabla = new JButton(bd.getlistTablas().get(i).getNombreTablas());
				
				botonTabla.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						laminaTablas.this.repaint();
						laminaTablas.this.invalidate();
						laminaTablas.this.setVisible(false);
						laminaTablas.this.removeAll();
						Ventana.this.remove(laminaTablas.this);
						Ventana.this.add(new laminaRegistros(nombreBD, botonTabla.getActionCommand()));
					}
					
				});
				
				laminaBotonesTabla.add(botonTabla);
				laminaTablas.add(laminaBotonesTabla);
			}
			
			JPanel laminaBotones = new JPanel();
			
			JButton botonCrear = new JButton("Crear Tabla");
			JButton botonEliminar = new JButton("Eliminar Tabla");
			JButton botonSalirTablas = new JButton("Salir de BD");
			
			botonCrear.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String nombreTabla = JOptionPane.showInputDialog(Ventana.this, 
							"Ingresar nombre de la tabla", "Crear Tabla", JOptionPane.INFORMATION_MESSAGE);
					if(nombreTabla != null) {
						if(nombreTabla.equals("")) {
							JOptionPane.showMessageDialog(Ventana.this, "Ingresar un nombre a la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
						} else {
							obtenerBaseActual(nombreBD).agregarTabla(nombreBD, nombreTabla, true);
							JOptionPane.showMessageDialog(Ventana.this, "¡Nueva tabla creada!", "Información", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					laminaTablas.this.repaint();
					laminaTablas.this.invalidate();
					laminaTablas.this.setVisible(false);
					laminaTablas.this.removeAll();
					Ventana.this.remove(laminaTablas.this);
					Ventana.this.add(new laminaTablas(nombreBD));
				}
				
			});
			
			botonEliminar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String nombreTabla = JOptionPane.showInputDialog(Ventana.this, "Ingresar tabla a eliminar", "Eliminar Tabla", 
							JOptionPane.INFORMATION_MESSAGE);
					
					if(nombreTabla.equals("")) {
						JOptionPane.showMessageDialog(Ventana.this, "Ingresar un nombre a la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
					} else {
						ArrayList <Tablas> listTablas = bd.getlistTablas();
						
						for(int i = 0; i < listTablas.size(); i++) {
							if(listTablas.get(i).getNombreTablas().equals(nombreTabla)) {
								listTablas.remove(i);
								JOptionPane.showMessageDialog(Ventana.this,"La tabla se ha borrado", "Tabla borrada", 
										JOptionPane.INFORMATION_MESSAGE);
								bd.setNumTablas(bd.getNumTablas() - 1);
								break;
							} else {
								if(i == listTablas.size() - 1)
									JOptionPane.showMessageDialog(Ventana.this, "No se borró ninguna tabla", "Tabla no borrada", 
											JOptionPane.WARNING_MESSAGE);
							}
						}
					}
					
					laminaTablas.this.repaint();
					laminaTablas.this.invalidate();
					laminaTablas.this.setVisible(false);
					laminaTablas.this.removeAll();
					Ventana.this.remove(laminaTablas.this);
					Ventana.this.add(new laminaTablas(nombreBD));
				}
				
			});
			
			botonSalirTablas.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					laminaTablas.this.repaint();
					laminaTablas.this.invalidate();
					laminaTablas.this.setVisible(false);
					laminaTablas.this.removeAll();
					Ventana.this.remove(laminaTablas.this);
					Ventana.this.add(new laminaInicio());
				}
				
			});
			
			laminaBotones.add(botonCrear);
			laminaBotones.add(botonEliminar);
			laminaBotones.add(botonSalirTablas);
			
			laminaContenido.add(laminaTablas, BorderLayout.NORTH);
			laminaContenido.add(laminaBotones, BorderLayout.SOUTH);
			
			add(laminaContenido, BorderLayout.CENTER);
			
			
		}
		
	}
	
	private class laminaRegistros extends JPanel{
		
		public laminaRegistros(String nombreBD, String nombreTabla) {
			
			setLayout(new BorderLayout());
			
			JLabel titulo = new JLabel("Tabla: " + nombreTabla);
			
			titulo.setFont(new Font("Century Gothic", Font.PLAIN, 20));
			
			add(titulo, BorderLayout.NORTH);
			
			JPanel lamina = new JPanel(new BorderLayout());
			
			JPanel laminaTabla = new JPanel(new BorderLayout());
			
			DefaultTableModel model = new DefaultTableModel();
			JTable table = new JTable(model);
			JScrollPane scrollPane = new JScrollPane(table);
			
			Tablas tabla = obtenerTablaActual(obtenerBaseActual(nombreBD), nombreTabla);
			
			for(int i = 0; i < tabla.getNumAtributos(); i++) {
				model.addColumn(tabla.getAtributosNombresAL().get(i));
			}
			
			ArrayList <ArrayList <String>> registros = tabla.getRegistros();
			
			try {
				Object[] row = new Object[registros.size()];
				for(int i = 0; i < registros.get(0).size(); i++) {
					for(int k = 0; k < registros.size(); k++) {
						row[k] = registros.get(k).get(i);
					}
					model.addRow(row);
				}
			} catch(Exception e) {
				
			}
			laminaTabla.add(scrollPane);
			
			JPanel laminaBtnA = new JPanel();
			
			JButton botonAtributos = new JButton("Atributos");
			JButton botonNuRegr = new JButton("Nuevo Registro");
			JButton botonElmrRegr = new JButton("Eliminar Registro");
			JButton botonRegresar = new JButton("Regresar");
			
			botonAtributos.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					laminaRegistros.this.repaint();
					laminaRegistros.this.invalidate();
					laminaRegistros.this.setVisible(false);
					laminaRegistros.this.removeAll();
					Ventana.this.remove(laminaRegistros.this);
					Ventana.this.add(new laminaAtributos(nombreBD, nombreTabla));
				}
				
			});
			
			botonNuRegr.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					for(int i = 0; i < tabla.getNumAtributos();i++) {
						ArrayList <String>  registroAtrib = registros.get(i);
						registroAtrib.add(JOptionPane.showInputDialog(Ventana.this, "Ingresar " + tabla.getAtributosNombresAL().get(i), 
								"Ingresar registro", JOptionPane.INFORMATION_MESSAGE));
					}
					laminaRegistros.this.repaint();
					laminaRegistros.this.invalidate();
					laminaRegistros.this.setVisible(false);
					laminaRegistros.this.removeAll();
					Ventana.this.remove(laminaRegistros.this);
					Ventana.this.add(new laminaRegistros(nombreBD, nombreTabla));
				}
				
			});
			
			botonElmrRegr.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String registroEliminar = JOptionPane.showInputDialog(Ventana.this, "Ingresar el valor del registro: (El criterio es el primer atributo)"
							, "Eliminar registro", JOptionPane.INFORMATION_MESSAGE);
					
					if(registroEliminar != null) {
						if(registroEliminar.equals("")) {
							JOptionPane.showMessageDialog(Ventana.this, "Ingrese el valor", "Aviso", JOptionPane.WARNING_MESSAGE);
						} else {
							
							int posicionEliminar = 0;
							for(int i = 0; i < registros.size(); i++) {
								if(i == 0) {
									for(int k = 0; k < registros.get(i).size(); k++) {
										if(registros.get(i).get(k).equals(registroEliminar)) {
											registros.get(i).remove(k);
											posicionEliminar = k;
										}
									}
								} else {
									registros.get(i).remove(posicionEliminar);
								}
							}
							
						}
					}
					
					laminaRegistros.this.repaint();
					laminaRegistros.this.invalidate();
					laminaRegistros.this.setVisible(false);
					laminaRegistros.this.removeAll();
					Ventana.this.remove(laminaRegistros.this);
					Ventana.this.add(new laminaRegistros(nombreBD, nombreTabla));
					
				}
				
			});
			
			botonRegresar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					laminaRegistros.this.repaint();
					laminaRegistros.this.invalidate();
					laminaRegistros.this.setVisible(false);
					laminaRegistros.this.removeAll();
					Ventana.this.remove(laminaRegistros.this);
					Ventana.this.add(new laminaTablas(nombreBD));
				}
				
			});
			
			laminaBtnA.add(botonAtributos);
			laminaBtnA.add(botonNuRegr);
			laminaBtnA.add(botonElmrRegr);
			laminaBtnA.add(botonRegresar);
			
			lamina.add(laminaTabla, BorderLayout.NORTH);
			lamina.add(laminaBtnA, BorderLayout.SOUTH);
			
			add(lamina);
			
		}
		
	}
	
	private class laminaAtributos extends JPanel{
		
		public laminaAtributos(String nombreBD, String nombreTabla) {
			setLayout(new BorderLayout());
			
			JLabel titulo = new JLabel("Tabla: " + nombreTabla);
			titulo.setFont(new Font("Century Gothic", Font.PLAIN, 20));
			add(titulo, BorderLayout.NORTH);
			
			JPanel lamina = new JPanel(new BorderLayout());
			
			JPanel laminalabels = new JPanel();
			
			Tablas tabla = obtenerTablaActual(obtenerBaseActual(nombreBD), nombreTabla);
			
			ArrayList <String> listAtributos = tabla.getAtributosNombresAL();
			
			laminalabels.setLayout(new GridLayout(listAtributos.size(), 1));
			
			for(int i = 0; i < listAtributos.size(); i++) {
				JPanel laminaLabelsbien = new JPanel();
				JLabel atributolabel = new JLabel(listAtributos.get(i));
				laminaLabelsbien.add(atributolabel);
				laminalabels.add(laminaLabelsbien);
			}
			
			JPanel laminaBotones = new JPanel();
			
			JButton botonCrear = new JButton("Crear Atributo");
			JButton botonEliminar = new JButton("EliminarAtributo");
			JButton botonRegresar = new JButton("Regresar");
			
			botonCrear.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String nombreAtributo = JOptionPane.showInputDialog(Ventana.this, "Ingresar nombre de atributo", "Crear Atributo", 
							JOptionPane.INFORMATION_MESSAGE);
					if(nombreAtributo.equals("")) {
						JOptionPane.showMessageDialog(Ventana.this, "Ingresar un nombre por favor", "Aviso", JOptionPane.WARNING_MESSAGE);
					} else {
						tabla.setAtributo(nombreAtributo, true);
						JOptionPane.showMessageDialog(Ventana.this, "¡Atributo creado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					}
					laminaAtributos.this.repaint();
					laminaAtributos.this.invalidate();
					laminaAtributos.this.setVisible(false);
					laminaAtributos.this.removeAll();
					Ventana.this.remove(laminaAtributos.this);
					Ventana.this.add(new laminaAtributos(nombreBD, nombreTabla));
				}
				
			});
			
			botonEliminar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String nombreAtributo = JOptionPane.showInputDialog(Ventana.this, "Ingresar nombre de atributo a eliminar", 
							"Eliminar Atributo", JOptionPane.INFORMATION_MESSAGE);
					
					if(nombreAtributo.equals("")) {
						JOptionPane.showMessageDialog(Ventana.this, "Ingresar un nombre por favor", "Aviso", JOptionPane.WARNING_MESSAGE);
					} else {
						for(int i = 0; i < listAtributos.size(); i++) {
							if(listAtributos.get(i).equals(nombreAtributo)) {
								listAtributos.remove(i);
								ArrayList <ArrayList <String>> registros = tabla.getRegistros();
								registros.remove(i);
								tabla.setNumAtributos(tabla.getNumAtributos() - 1);
							}
						}
						JOptionPane.showMessageDialog(Ventana.this, "Atributo borrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					}
					
					laminaAtributos.this.repaint();
					laminaAtributos.this.invalidate();
					laminaAtributos.this.setVisible(false);
					laminaAtributos.this.removeAll();
					Ventana.this.remove(laminaAtributos.this);
					Ventana.this.add(new laminaAtributos(nombreBD, nombreTabla));
				}
				
			});
			
			botonRegresar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					laminaAtributos.this.repaint();
					laminaAtributos.this.invalidate();
					laminaAtributos.this.setVisible(false);
					laminaAtributos.this.removeAll();
					Ventana.this.remove(laminaAtributos.this);
					Ventana.this.add(new laminaRegistros(nombreBD, nombreTabla));
				}
				
			});
			
			laminaBotones.add(botonCrear);
			laminaBotones.add(botonEliminar);			
			laminaBotones.add(botonRegresar);
			
			lamina.add(laminalabels, BorderLayout.NORTH);
			lamina.add(laminaBotones, BorderLayout.SOUTH);
			
			add(lamina, BorderLayout.CENTER);
		}
		
	}
	
	private BaseDatos obtenerBaseActual(String nombre) {
		
		for(int i = 0; i < BDAL.size(); i++) {
			if(BDAL.get(i).getNombreBD().equals(nombre)) {
				return BDAL.get(i);
			}
		}
	
		return null;
		
	}
	
	private Tablas obtenerTablaActual(BaseDatos BD, String tabla) {
		
		ArrayList <Tablas> listaTablas = BD.getlistTablas();
		
		for(int i = 0; i < listaTablas.size();i++) {
			
			if(listaTablas.get(i).getNombreTablas().equals(tabla)) {
				return listaTablas.get(i);
			}
			
		}
		
		return null;
	}
	
}
