package presentacion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import comunicaciones.*;

public class ServidorReco {
	public static void main(String[] args) {
		try (ServerSocket servreco=new ServerSocket(9000);ServerSocket servregi=new ServerSocket(8000);){//se puede hace run try con recursos
			
			ExecutorService es=Executors.newFixedThreadPool(10); //creamos un pool
			
			while (true) {
				//quedamos a la espera de una llamada 
				System.out.println("Esperando conexion...");
				//establecimiento de conexion	
				Socket screc=servreco.accept();
				System.out.println("Llamada Recuperacion recibida");
				/*Socket screg=servregi.accept();
				//se produce la conexion 
				System.out.println("Llamada Registro recibida");*/
				
				es.submit(new HiloClienteRecuperacion(screc)); 
				//es.submit(new HiloClienteRegistro(screg));//mediante el método submit se arranca 
			}//Se crea un Hilocliente para cada uno que llegue y se llama 
		}catch (IOException ex) {
		ex.printStackTrace();
	}
}
}
