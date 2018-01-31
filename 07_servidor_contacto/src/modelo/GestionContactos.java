package modelo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Contacto;



public class GestionContactos  {

	private String nombre;
	private String email;
	private int telefono;
	
	
	Statement st;
	String sql;
	
	
		
	
	public void alta(String nombre,String email,int telefono)  {
		try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "root"))
 			{
		/*	
			this.st=cn.createStatement();
			sql="Insert into contactos(nombre,email,telefono) values('" +  nombre + "','" + email + "'," +telefono+")";
			st.execute(sql);
		*/
			//consultas preparadas
			String sql="Insert into contactos(nombre,email,telefono) values(?,?,?)";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1,nombre);
			ps.setString(2,email);
			ps.setInt(3, telefono);
			ps.execute();//en este caso no se pasa como parametro la query en el string
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}/*finally {
			try {
				if (cn!=null){ //Para que no genera un NullPointerException 
				cn.close();
				}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	
	public void eliminar(String email) {
		try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "root");) {
			
			this.st=cn.createStatement();
			
			sql="delete from contactos where email='" + email +"'";
			st.execute(sql);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}/*finally {
			try {
				if (cn!=null){ //Para que no genera un NullPointerException 
				cn.close();
				}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
		
	}
	

	public List<Contacto> buscar(String nombre) {
		ArrayList<Contacto> contacto=null;
		try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "root");) {
			
			this.st=cn.createStatement();
			sql="select * from contactos where nombre like '%"+ nombre +"%'";
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				contacto.add(new Contacto(rs.getString("nombre"),rs.getString("email"),rs.getInt("telefono")));
				
			}
						
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
			return contacto;
	}
	
	
	public List<Contacto> recuperarTodos() {
		//DEvolvemos un interfaz List para "desacoplar" capas 
	  //ArrayList implementa la interfaz, por lo que se puede devolver para "aislar" el tipo de estructura que se devuelve o para
		//que si se quiere cambiar a otra estructura sea más facil cambiarlo en el codigo de llamada
		
		ArrayList<Contacto> contactos = new ArrayList<>();
		try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "root");) {
			
			this.st=cn.createStatement();
			
			String sql="select * from contactos";
			ResultSet rs=st.executeQuery(sql);
			//Statement st2=cn.createStatement();

			//st2.execute("insert into contactos(nombre, email, telefono) values('lolo','lolo@ff.es',8998)");//No da error, pero el result set no lo incluye			
				while (rs.next()) {
					contactos.add(new Contacto(rs.getString("nombre"),rs.getString("email"),rs.getInt("telefono")));
				}
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return contactos;
	}
	
	
	
}
