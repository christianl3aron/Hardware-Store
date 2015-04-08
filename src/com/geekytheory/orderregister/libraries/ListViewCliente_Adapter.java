package com.geekytheory.orderregister.libraries;

import java.util.ArrayList;

import com.example.android.navigationdrawerexample.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListViewCliente_Adapter extends ArrayAdapter<Object> {

	Context context;
	private ArrayList<Cliente> clientes;
	
	public ListViewCliente_Adapter(Context context, ArrayList<Cliente> clientes) {
		super(context, R.layout.item_client_list);
		this.context = context;
		this.clientes = clientes;
	}
	
	public int getCount() {
		return clientes.size();
	}
	
	
	private static class PlaceHolder {

		TextView empresa;
		TextView ruc;
		TextView departamento;
		TextView provincia;
		TextView distrito;
		TextView nombre;
		TextView apellidos;
		TextView direccion;
		TextView telefono;
		TextView celular;
		

		public static PlaceHolder generate(View convertView) {
			PlaceHolder placeHolder = new PlaceHolder();
			placeHolder.empresa = (TextView) convertView.findViewById(R.id.edtempresa_clientlist);
			placeHolder.ruc = (TextView) convertView.findViewById(R.id.edtruc_clientlist);
			placeHolder.departamento = (TextView) convertView.findViewById(R.id.edtdepartamento_clientlist);
			placeHolder.provincia = (TextView) convertView.findViewById(R.id.edtprovincia_clientlist);
			placeHolder.distrito = (TextView) convertView.findViewById(R.id.edtdistrito_clientlist);
			placeHolder.nombre = (TextView) convertView.findViewById(R.id.edtnombre_clientlist);
			placeHolder.apellidos = (TextView) convertView.findViewById(R.id.edtapellidos_clientlist);
			placeHolder.direccion = (TextView) convertView.findViewById(R.id.edtdireccion_clientlist);
			placeHolder.telefono = (TextView) convertView.findViewById(R.id.edttelefono_clientlist);
			placeHolder.celular = (TextView) convertView.findViewById(R.id.edtcelular_clientlist);
			return placeHolder;
		}

	}
	
	
	public View getView(int position, View convertView, ViewGroup parent) {
		PlaceHolder placeHolder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_client_list, null);
			placeHolder = PlaceHolder.generate(convertView);
			convertView.setTag(placeHolder);
		} else {
			placeHolder = (PlaceHolder) convertView.getTag();
		}
		placeHolder.empresa.setText(titleize(clientes.get(position).getEmpresa()));
		placeHolder.ruc.setText(clientes.get(position).getRuc());
		placeHolder.departamento.setText(titleize(clientes.get(position).getDepartamento()));
		placeHolder.provincia.setText(titleize(clientes.get(position).getProvincia()));
		placeHolder.distrito.setText(titleize(clientes.get(position).getDistrito()));
		placeHolder.nombre.setText(titleize(clientes.get(position).getNombre()));
		placeHolder.apellidos.setText(titleize(clientes.get(position).getAppaterno())+" "+clientes.get(position).getApmaterno());
		placeHolder.direccion.setText(titleize(clientes.get(position).getDireccion()));
		placeHolder.telefono.setText(clientes.get(position).getTelefono());
		placeHolder.celular.setText(clientes.get(position).getCelular());
		
		return (convertView);
	}
	
	private String titleize(String source){
        boolean cap = true;
        char[]  out = source.toCharArray();
        int i, len = source.length();
        for(i=0; i<len; i++){
            if(Character.isWhitespace(out[i])){
                cap = true;
                continue;
            }
            if(cap){
                out[i] = Character.toUpperCase(out[i]);
                cap = false;
            }
        }
        return new String(out);
    }

	
}
