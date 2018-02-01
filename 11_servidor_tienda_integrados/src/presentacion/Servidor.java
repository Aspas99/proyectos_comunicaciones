package presentacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import comunicaciones.*;

public class Servidor {
	public static void main(String[] args) {
		try (ServerSocket servreco=new ServerSocket(8000)){//se puede hace run try con recursos
			
			ExecutorService es=Executors.newFixedThreadPool(10); //creamos un pool
			
			while (true) {
				//quedamos a la espera de una llamada 
				System.out.println("Esperando conexion...");
				//establecimiento de conexion	
				Socket sc=servreco.accept();
				String op=recuperarOperacion(sc);//determinamos que operaicon quiere hacer  el cliente
				//se produce la conexion 
				System.out.println("Llamada recibida");
				switch(op) {
				case "registro":
					es.submit(new HiloClienteRecuperacion(sc));
					break;
				case "recuperacion":
					es.submit(new HiloClienteRegistro(sc));//mediante el método submit se arranca 
					break;
				} 
				
			}//Se crea un Hilocliente para cada uno que llegue y se llama 
		}catch (IOException ex) {
		ex.printStackTrace();
	}
}
	private static String recuperarOperacion(Socket sc) {
		String op="";
		try {
			
			JSONObject ob;
			try {
				BufferedReader bf=new BufferedReader(new InputStreamReader(sc.getInputStream()));
				JSONParser parse=new JSONParser();
				ob = (JSONObject)parse.parse(bf.readLine());//hay que parsear la linea de texto y no el InputStream completo cuando son
				op=ob.get("operacion").toString();			//VARIOS Json, el de la operacion a realizar y los datos
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		return op;
	}
}
