package presentacion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import comunicaciones.*;

public class Servidor {
	public static void main(String[] args) {
		try (ServerSocket serv=new ServerSocket(9000);){//se puede hace run try con recursos
			
			ExecutorService es=Executors.newFixedThreadPool(10); //creamos un pool
			
			while (true) {
				//quedamos a la espera de una llamada 
				System.out.println("Esperando conexion...");
				//establecimiento de conexion	
				Socket sc=serv.accept();
				//se produce la conexion 
				System.out.println("Llamada recibida");
				
				es.submit(new HiloCliente(sc)); //mediante el método submit se arranca 
			}//Se crea un Hilocliente para cada uno que llegue y se llama 
		}catch (IOException ex) {
		ex.printStackTrace();
	}
}
