package principal;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import beans.Pedidos;
import modelo.GestionPedidos;
import principal.adaptadores.AdaptadorTabla;

public class JCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtProducto;
	private JTextField txtUnidades;
	private JTable tbPedidos;
	List<Pedidos>pedidos=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCliente frame = new JCliente();
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
	public JCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tbPedidos = new JTable();
		tbPedidos.setBounds(189, 40, 218, 168);
		contentPane.add(tbPedidos);
		 
		
		JButton btnBuscarPedidos = new JButton("Buscar Pedidos");
		btnBuscarPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocalDateTime fecha=null;
				GestionPedidos pedido = new GestionPedidos("",0,"",fecha);
				pedidos=pedido.recibirPedidos(txtProducto.getText(),Integer.parseInt(txtUnidades.getText()));
				AdaptadorTabla adp=new AdaptadorTabla(pedidos);
			     tbPedidos.setModel(adp);
			}
		});
		btnBuscarPedidos.setBounds(30, 160, 125, 23);
		contentPane.add(btnBuscarPedidos);
		
		txtProducto = new JTextField();
		txtProducto.setBounds(30, 37, 100, 20);
		contentPane.add(txtProducto);
		txtProducto.setColumns(10);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(22, 12, 79, 14);
		contentPane.add(lblProducto);
		
		JLabel lblUnidades = new JLabel("Unidades");
		lblUnidades.setBounds(22, 66, 93, 14);
		contentPane.add(lblUnidades);
		
		txtUnidades = new JTextField();
		txtUnidades.setBounds(37, 91, 93, 20);
		contentPane.add(txtUnidades);
		txtUnidades.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(176, 24, 248, 211);
		contentPane.add(scrollPane);
		
		
		
		JButton btnNuevoPedido = new JButton("Nuevo Pedido");
		btnNuevoPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPedidos jpedidos=JPedidos.nuevaVentana();
				if (jpedidos!=null) {
					jpedidos.setEnabled(true);
					jpedidos.setVisible(true);
				}
			}
		});
		btnNuevoPedido.setBounds(34, 212, 132, 23);
		contentPane.add(btnNuevoPedido);
	
		
		
	}
}
