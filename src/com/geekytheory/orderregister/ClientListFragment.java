package com.geekytheory.orderregister;

import java.util.ArrayList;

import com.example.android.navigationdrawerexample.R;
import com.geekytheory.orderregister.libraries.AdminSQLiteOpenHelper;
import com.geekytheory.orderregister.libraries.Cliente;
import com.geekytheory.orderregister.libraries.GlobalVariables;
import com.geekytheory.orderregister.libraries.ListViewCliente_Adapter;
import com.geekytheory.orderregister.libraries.ExpandListAdapter;
import com.geekytheory.orderregister.libraries.ExpandListChild;
import com.geekytheory.orderregister.libraries.ExpandListGroup;
import com.geekytheory.orderregister.libraries.Tools;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;

public class ClientListFragment extends Fragment {
	
	private AutoCompleteTextView atvbuscar_cl;
	private Spinner spnopciones_cl;
	private Spinner spndepartamento_cl;
	private int opcion=0;
	
	private Tools tools;
	private View clRootView;
	private AdminSQLiteOpenHelper admin_cl;
	
	private ExpandListAdapter ExpAdapter;
    private ArrayList<ExpandListGroup> ExpListItems;
    private ExpandableListView ExpandList;
	
	public ArrayList<String> Array_Departamentos = new ArrayList<String>();		
	public ArrayList<Cliente> Array_Clientes = new ArrayList<Cliente>();
	public ArrayList<Cliente> array_Clientes_Copy = new ArrayList<Cliente>();	
	private ListViewCliente_Adapter adapter;
	
	public ClientListFragment(){}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		clRootView = inflater.inflate(R.layout.fragment_client_list, container, false);
		admin_cl=new AdminSQLiteOpenHelper(getActivity(),"administracion", null, 1);
		tools = new Tools(getActivity().getApplicationContext());
		
		fillListWithAllClients(); 
		inicializarSpnopciones();		//inicializa spinner opciones
		inicializarSpndepartamento();	//inicializa spinner departamento
		inicializarAutoCompleteTextView();
		
		ExpandList = (ExpandableListView) clRootView.findViewById(R.id.expandableListView1);
        ExpListItems = SetStandardGroups();
        ExpAdapter = new ExpandListAdapter(getActivity(), ExpListItems);
        ExpandList.setAdapter(ExpAdapter);
        
		return clRootView;
	}
	
	
	
	/**
	 * 
	 * 
	 * PRINCIPAL METHODS
	 * 
	 * 
	 * */
	
	private void fillListWithAllClients(){
			Array_Clientes.clear();
			//array_Clientes_Copy.clear();
	        try {
		        	Cursor filas = admin_cl.getSelect("select empresa.empresa, empresa.ruc, empresa.departamento, empresa.provincia, empresa.distrito, cliente.nombre, cliente.paterno, cliente.materno, empresa.direccion, cliente.telefono, cliente.celular, cliente.dni from empresa, cliente where cliente.dni=empresa.cliente");
		        	if(filas.moveToFirst()){
		            	 do {
		            		 Cliente client_temp = new Cliente(); 
		            		 client_temp.setEmpresa(filas.getString(0));
		            		 client_temp.setRuc(filas.getString(1));
		            		 client_temp.setDepartamento(filas.getString(2));
		            		 client_temp.setProvincia(filas.getString(3));
		            		 client_temp.setDistrito(filas.getString(4));
		            		 client_temp.setNombre(filas.getString(5));
		            		 client_temp.setAppaterno(filas.getString(6));
		            		 client_temp.setApmaterno(filas.getString(7));
		            		 client_temp.setDireccion(filas.getString(8));
		            		 client_temp.setTelefono(filas.getString(9));
		            		 client_temp.setCelular(filas.getString(10));
		            		 client_temp.setDni(filas.getString(11));
	            	         Array_Clientes.add(client_temp);
	        	        	 //array_Clientes_Copy.add(client_temp);  
		                 } while (filas.moveToNext());    
		            	 inicializarListView(Array_Clientes);	
		        	}		
			} catch (Exception e) {tools.showShortMessage("No hay contactos! "+e.getMessage());} 
	}
		
	private void inicializarListView(final ArrayList<Cliente> Array_intern) {	
		ListView lista = (ListView) clRootView.findViewById(R.id.lstclientes_clientlist);
 		adapter = new ListViewCliente_Adapter(getActivity(), Array_intern);
 		lista.setAdapter(adapter);
 		lista.setOnItemClickListener(new OnItemClickListener() {  
            public void onItemClick(AdapterView<?> parent, View view, final int position,long id) {
            	try {
            		 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getActivity());
                     dialogo1.setTitle("Seleccione Opcion");  
                     dialogo1.setMessage(Array_intern.get(position).getNombre()+" "+Array_intern.get(position).getAppaterno()+" "+Array_intern.get(position).getApmaterno());            
                     dialogo1.setCancelable(true);    
                     dialogo1.setPositiveButton("Crear Pedido", new DialogInterface.OnClickListener() {  
                         public void onClick(DialogInterface dialogo1, int id) {  
                        	 
                        	 try {
								GlobalVariables global=(GlobalVariables)getActivity().getApplicationContext();
								 global.setEmpresa(Array_intern.get(position).getEmpresa());
								 global.setRuc(Array_intern.get(position).getRuc());
								 global.setDepartamento(Array_intern.get(position).getDepartamento());
								 global.setProvincia(Array_intern.get(position).getProvincia());
								 global.setDistrito(Array_intern.get(position).getDistrito());
								 global.setNombre(Array_intern.get(position).getNombre());
								 global.setAppaterno(Array_intern.get(position).getAppaterno());
								 global.setApmaterno(Array_intern.get(position).getApmaterno());
								 global.setDireccion(Array_intern.get(position).getDireccion());
								 global.setTelefono(Array_intern.get(position).getTelefono());
								 global.setCelular(Array_intern.get(position).getCelular());
								 global.setDni(Array_intern.get(position).getDni());
								 global.activateFlag();
								 tools.showShortMessage("cliente registrado, listo para elegir productos");
							} catch (Exception e) {		
								tools.showShortMessage("error"+e.getMessage());		
							}
                        	 goToCatalog();
                         }});  
                     dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {  
                         public void onClick(DialogInterface dialogo1, int id) {	dialogo1.dismiss();		}});            
                     dialogo1.show();  
	     		} catch (Exception e) {
	     			tools.showShortMessage("error en creacion de dialogo"+e.getMessage());
	     		}            	                                   
              }});
	}

	private void inicializarAutoCompleteTextView(){
		atvbuscar_cl=(AutoCompleteTextView) clRootView.findViewById(R.id.txtbuscar_clientlist);
		atvbuscar_cl.addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable arg0) {}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,int arg2, int arg3) {}
			@Override
			public void onTextChanged(CharSequence s, int start, int before,int count) {filterByText();}});
	}
	
	private void inicializarSpnopciones(){
		spnopciones_cl=(Spinner) clRootView.findViewById(R.id.spnopcion_clientlist);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.array_spnopciones_cl, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnopciones_cl.setAdapter(adapter);
		spnopciones_cl.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long arg3) {
				if(position==0) {opcion=0;cleanAutoCompleteTextView();}//"cliente";
				else if(position==1) {opcion=1;cleanAutoCompleteTextView();}//"empresa";
				else if(position==2) {opcion=2;cleanAutoCompleteTextView();}//"ruc";
				}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}});
	}
	
	private void inicializarSpndepartamento(){
        	Cursor filas = admin_cl.getSelect("select DISTINCT(departamento) FROM empresa ORDER BY departamento ASC ");
        	if(filas.moveToFirst()){
        		Array_Departamentos.add("SELECCIONE");
            	 do {
            		 Array_Departamentos.add(filas.getString(0).toString());
                 } while (filas.moveToNext()); 
            	spndepartamento_cl=(Spinner) clRootView.findViewById(R.id.spndepartamento_clientlist);
     			ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Array_Departamentos);
     			spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
     		    spndepartamento_cl.setAdapter(spinnerArrayAdapter);
     		    spndepartamento_cl.setOnItemSelectedListener(new OnItemSelectedListener() {
     				@Override
     				public void onItemSelected(AdapterView<?> arg0, View arg1,int pos, long arg3) {
     					for(int i=1;i<Array_Departamentos.size();i++)
     					{	if(pos==i)	tools.showShortMessage("departamento = "+Array_Departamentos.get(i));
     					}
     				}
     				@Override
     				public void onNothingSelected(AdapterView<?> arg0) {}});
        	}		
	}
	
	public void filterByText(){
		 array_Clientes_Copy.clear();
		 if(!atvbuscar_cl.getText().toString().equals("")){
			switch (opcion){
			case 0:{	for(int i=0;i<Array_Clientes.size();i++){
							if((Array_Clientes.get(i).getNombre()+" "+Array_Clientes.get(i).getAppaterno()+""+Array_Clientes.get(i).getApmaterno()).toLowerCase().contains(atvbuscar_cl.getText().toString().toLowerCase())){
							array_Clientes_Copy.add(Array_Clientes.get(i));}}}break;
			case 1:{	for(int i=0;i<Array_Clientes.size();i++){
							if(Array_Clientes.get(i).getEmpresa().toLowerCase().contains(atvbuscar_cl.getText().toString().toLowerCase())){
							array_Clientes_Copy.add(Array_Clientes.get(i));}}}break;
			case 2:{	for(int i=0;i<Array_Clientes.size();i++){
							if(Array_Clientes.get(i).getRuc().toLowerCase().contains(atvbuscar_cl.getText().toString().toLowerCase())){
							array_Clientes_Copy.add(Array_Clientes.get(i));}}}break;
			}
			inicializarListView(array_Clientes_Copy);
		}else if(atvbuscar_cl.getText().toString().equals("")) inicializarListView(Array_Clientes);
	}
	
	/**
	 * 
	 * 
	 * SECUNDARY METHODS
	 * 
	 * 
	 * */
	
	public void cleanAutoCompleteTextView(){
		atvbuscar_cl.setText("");
	}
	
	public void goToCatalog(){
     	Intent i=new Intent(getActivity(),CatalogActivity.class);
		getActivity().startActivity(i);
		getActivity().overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);
		getActivity().finish();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	public ArrayList<ExpandListGroup> SetStandardGroups() {
	    ArrayList<ExpandListGroup> list = new ArrayList<ExpandListGroup>();
	    ArrayList<ExpandListChild> list2 = new ArrayList<ExpandListChild>();
	    ExpandListGroup gru1 = new ExpandListGroup();
	    gru1.setName("Comedy");
	    ExpandListChild ch1_1 = new ExpandListChild();
	    ch1_1.setName("A movie");
	    ch1_1.setTag(null);
	    list2.add(ch1_1);
	    ExpandListChild ch1_2 = new ExpandListChild();
	    ch1_2.setName("An other movie");
	    ch1_2.setTag(null);
	    list2.add(ch1_2);
	    ExpandListChild ch1_3 = new ExpandListChild();
	    ch1_3.setName("And an other movie");
	    ch1_3.setTag(null);
	    list2.add(ch1_3);
	    gru1.setItems(list2);
	    list2 = new ArrayList<ExpandListChild>();
	
	    ExpandListGroup gru2 = new ExpandListGroup();
	    gru2.setName("Action");
	    ExpandListChild ch2_1 = new ExpandListChild();
	    ch2_1.setName("A movie");
	    ch2_1.setTag(null);
	    list2.add(ch2_1);
	    ExpandListChild ch2_2 = new ExpandListChild();
	    ch2_2.setName("An other movie");
	    ch2_2.setTag(null);
	    list2.add(ch2_2);
	    ExpandListChild ch2_3 = new ExpandListChild();
	    ch2_3.setName("And an other movie");
	    ch2_3.setTag(null);
	    list2.add(ch2_3);
	    gru2.setItems(list2);
	    list.add(gru1);
	    list.add(gru2);
	    return list;
	}
}
