package principal.adaptadores;


import java.util.List;

import javax.swing.table.AbstractTableModel;

import beans.Pedidos;

public class AdaptadorTabla extends AbstractTableModel {

	List<Pedidos> pedidos;
	
	public AdaptadorTabla(List<Pedidos> pedidos) {
		this.pedidos = pedidos;
	}
	
	@Override
	public int getColumnCount() {
		//Aqui incluiremos el numero de columnas de la tabla de BBDD o vista a visualizar
		return 4;
	}

	@Override
	public int getRowCount() {
		// Desde un Listener se contactará con la clase de GestionContactos para conocer el numero de filas a representar
		
		return pedidos.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {//Indicamos el numero de fila y columna respectivamente
		String valor="";
		switch(arg1) {
		case 0: valor=pedidos.get(arg0).getProducto();
			break;
		case 1: valor=String.valueOf(pedidos.get(arg0).getUnidades());
			break;
		case 2: valor=pedidos.get(arg0).getIpcliente();
			break;
		case 3: valor=pedidos.get(arg0).getFecha().toString();
		}
		return valor; 
		
	}

	@Override//Aqui querremos saber el nombre de cada columna
	public String getColumnName(int arg0) {
		String nombre="";
		switch(arg0) {
		case 0: nombre="producto";
			break;
		case 1: nombre="unidades";
			break;
		case 2: nombre="ipCliente";
			break;
		case 3: nombre="fecha";
		}
		return nombre;
	}

}

