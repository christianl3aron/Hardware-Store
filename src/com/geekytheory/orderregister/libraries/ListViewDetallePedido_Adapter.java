package com.geekytheory.orderregister.libraries;

import java.util.ArrayList;

import com.example.android.navigationdrawerexample.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListViewDetallePedido_Adapter extends ArrayAdapter<Object> {

	Context context;
	private ArrayList<Detalle_pedido> detalles;
	
	public ListViewDetallePedido_Adapter(Context context, ArrayList<Detalle_pedido> detalles) {
		super(context, R.layout.item_add_order_list);
		this.context = context;
		this.detalles = detalles;
	}
	
	public int getCount() {
		return detalles.size();
	}
	
	
	private static class PlaceHolder {

		TextView cant;
		TextView cod;
		TextView nom;
		TextView punit;
		TextView total;
		
		

		public static PlaceHolder generate(View convertView) {
			PlaceHolder placeHolder = new PlaceHolder();
			placeHolder.cant = (TextView) convertView.findViewById(R.id.txt_cantidad_listao);
			placeHolder.cod = (TextView) convertView.findViewById(R.id.txt_codigo_listao);
			placeHolder.nom = (TextView) convertView.findViewById(R.id.txt_pro_listao);
			placeHolder.punit= (TextView) convertView.findViewById(R.id.txt_preciouni_listao);
			placeHolder.total = (TextView) convertView.findViewById(R.id.txt_preciototal_listao);
			return placeHolder;
		}

	}
	
	
	public View getView(int position, View convertView, ViewGroup parent) {
		PlaceHolder placeHolder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_add_order_list, null);
			placeHolder = PlaceHolder.generate(convertView);
			convertView.setTag(placeHolder);
		} else {
			placeHolder = (PlaceHolder) convertView.getTag();
		}
		placeHolder.cant.setText(detalles.get(position).getCantidad());
		placeHolder.cod.setText(detalles.get(position).getCodigo());
		placeHolder.nom.setText(detalles.get(position).getNombre());
		placeHolder.punit.setText(detalles.get(position).getPrecio());
		placeHolder.total.setText(detalles.get(position).getToalpro());
		
		return (convertView);
	}

	
}
