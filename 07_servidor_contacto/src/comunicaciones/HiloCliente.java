package comunicaciones;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import beans.Contacto;
import modelo.GestionContactos;

public class HiloCliente implements Runnable {

	private Socket sc;
	public HiloCliente(Socket sc) {
		this.sc=sc;
	}
	@Override
	public void run() {
		try {
			InputStream is=sc.getInputStream();
			OutputStream os=sc.getOutputStream();
			//envio de datos es una operacion de escritura con un printstream
			try(PrintStream salida = new PrintStream(os);BufferedReader bf=new BufferedReader(new InputStreamReader (is));){
				//recuperamos la cadena de busqueda
				String cad=bf.readLine();
				//recogemos contactos
				GestionContactos agenda = new GestionContactos(); 
				List<Contacto> contactos=agenda.buscar(cad);
				//transformamos la lista de contactos a JSON
				String respuesta=formatearJSON(contactos);
				salida.println(respuesta);
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				sc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private String formatearJSON(List<Contacto> contacto) {
		JSONArray array=new JSONArray();
		
		contacto.forEach(c->{
			JSONObject ob=new JSONObject();
			ob.put("nombre", c.getNombre());
			ob.put("email", c.getEmail());
			ob.put("telefono", c.getTelefono());
			array.add(ob);
		});
		return array.toJSONString();
	}
}
