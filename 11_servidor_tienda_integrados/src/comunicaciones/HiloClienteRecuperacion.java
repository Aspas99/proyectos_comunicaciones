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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import beans.Pedidos;
import modelo.GestionTienda;

public class HiloClienteRecuperacion implements Runnable {

	private Socket sc;
	List<Pedidos> pedidos=null;
	
	public HiloClienteRecuperacion(Socket sc) {
		this.sc=sc;
	}
	
	@Override
	public void run() {
		try {
			InputStream is=sc.getInputStream();
			OutputStream os=sc.getOutputStream();
			//envio de datos es una operacion de escritura con un printstream
			try(PrintStream salida = new PrintStream(os);
					BufferedReader bf=new BufferedReader(new InputStreamReader (is));){
				JSONParser parser=new JSONParser();
				JSONArray array=(JSONArray) parser.parse(new InputStreamReader(is));
				for(Object ob:array) {
					JSONObject data=(JSONObject) ob;
					GestionTienda tienda=new GestionTienda("",0,"",null);
					pedidos=tienda.devolverPedido(data.get("producto").toString(),Integer.parseInt(data.get("unidades").toString()));
					salida.println(formatearJSON(pedidos));
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	private String formatearJSON(List<Pedidos> pedido) {//en lugar de llamar a este  metodo hacerlo en el cuerpo  
		JSONArray array=new JSONArray();
		
		pedido.forEach(c->{
			JSONObject ob=new JSONObject();
			ob.put("producto", c.getProducto());
			ob.put("unidades", c.getUnidades());
			ob.put("ipCliente", c.getIpcliente());
			ob.put("fecha", c.getFecha());
			array.add(ob);
		});
		return array.toJSONString();
	}
	

	
}
