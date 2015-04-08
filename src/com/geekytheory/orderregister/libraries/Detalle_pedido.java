package com.geekytheory.orderregister.libraries;

public class Detalle_pedido extends Producto{

	private String cantidad;
	private String toalpro;
	

	public Detalle_pedido(){};
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getToalpro() {
		return toalpro;
	}
	public void setToalpro(String toalpro) {
		this.toalpro = toalpro;
	}
	
}
