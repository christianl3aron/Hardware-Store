package com.geekytheory.orderregister.libraries;

import java.util.ArrayList;

import com.example.android.navigationdrawerexample.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridviewAdapter extends BaseAdapter
{
    private ArrayList<Producto> listProducto;
    private Context activity;
 
    public GridviewAdapter(Context activity,ArrayList<Producto> listProducto) {
        super();
        this.listProducto = listProducto;
        this.activity = activity;
    }
 
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listProducto.size();
    }
 
    @Override
    public Producto getItem(int position) {
        // TODO Auto-generated method stub
        return listProducto.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }
 
    public static class ViewHolder
    {
        public ImageView img_producto;
        public TextView txt_nombre;
        public TextView txt_marca;
        public TextView txt_precio;
        
        public static ViewHolder generate(View convertView) {
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.img_producto=(ImageView) convertView.findViewById(R.id.img_producto_item);
			viewHolder.txt_nombre=(TextView) convertView.findViewById(R.id.txt_nomproducto_dialog);
			viewHolder.txt_marca=(TextView) convertView.findViewById(R.id.txt_marca_item);
			viewHolder.txt_precio=(TextView) convertView.findViewById(R.id.txt_precio_item);
			
			return viewHolder;
		}
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

    	ViewHolder view;
        if(convertView==null){
            view = new ViewHolder();
            convertView = View.inflate(activity,R.layout.item_gridview, null);
            view=ViewHolder.generate(convertView);
            convertView.setTag(view);
        }else{
            view = (ViewHolder) convertView.getTag();
        }
        view.txt_nombre.setText(String.valueOf(position+1)+".  "+listProducto.get(position).getNombre());
        view.txt_marca.setText(listProducto.get(position).getMarca());
        view.txt_precio.setText("S/ "+listProducto.get(position).getPrecio());
        view.img_producto.setImageResource(activity.getResources().getIdentifier("drawable/" + listProducto.get(position).getImagen(), null, activity.getPackageName()));
 
        return convertView;
    }
}