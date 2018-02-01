package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import beans.Pedidos;

public class GestionTienda extends Pedidos {

	
	public GestionTienda(String producto, int unidades, String ipcliente, LocalDateTime fecha) {
		super(producto, unidades, ipcliente, fecha);
		// TODO Auto-generated constructor stub
	}

	public void crearPedido(String producto,int unidades,String ipCliente,LocalDateTime fecha) {
		java.sql.Date fechaSql=(Date) java.sql.Date.from(fecha.toInstant(null));
		
		try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tiendas", "root", "root"))
		{
			String sql="Insert into pedidos(producto,unidades,ipCliente,fecha) values(?,?,?,?)";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1,producto);
			ps.setInt(2,unidades);
			ps.setString(3, ipCliente);
			ps.setDate(4, fechaSql);
			ps.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 
	}
	
	public List<Pedidos>devolverPedido(String producto, int unidades) {
		ArrayList<Pedidos> pedidos=new ArrayList<>();
		Pedidos pedido=null;
		LocalDateTime fecha=null;
		
		try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tiendas", "root", "root"))
		{
			String sql="select producto,unidades,ipCliente,fecha from pedidos where producto=='" + producto +
					"' and unidades=='" + unidades + "'" ;
			Statement st = cn.createStatement();
			ResultSet rs=st.executeQuery(sql);
		
			while (rs.next()) {
					
				pedido = new Pedidos(rs.getString("producto"),rs.getInt("unidades"),rs.getString("ipCliente"),fecha);
				pedidos.add(pedido);
				
			}
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return pedidos;
		}
}
