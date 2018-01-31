package principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import beans.Contacto;
import modelo.BusquedaContacto;
import principal.adaptadores.AdaptadorTabla;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class JVentanaBuscador extends JFrame {

	private JPanel contentPane;
	private JTextField txtContacto;
	private JTable table;
	private JTable tablaContactos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JVentanaBuscador frame = new JVentanaBuscador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	/**
	 * Create the frame.
	 */
	public JVentanaBuscador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtContacto = new JTextField();
		txtContacto.setBounds(23, 35, 183, 20);
		txtContacto.setColumns(10);
		contentPane.add(txtContacto);
		
		tablaContactos = new JTable();
		tablaContactos.setBounds(216, 11, 197, 219);
		//contentPane.add(tablaContactos);
		
		JScrollPane scrollPane = new JScrollPane();
	       scrollPane.setBounds(210, 35, 214, 157);
	       contentPane.add(scrollPane);
		
	       scrollPane.setViewportView(tablaContactos);
	       	
	
	     //carga de la tabla
	       BusquedaContacto gcontactos=new BusquedaContacto("","",0);
		JButton btnBuscarContacto = new JButton("Buscar Contacto");
		btnBuscarContacto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Contacto> contactos = gcontactos.buscarContacto(txtContacto.getText());
				 AdaptadorTabla adp=new AdaptadorTabla(contactos);
				 tablaContactos.setModel(adp);
			}
		});
		btnBuscarContacto.setBounds(23, 81, 145, 23);
		contentPane.add(btnBuscarContacto);
	
		
	       
	       //creamos objeto adaptador 
	      
 
        
		
		/*
		table = new JTable();
		table.setBounds(237, 54, 1, 1);
		contentPane.add(table);*/
	
	}
}
