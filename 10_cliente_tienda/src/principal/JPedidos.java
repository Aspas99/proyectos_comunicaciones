package principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.GestionPedidos;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class JPedidos extends JFrame {

	private static JPedidos jpedidos=null;
	private JPanel contentPane;
	private JTextField txtProducto;
	private JTextField txtUnidades;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPedidos frame = new JPedidos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	private JPedidos() {
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(10, 29, 72, 14);
		contentPane.add(lblProducto);
		
		txtProducto = new JTextField();
		txtProducto.setBounds(96, 26, 86, 20);
		contentPane.add(txtProducto);
		txtProducto.setColumns(10);
		
		JLabel lblUnidades = new JLabel("Unidades");
		lblUnidades.setBounds(10, 68, 82, 14);
		contentPane.add(lblUnidades);
		
		txtUnidades = new JTextField();
		txtUnidades.setBounds(96, 65, 86, 20);
		contentPane.add(txtUnidades);
		txtUnidades.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocalDateTime fecha = LocalDateTime.now();
				//DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				//fecha=LocalDateTime.parse(fecha.toString(), df);
				GestionPedidos pedido=new GestionPedidos(txtProducto.getText(), Integer.parseInt(txtUnidades.getText()), 
						"", null);
				pedido.enviarPedido(txtProducto.getText(), Integer.parseInt(txtUnidades.getText()),pedido.getIpcliente() , fecha);
						
			}
		});
		btnGuardar.setBounds(190, 210, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jpedidos.dispose();
			}
		});
		btnSalir.setBounds(320, 25, 89, 23);
		contentPane.add(btnSalir);
	}
	
	public static JPedidos nuevaVentana() {
		
		if (jpedidos==null) {
			jpedidos=new JPedidos();
			
		}
		
		return jpedidos;
	}
}
