package principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) {
		String ip="10.1.1.15";
		int puerto=9000;
		
		try {
			//establecimiento de conexion
			Socket sc=new Socket(ip,puerto);
			///crear canales entrada-salida
			InputStream is=sc.getInputStream();
			OutputStream os=sc.getOutputStream();
			//envio de datos es una operacion de escritura con un printstream
			try(PrintStream salida = new PrintStream(os);BufferedReader bf=new BufferedReader(new InputStreamReader (is));){
			
				salida.println("Antonio");
				//leo respuesta 
			
				String cad=bf.readLine();//solo mandamos una linea, si fuera un buffer con más cantidad de texto deberiamos 
				System.out.println(cad);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		

	}

}
