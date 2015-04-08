package com.geekytheory.orderregister.libraries;


import android.app.Application;

public class GlobalVariables extends Application {
	
	private Pedido pedido=new Pedido();
	private int flag=0;

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
	
	
	//metodos heredados de la clase CLIENTE
	
	public void setEmpresa(String empresa){
		pedido.setEmpresa(empresa);
	}
	
	public void setRuc(String ruc){
		pedido.setRuc(ruc);
	}
	
	public void setDepartamento(String departamento){
		pedido.setDepartamento(departamento);
	}
	public void setProvincia(String provincia){
		pedido.setProvincia(provincia);
	}
	public void setDistrito(String distrito){
		pedido.setDistrito(distrito);
	}
	public void setNombre(String nom){
		pedido.setNombre(nom);
	}
	public void setAppaterno(String appaterno){
		pedido.setAppaterno(appaterno);
	}
	public void setApmaterno(String apmaterno){
		pedido.setApmaterno(apmaterno);
	}
	public void setDireccion(String direccion){
		pedido.setDireccion(direccion);
	}
	public void setTelefono(String telefono){
		pedido.setTelefono(telefono);
	}
	public void setCelular(String celular){
		pedido.setCelular(celular);
	}
	public void setDni(String dni){
		pedido.setDni(dni);
	}
	
	//metodos Propios
	
	public void setFecha(String fecha){
		pedido.setFecha(fecha);
	}
	public void setCodigo(String codigo){
		pedido.setCodigo(codigo);
	}
	public void setTotal(String total){
		pedido.setTotal(total);
	}
	public void setSubTotal(String subtotal){
		pedido.setSubtotal(subtotal);
	}
	public void setIgv(String igv){
		pedido.setIgv(igv);
	}
	public void addDetalle(Detalle_pedido detalle){
		pedido.AddDetalleToList(detalle);
	}

	
	
	public int getFlag() {
		return flag;
	}

	
	
	
	
	public void resetFlag() {
		this.flag =0;
	}
	public void activateFlag() {
		this.flag =1;
	}
	
	public void resetPedido(){
		this.pedido=new Pedido();
		resetFlag();
	}
	public void resetDetalleList(){
		this.pedido.resetDetalleList();
	}

	
	public void updateCantidad(int i, String cantidad){
		this.pedido.updateCantList(i, cantidad);
	}
	
	public void updateTotalpro(int i, String totalpro){
		this.pedido.updateTotalList(i, totalpro);
	}


}
