package principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MuestraContactos {

	public static void main(String[] args) {
						String dir="http://10.1.1.100:8080/servicio_contactos/Contactos";
				try {
					URL url = new URL(dir);
					URLConnection con = url.openConnection();
					//entrada de datos
					InputStream is=con.getInputStream(); 
					/*try(BufferedReader bf=new BufferedReader(new InputStreamReader(is));){
						 //Asi no tenemos que cerrar explicitamente el BufferedReader
						bf.lines().forEach(s->System.out.println(s));
					}
					*/
					JSONParser parser=new JSONParser();
					JSONArray array=(JSONArray) parser.parse(new InputStreamReader(is));
					//recorremos el array JSON y mostramos los nombres de todos los contactos de la agenda
					//El Array JSONArray es un array de objetos JSON
					for(Object ob:array) {
						JSONObject data=(JSONObject) ob;
						System.out.println(data.get("nombre"));
					}
					
					
				}catch (IOException ex) {
					ex.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
}

	


