package principal.adaptadores;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import beans.Contacto;

public class AdaptadorTabla extends AbstractTableModel {

	List<Contacto> contactos;
	
	public AdaptadorTabla(List<Contacto> contactos) {
		this.contactos = contactos;
	}
	
	@Override
	public int getColumnCount() {
		//Aqui incluiremos el numero de columnas de la tabla de BBDD o vista a visualizar
		return 3;
	}

	@Override
	public int getRowCount() {
		// Desde un Listener se contactará con la clase de GestionContactos para conocer el numero de filas a representar
		
		return contactos.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {//Indicamos el numero de fila y columna respectivamente
		String valor="";
		switch(arg1) {
		case 0: valor=contactos.get(arg0).getNombre();
			break;
		case 1: valor=contactos.get(arg0).getEmail();
			break;
		case 2: valor=String.valueOf(contactos.get(arg0).getTelefono());
			break;
		}
		return valor; 
		
	}

	@Override//Aqui querremos saber el nombre de cada columna
	public String getColumnName(int arg0) {
		String nombre="";
		switch(arg0) {
		case 0: nombre="Nombre";
			break;
		case 1: nombre="email";
			break;
		case 2: nombre="telefono";
			break;
		}
		return nombre;
	}
}