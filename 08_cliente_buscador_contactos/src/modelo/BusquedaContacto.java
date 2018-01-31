package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import beans.Contacto;

public class BusquedaContacto extends Contacto {

	
	 
	public BusquedaContacto(String nombre, String email, int telefono) {
		super(nombre, email, telefono);
		// TODO Auto-generated constructor stub
	}




	public List<Contacto>buscarContacto(String nombre ) {
		
		String ip="10.1.1.100";
		int puerto=9000;
		//entrada de datos
		List<Contacto> contactos=null;
		
	try {	
		
		Socket sc=new Socket(ip,puerto);
		InputStream is=sc.getInputStream();
		OutputStream os=sc.getOutputStream();
			
		try(PrintStream salida = new PrintStream(os);BufferedReader bf=new BufferedReader(new InputStreamReader(is));){
			
			salida.println(nombre);
			String respJSON=bf.readLine();
			contactos=transformarEnLista(respJSON);
		
		}
		}catch (IOException ex) {
			ex.printStackTrace();
	    }
		return contactos;
	}	
		
		

	
	private List<Contacto> transformarEnLista(String json) {
		ArrayList<Contacto> agenda = new ArrayList<>();
		JSONParser parser=new JSONParser();
		JSONArray array;
		try {
			array = (JSONArray)parser.parse(json);
			array.forEach(c->{
				JSONObject data = (JSONObject) c;
				/*JSONObject dat2=(JSONObject) data.get("nombre");
				JSONObject dat3=(JSONObject) data.get("email");
				JSONObject dat4=(JSONObject) data.get("telefono");*/
				Contacto user=new Contacto( data.get("nombre").toString(),
						data.get("email").toString(),Integer.parseInt(data.get("telefono").toString()));
				agenda.add(user);
			});
			
		
	
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return agenda;
		
	}
} 
