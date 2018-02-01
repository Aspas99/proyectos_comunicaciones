package beans;

import java.time.LocalDateTime;

public class Pedidos {
	private int idPedido;
	private String producto;
	private int unidades;
	private String ipcliente;
	private LocalDateTime fecha;
	
	
	public Pedidos(String producto, int unidades, String ipcliente, LocalDateTime fecha) {
		super();
		this.producto = producto;
		this.unidades = unidades;
		this.ipcliente = ipcliente;
		this.fecha = fecha;
	}


	public int getIdPedido() {
		return idPedido;
	}


	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}


	public String getProducto() {
		return producto;
	}


	public void setProducto(String producto) {
		this.producto = producto;
	}


	public int getUnidades() {
		return unidades;
	}


	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}


	public String getIpcliente() {
		return ipcliente;
	}


	public void setIpcliente(String ipcliente) {
		this.ipcliente = ipcliente;
	}


	public LocalDateTime getFecha() {
		return fecha;
	}


	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	
	
}
