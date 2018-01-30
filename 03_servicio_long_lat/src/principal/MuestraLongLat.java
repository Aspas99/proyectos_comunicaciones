package principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MuestraLongLat {

	public static void main(String[] args) {
		String dir="http://apidev.accuweather.com/locations/v1/search?q=madrid,%20spain&apikey=hoArfRosT1215";
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
	
	//El Array JSONArray es un array de objetos JSON
	JSONObject data = (JSONObject) array.get(0);
	JSONObject dat2=(JSONObject) data.get("GeoPosition");
	//con el get le pides las propiedades dependiendo del tipo de objeto
	System.out.println("Latitud objeto 0:" + dat2.get("Latitude"));
	System.out.println("Longitud objeto 0:" + dat2.get("Longitude"));
	
	
}catch (IOException ex) {
	ex.printStackTrace();
} catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

}

}
