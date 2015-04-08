package com.geekytheory.orderregister;

import java.util.ArrayList;
import java.util.Locale;



import com.example.android.navigationdrawerexample.R;
import com.geekytheory.orderregister.libraries.AdminSQLiteOpenHelper;
import com.geekytheory.orderregister.libraries.Detalle_pedido;
import com.geekytheory.orderregister.libraries.GlobalVariables;
import com.geekytheory.orderregister.libraries.GridviewAdapter;
import com.geekytheory.orderregister.libraries.Pedido;
import com.geekytheory.orderregister.libraries.Producto;
import com.geekytheory.orderregister.libraries.Tools;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class CatalogFragment extends Fragment {
	
	View caRootView;
	TabHost tabhost;
	AdminSQLiteOpenHelper admin_ca;
	GridView gridView;
	AutoCompleteTextView atv_text;
	
	private Tools tools;
	private GridviewAdapter mAdapter;
    private ArrayList<Producto> arrayProducto = new ArrayList<Producto>();
    ArrayList<Producto> array2 = new ArrayList<Producto>();
    

	
	public CatalogFragment() {}
    @Override
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        caRootView = inflater.inflate(R.layout.fragment_catalog, container, false);
        admin_ca = new AdminSQLiteOpenHelper(getActivity(),"administracion", null, 1);
        tools = new Tools(getActivity().getApplicationContext());
        
        tabhost=(TabHost) caRootView.findViewById(R.id.tabhost);
        tabhost.setup();
        TabSpec spec=tabhost.newTabSpec("tab1");
        spec.setIndicator("PAGINA PRINCIPAL");
        spec.setContent(R.id.tab1);
        tabhost.addTab(spec);
        
        TabSpec spac=tabhost.newTabSpec("tab2");
        spac.setIndicator("otro tab");
        spac.setContent(R.id.tab2);
        tabhost.addTab(spac);
        
        TabSpec spoc=tabhost.newTabSpec("tab3");
        spoc.setIndicator("tuberias");
        spoc.setContent(R.id.tab3);
        tabhost.addTab(spoc);
        
        inicializarArrayproductoList();
        showAdapterOnGrid(arrayProducto);
        
        atv_text=(AutoCompleteTextView)  caRootView.findViewById(R.id.atv_search_ca);
        atv_text.addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable s) {}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override
			public void onTextChanged(CharSequence s, int start, int before,int count) {
				array2.clear();
				if(!atv_text.getText().toString().equals("")){
					int i=0;
					for( i=0;i<arrayProducto.size();i++){
						if(arrayProducto.get(i).getNombre().toLowerCase(Locale.getDefault()).contains(atv_text.getText().toString().toLowerCase())){
							array2.add(arrayProducto.get(i));
						}
					}					
					showAdapterOnGrid(array2);
				}else if(atv_text.getText().toString().equals("")){
					showAdapterOnGrid(arrayProducto);
				}
			}});
		
		return caRootView;
    }//finish          ONCREATE()
    

    
    
    public void inicializarArrayproductoList(){
    	arrayProducto.clear();
    	int num=0;
        try {
	        	Cursor filas = admin_ca.getSelect("select * from producto");
	        	if(filas.moveToFirst()){
	            	 do {
	            		 num++;
	            		 Producto pro = new Producto(); 
	            		 	pro.setNum(String.valueOf(num));
	            		 	pro.setCategoria(filas.getString(3));
	            		 	pro.setSubcat(filas.getString(4));
	            	    	pro.setCodigo(filas.getString(1));
	            	    	pro.setDescripcion(filas.getString(2));
	            	    	pro.setImagen(filas.getString(7));
	            	    	pro.setMarca(filas.getString(6));
	            	    	pro.setNombre(filas.getString(0));
	            	    	pro.setPrecio(filas.getString(5));
	            	    	pro.setRating("5");
	            	        arrayProducto.add(pro);
	                 } while (filas.moveToNext());    
	        	}		
		} catch (Exception e) {
			e.printStackTrace();
			tools.showShortMessage("No hay productos! "+e.getMessage());
		} 
    }
    
    public void showAdapterOnGrid(ArrayList<Producto> arrayList){
    	gridView = (GridView) caRootView.findViewById(R.id.gv_all_ca);
    	
    	if (arrayList.size()!=0) {
			mAdapter = new GridviewAdapter(getActivity(), arrayList);
			gridView.setAdapter(mAdapter);
			gridView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
					try {
						buidRegisterDialog(mAdapter.getItem(position));
					} catch (Exception e) {
						tools.showShortMessage("no salio" + e.getMessage());
					}
				}
			});
		}
    }
    
private void buidRegisterDialog(final Producto producto_dial){
    	
    	final Dialog dialog = new Dialog(getActivity());
		dialog.setContentView(R.layout.dialog_registerpro);
		dialog.setTitle("Registrar Producto");
		dialog.setCancelable(true);
		
		Button btncancel_dialog=(Button) dialog.findViewById(R.id.btn_gotopedido_dialog);
		GlobalVariables global=(GlobalVariables)getActivity().getApplicationContext();
		Pedido pedido=global.getPedido();
		if(pedido.getListpro().size()>0){
			btncancel_dialog.setVisibility(View.VISIBLE);
		}
		btncancel_dialog.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
							Intent i=new Intent(getActivity(),AddOrderActivity.class);
							getActivity().startActivity(i);
							getActivity().finish();
							getActivity().overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);
				}
		});
		
		ImageView img_producto_dia =(ImageView) dialog.findViewById(R.id.img_producto_dialog);
		img_producto_dia.setImageResource(getResources().getIdentifier("drawable/" + producto_dial.getImagen(), null, getActivity().getPackageName()));
		 
		TextView txt_nom_dia=(TextView) dialog.findViewById(R.id.txt_nomproducto_dialog);
		txt_nom_dia.setText(producto_dial.getNombre());
		
		TextView txt_cod_dia=(TextView) dialog.findViewById(R.id.txt_codigo_dialog);
		txt_cod_dia.setText(producto_dial.getCodigo());
		
		ImageView img_marca_dia = (ImageView) dialog.findViewById(R.id.img_marca_dialog);
		img_marca_dia.setImageResource(getResources().getIdentifier("drawable/" + producto_dial.getMarca().toLowerCase(), null, getActivity().getPackageName()));
		
		EditText txt_precio_dia = (EditText) dialog.findViewById(R.id.txt_precio_dialog);
		txt_precio_dia.setText("S/ "+producto_dial.getPrecio());
		
		ImageButton btn_cant_dialog=(ImageButton) dialog.findViewById(R.id.btn_car_dialog);
		btn_cant_dialog.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					dialog.dismiss();
					buidCantDialog(producto_dial);
				} catch (Exception e) {
					tools.showShortMessage("dialog_cantidad no SIRVE "+ e.getMessage());
				}
			}
		});
		
		EditText txt_desc_dia = (EditText) dialog.findViewById(R.id.txt_desc_dialog);
		txt_desc_dia.setText(producto_dial.getDescripcion());
			
		dialog.show();
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
				
				
				int flag=0;
				if(pedido.getListpro().size()!=0){
					
					try {
						for(int i=0;i<pedido.getListpro().size();i++){
							if(ped_actual.getCodigo().equals(pedido.getListpro().get(i).getCodigo())){
								global.updateCantidad(i,ped_actual.getCantidad() );
								global.updateTotalpro(i,ped_actual.getToalpro() );
								
								tools.showShortMessage("cantidad actulizada");
								flag=1;
							}
						}
					} catch (NumberFormatException e) {
						tools.showShortMessage(e.getMessage());
					}
					
					if(flag==0){global.addDetalle(ped_actual);
					tools.showShortMessage("producto agregado");}
				}
				else{global.addDetalle(ped_actual);
				tools.showShortMessage("producto agregado");}

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
