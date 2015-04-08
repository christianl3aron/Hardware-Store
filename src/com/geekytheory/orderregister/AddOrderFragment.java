package com.geekytheory.orderregister;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.example.android.navigationdrawerexample.R;
import com.geekytheory.orderregister.libraries.AdminSQLiteOpenHelper;
import com.geekytheory.orderregister.libraries.Detalle_pedido;
import com.geekytheory.orderregister.libraries.GlobalVariables;
import com.geekytheory.orderregister.libraries.ListViewDetallePedido_Adapter;
import com.geekytheory.orderregister.libraries.Pedido;
import com.geekytheory.orderregister.libraries.Producto;
import com.geekytheory.orderregister.libraries.Tools;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

public class AddOrderFragment extends Fragment{

	TextView txtnombrescomp;
	TextView txtempresa;
	TextView txtdepprodis;
	TextView txtdni;
	TextView txtruc;
	TextView txtsubtotal;
	TextView txtigv;
	TextView txttotal;
	TextView txtdireccion;
	Button btn_gotocatalog;
	Button btn_gotoaddclient;
	Button btn_gotoclientlist;
	Button btn_register;
	View aoRootView;
	float total;
	float igv;
	float subtotal;
	
	private Tools tools;
	AdminSQLiteOpenHelper admin_ao;
	GlobalVariables global;
	Pedido pedido;
	
	private ListViewDetallePedido_Adapter adapter;
	
	public AddOrderFragment(){}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		aoRootView = inflater.inflate(R.layout.fragment_add_order, container, false);
		admin_ao = new AdminSQLiteOpenHelper(getActivity(),"administracion", null, 1);
		tools = new Tools(getActivity().getApplicationContext());
		
		txtnombrescomp=(TextView) aoRootView.findViewById(R.id.txt_nombrescomp_ao);
		txtempresa=(TextView) aoRootView.findViewById(R.id.txt_empresa_ao);
		txtdepprodis=(TextView) aoRootView.findViewById(R.id.txt_depprodis_ao);
		txtdni=(TextView) aoRootView.findViewById(R.id.txt_dni_ao);
		txtruc=(TextView) aoRootView.findViewById(R.id.txt_ruc_ao);
		txtdireccion=(TextView) aoRootView.findViewById(R.id.txt_direccion_ao);
		btn_gotocatalog=(Button) aoRootView.findViewById(R.id.btn_gotocatalog_ao);
		btn_gotoaddclient=(Button) aoRootView.findViewById(R.id.btn_gotoaddclient_ao);
		btn_gotoclientlist=(Button) aoRootView.findViewById(R.id.btn_gotoclientlist_ao);
		btn_register=(Button) aoRootView.findViewById(R.id.btn_register_ao);
		txtsubtotal=(TextView) aoRootView.findViewById(R.id.txt_subtotal_ao);
		txtigv=(TextView) aoRootView.findViewById(R.id.txt_igv_ao);
		txttotal=(TextView) aoRootView.findViewById(R.id.txt_total_ao);
		
		btn_gotocatalog.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
					Intent i=new Intent(getActivity(),CatalogActivity.class);
					startActivity(i);
		        	getActivity().overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);
		        	getActivity().finish();
			}});
		btn_gotoclientlist.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
					Intent i=new Intent(getActivity(),ClientListActivity.class);
					startActivity(i);
		        	getActivity().overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);
		        	getActivity().finish();
			}});
		btn_gotoaddclient.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
					Intent i=new Intent(getActivity(),AddClientActivity.class);
					startActivity(i);
		        	getActivity().overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);
		        	getActivity().finish();
			}});
		btn_register.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
				//SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");

				
				GlobalVariables global=(GlobalVariables)getActivity().getApplicationContext();
				Pedido pedido=global.getPedido();
				int cant_de=pedido.getListpro().size();
				if(global.getFlag()==1 && cant_de>0){
					
					try {
						SimpleDateFormat cod_sf = new SimpleDateFormat("yyyyMMddhhmmss");
						String codigo = cod_sf.format(new Date());
						
						SimpleDateFormat fec_sf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
						String fecha = fec_sf.format(new Date());
						
						ContentValues registroPedido = new ContentValues();
						registroPedido.put("codigo", codigo);
						registroPedido.put("fecha", fecha);
						registroPedido.put("subtotal", txtsubtotal.getText().toString().replace(",", "."));
						registroPedido.put("igv", txtigv.getText().toString().replace(",", "."));
						registroPedido.put("total", txttotal.getText().toString().replace(",", "."));
						registroPedido.put("nombre", pedido.getNombre());
						registroPedido.put("paterno", pedido.getAppaterno());
						registroPedido.put("materno", pedido.getApmaterno());
						registroPedido.put("dni", pedido.getDni());
						registroPedido.put("empresa", pedido.getEmpresa());
						registroPedido.put("ruc", pedido.getRuc());
						registroPedido.put("departamento", pedido.getDepartamento());
						registroPedido.put("provincia", pedido.getProvincia());
						registroPedido.put("distrito", pedido.getDistrito());
						registroPedido.put("direccion", pedido.getDireccion());
						admin_ao.insertInTable("pedido", registroPedido);
						
						for(int i=0;i<cant_de;i++){
							ContentValues registroDetalle = new ContentValues();
							registroDetalle.put("codpedido", codigo);
							registroDetalle.put("cantidad", pedido.getListpro().get(i).getCantidad());
							registroDetalle.put("totalpro", pedido.getListpro().get(i).getToalpro());
							registroDetalle.put("nombre", pedido.getListpro().get(i).getNombre());
							registroDetalle.put("codigo", pedido.getListpro().get(i).getCodigo());
							registroDetalle.put("descripcion", pedido.getListpro().get(i).getDescripcion());
							registroDetalle.put("categoria", pedido.getListpro().get(i).getCategoria());
							registroDetalle.put("subcategoria", pedido.getListpro().get(i).getSubcat());
							registroDetalle.put("precio", pedido.getListpro().get(i).getPrecio());
							registroDetalle.put("marca", pedido.getListpro().get(i).getMarca());
							registroDetalle.put("imagen", pedido.getListpro().get(i).getImagen());
							admin_ao.insertInTable("detalle", registroDetalle);
						}
						tools.showShortMessage("enviado con éxito!");
						global.resetPedido();
						fillListWithAllProducts();
						txtsubtotal.setText("0.00");
						txtigv.setText("0.00");
						txttotal.setText("0.00");
						txtnombrescomp.setText("");
						txtempresa.setText("");
						txtdepprodis.setText("");
						txtdni.setText("");
						txtruc.setText("");
						txtdireccion.setText("");
						
					} catch (Exception e) {
						tools.showShortMessage("error en guardar: "+e.getMessage());
					}
					
					
				}else if(!(global.getFlag()==1)) tools.showShortMessage("elija el cliente");
				else if(!(cant_de>0)) tools.showShortMessage("elija productos");
				
				
				
			}});
		
		
		fillListWithAllProducts();
		if(global.getFlag()==1){
			pedido=global.getPedido();
			txtnombrescomp.setText(tools.titleize(pedido.getNombre()+" "+pedido.getAppaterno()+" "+pedido.getApmaterno()));
			txtempresa.setText(tools.titleize(pedido.getEmpresa()));
			txtdepprodis.setText(tools.titleize(pedido.getDepartamento()+" - "+pedido.getProvincia()+" - "+pedido.getDistrito()));
			txtdni.setText(pedido.getDni());
			txtruc.setText(pedido.getRuc());
			txtdireccion.setText(tools.titleize(pedido.getDireccion()));
			}
        
		
		return aoRootView;
	}// finish oncreate() event
	
	private void fillListWithAllProducts(){
		global=(GlobalVariables)getActivity().getApplicationContext();
		pedido=global.getPedido();
			inicializarListView(pedido.getListpro());
			subtotal=0;
				for(int i=0;i<pedido.getListpro().size();i++)
				{subtotal=(float)subtotal+Float.parseFloat(pedido.getListpro().get(i).getToalpro().replace(",", "."));
				}
		calcularMonto();
	}
	
	
	private void inicializarListView(final ArrayList<Detalle_pedido> Array_intern) {	
		ListView lista = (ListView) aoRootView.findViewById(R.id.lst_detallepedido_ao);
 		adapter = new ListViewDetallePedido_Adapter(getActivity(), Array_intern);
 		lista.setAdapter(adapter);
 		lista.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position,long id) {
				
				 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getActivity());
                 dialogo1.setTitle("Seleccione Opcion");  
                 dialogo1.setMessage(pedido.getListpro().get(position).getNombre());            
                 dialogo1.setCancelable(true);    
                 dialogo1.setPositiveButton("Cambiar Cantidad", new DialogInterface.OnClickListener() {  
                     public void onClick(DialogInterface dialogo1, int id) {  
                    	 try {
							buidCantDialog(pedido.getListpro().get(position));
						} catch (Exception e) {
							tools.showShortMessage(e.getMessage());
						}
                    	 dialogo1.dismiss();
                     }});  
                 dialogo1.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {  
                     public void onClick(DialogInterface dialogo1, int id) {  
                    	 try {
							pedido.getListpro().remove(position);
							tools.showShortMessage("producto eliminado de la lista");
							fillListWithAllProducts();
							 dialogo1.dismiss();
						} catch (Exception e) {
							tools.showShortMessage("Error en Delete "+e.getMessage());
						}
                     }});            
                 dialogo1.show();
				
			}});
 		
 		
	}
	
	private void calcularMonto(){
		igv=(float) (subtotal*0.18);
		total=subtotal+igv;
		txtsubtotal.setText(String.format("%.2f",subtotal));
		txtigv.setText(String.format("%.2f",igv));
		txttotal.setText(String.format("%.2f",total));
	}
	
	private void buidCantDialog(final Producto producto_cant){
		
		final Dialog dialog_cant = new Dialog(getActivity());
		dialog_cant.setContentView(R.layout.dialog_cantpro);
		dialog_cant.setTitle("Seleccionar Cantidad");
		dialog_cant.setCancelable(true);
		
		int value=5;
		GlobalVariables global=(GlobalVariables)getActivity().getApplicationContext();
		Pedido pedido=global.getPedido();
		
		if(pedido.getListpro().size()!=0){
			try {
				for(int i=0;i<pedido.getListpro().size();i++){
					if(producto_cant.getCodigo().equals(pedido.getListpro().get(i).getCodigo())){
						value=Integer.parseInt(pedido.getListpro().get(i).getCantidad());
					}
				}
			} catch (NumberFormatException e) {
				tools.showShortMessage(e.getMessage());
			}
		}
		
		
		TextView txtprecio_cdial=(TextView) dialog_cant.findViewById(R.id.txt_preciopro_cdialog);
		txtprecio_cdial.setText("S/ "+producto_cant.getPrecio());
		
		final TextView txtcant_cdial=(TextView) dialog_cant.findViewById(R.id.txt_cantpro_cdialog);
		txtcant_cdial.setText(String.valueOf(value));
		final TextView txttotal_cdial=(TextView) dialog_cant.findViewById(R.id.txt_total_cdialog);
		txttotal_cdial.setText("S/ "+String.format("%.2f", value*Float.valueOf(producto_cant.getPrecio())));
		final NumberPicker np = (NumberPicker) dialog_cant.findViewById(R.id.numberPicker1);
		try {
			
			np.setMaxValue(100);
			np.setMinValue(1);
			np.setValue(value);
			np.setFocusable(true);
			np.setFocusableInTouchMode(true);
			np.setOnValueChangedListener(new OnValueChangeListener(){
				@Override
				public void onValueChange(NumberPicker picker, int oldVal,int newVal) {
					txtcant_cdial.setText(String.valueOf(newVal));
					float total=newVal*Float.valueOf(producto_cant.getPrecio());
					txttotal_cdial.setText("S/ "+String.format("%.2f", total));
				}});
		} catch (Exception e) {
			tools.showShortMessage("error en CHANGGEDVALUE LISTENER");
		}
		
		
		Button btnok_cdial = (Button) dialog_cant.findViewById(R.id.btn_ok_cantdialog);
		btnok_cdial.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Detalle_pedido ped_actual=new Detalle_pedido();
				try {
					ped_actual.setNombre(producto_cant.getNombre());
					ped_actual.setCodigo(producto_cant.getCodigo());
					ped_actual.setCategoria(producto_cant.getCategoria());
					ped_actual.setSubcat(producto_cant.getSubcat());
					ped_actual.setDescripcion(producto_cant.getDescripcion());
					ped_actual.setMarca(producto_cant.getMarca());
					ped_actual.setRating(producto_cant.getRating());
					ped_actual.setImagen(producto_cant.getImagen());
					ped_actual.setPrecio(producto_cant.getPrecio());
					ped_actual.setCantidad(String.valueOf(np.getValue()));
					ped_actual.setToalpro(String.format("%.2f", (np.getValue()*Float.valueOf(producto_cant.getPrecio()))));
				} catch (Exception e1) {
					tools.showShortMessage("error en creacion de detalle "+e1.getMessage());
				}
			
				
					GlobalVariables global=(GlobalVariables)getActivity().getApplicationContext();
					Pedido pedido=global.getPedido();
					
					
					
						
						try {
							for(int i=0;i<pedido.getListpro().size();i++){
								if(ped_actual.getCodigo().equals(pedido.getListpro().get(i).getCodigo())){
									global.updateCantidad(i,ped_actual.getCantidad() );
									global.updateTotalpro(i,ped_actual.getToalpro() );
									
									tools.showShortMessage("cantidad actulizada");
								}
							}
							fillListWithAllProducts();
							
						} catch (NumberFormatException e) {
							tools.showShortMessage(e.getMessage());
						}
						
						
	
				dialog_cant.dismiss();
			
			
			}});
		
		Button btncancel_cdial = (Button) dialog_cant.findViewById(R.id.btn_cancel_cantdialog);
		btncancel_cdial.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				dialog_cant.dismiss();
			}});
		
			
		dialog_cant.show();
	}
}
