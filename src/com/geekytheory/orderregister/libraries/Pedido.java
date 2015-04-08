package com.geekytheory.orderregister.libraries;

import java.util.ArrayList;

public class Pedido extends Cliente{
	
	private String fecha;
	private String total;
	private String subtotal;
	private String igv;
	private String codigo;
	private ArrayList<Detalle_pedido> listpro =new ArrayList<Detalle_pedido>();
	public Pedido(){}
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	
	public ArrayList<Detalle_pedido> getListpro() {
		return listpro;
	}
	public void setListpro(ArrayList<Detalle_pedido> listpro) {
		this.listpro = listpro;
	}
	public void AddDetalleToList(Detalle_pedido detalle){
		this.listpro.add(detalle);
	}
	public void resetDetalleList(){
		this.listpro=new ArrayList<Detalle_pedido>();
	}
	public void updateCantList(int i, String cantidad){
		this.listpro.get(i).setCantidad(cantidad);
	}
	
	public void updateTotalList(int i, String totalpro){
		this.listpro.get(i).setToalpro(totalpro);
	}
	
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public String getIgv() {
		return igv;
	}

	public void setIgv(String igv) {
		this.igv = igv;
	}
}
