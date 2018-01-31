package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import beans.Pedidos;

public class GestionPedidos extends Pedidos {
	String localIp;
	public GestionPedidos(String producto, int unidades, String ipcliente, LocalDateTime fecha) {
		super(producto, unidades, ipcliente, fecha);
		// TODO Auto-generated constructor stub
	}

	public List<Pedidos> recibirPedidos(String producto,int unidades){
		String ip="localhost";
		int puerto=9000;
		List<Pedidos> pedidos=null;
		//entrada de datos
		try {
			Socket sc=new Socket(ip,puerto);
			InputStream is=sc.getInputStream();
			OutputStream os=sc.getOutputStream();
				
			try(PrintStream salida = new PrintStream(os);
					BufferedReader bf=new BufferedReader(new InputStreamReader(is));){
				salida.println(formatearJSON(producto,unidades));//manda json con producto y unidades
				String respJSON=bf.readLine();
				pedidos=transformarEnLista(respJSON);//recibe la lista de pedidos	
			}
		}catch (IOException ex) {
				ex.printStackTrace(); 
		}
			return pedidos;
	}	
	
	public void enviarPedido(String producto,int unidades,String ipCliente,LocalDateTime fecha) {
		String ip="localhost";
		int puerto=8000;		
		try {       
					Socket sc=new Socket(ip,puerto);
					InputStream is=sc.getInputStream();
					OutputStream os=sc.getOutputStream();
					
					this.setIpcliente(sc.getLocalAddress().toString());
					//envio de datos es una operacion de escritura con un printstream
					try(PrintStream salida = new PrintStream(os);
							BufferedReader bf=new BufferedReader(new InputStreamReader (is));){		
							salida.println(formatearJSON(producto,unidades,ipCliente,fecha));
					}
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
			
		



private List<Pedidos> transformarEnLista(String json) {
	ArrayList<Pedidos> pedidos = new ArrayList<>();
	JSONParser parser=new JSONParser();
	JSONArray array;
	try {
		array = (JSONArray)parser.parse(json);
		array.forEach(c->{
			JSONObject data = (JSONObject) c;
			Pedidos pedido=new Pedidos( data.get("producto").toString(),
					Integer.parseInt(data.get("unidades").toString()),
					data.get("ipCliente").toString(),
					LocalDateTime.parse(data.get("fecha").toString()));
			pedidos.add(pedido);
		});
		
	} catch (org.json.simple.parser.ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return pedidos;	
}

private String formatearJSON(String producto,int unidades) {
	
		JSONObject ob=new JSONObject();
		ob.put("producto",producto);
		ob.put("unidades", unidades);
	
	return ob.toJSONString();
}

private String formatearJSON(String producto,int unidades,String ipCliente,LocalDateTime fecha) {
	
	JSONObject ob=new JSONObject();
	ob.put("producto",producto);
	ob.put("unidades", unidades);
	ob.put("ipCliente", ipCliente);
	ob.put("fecha", fecha);

return ob.toJSONString();
}

}