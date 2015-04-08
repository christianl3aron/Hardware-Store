package com.geekytheory.orderregister;

import com.example.android.navigationdrawerexample.R;
import com.geekytheory.orderregister.libraries.AdminSQLiteOpenHelper;
import com.geekytheory.orderregister.libraries.Tools;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddClientFragment extends Fragment implements OnClickListener {
	
	private EditText txtempresa_ac;
	private EditText txtruc_ac;
	private EditText txtdepartamento_ac;
	private EditText txtprovincia_ac;
	private EditText txtdistrito_ac;
	private EditText txtdireccion_ac;
	private EditText txtnombrecliente_ac;
	private EditText txtappaterno_ac;
	private EditText txtapmaterno_ac;
	private EditText txtdni_ac;
	private EditText txtcelular_ac;
	private EditText txttelefono_ac;
	private Button btncancel_ac;
	private Button btnok_ac;
    private ImageButton btnclear;
    
    private Tools tools;
    private View acRootView;
    private AdminSQLiteOpenHelper admin_ac;
	
	public AddClientFragment() {}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		acRootView = inflater.inflate(R.layout.fragment_addclient, container, false);
		admin_ac = new AdminSQLiteOpenHelper(getActivity(),"administracion", null, 1);
		tools = new Tools(getActivity().getApplicationContext());
		
		txtempresa_ac=(EditText) acRootView.findViewById(R.id.txtnombre_empresa);
		txtruc_ac=(EditText) acRootView.findViewById(R.id.txtruc_empresa);
		txtdepartamento_ac=(EditText) acRootView.findViewById(R.id.txtdepartamento_empresa);
		txtprovincia_ac=(EditText) acRootView.findViewById(R.id.txtprovincia_empresa);
		txtdistrito_ac=(EditText) acRootView.findViewById(R.id.txtdistrito_empresa);
		txtdireccion_ac=(EditText) acRootView.findViewById(R.id.txtdireccion_empresa);
		txtnombrecliente_ac=(EditText) acRootView.findViewById(R.id.txtnombre_cliente);
		txtappaterno_ac=(EditText) acRootView.findViewById(R.id.txtappaterno_cliente);
		txtapmaterno_ac=(EditText) acRootView.findViewById(R.id.txtapmaterno_cliente);
		txtdni_ac=(EditText) acRootView.findViewById(R.id.txtdni_cliente);
		txtcelular_ac=(EditText) acRootView.findViewById(R.id.txtcelular_cliente);
		txttelefono_ac=(EditText) acRootView.findViewById(R.id.txttelefono_cliente);

		btncancel_ac=(Button) acRootView.findViewById(R.id.btncancelar_addclient);
		btnok_ac=(Button) acRootView.findViewById(R.id.btnok_addclient);
		btnclear=(ImageButton) acRootView.findViewById(R.id.btnclear);
		btncancel_ac.setOnClickListener(this);
		btnok_ac.setOnClickListener(this);
		btnclear.setOnClickListener(this);
	    
		return acRootView;
	}


	public void onClick(View v) {
		 switch (v.getId()) {
         case R.id.btnok_addclient:{save();}break;
         case R.id.btncancelar_addclient:{getActivity().finish(); }break;
         case R.id.btnclear:{clearAll();}break;
         }
	}
	
	/**
	 * 
	 * 
	 * PRINCIPAL METHODS
	 * 
	 * 
	 * */
	
	//Logica de almacenamiento de datos.
	private void save(){
		if (checkTextBoxes()) {
					if (admin_ac.checkClientesExistentes(txtdni_ac.getText().toString()) && admin_ac.checkEmpresasExistentes(txtruc_ac.getText().toString())) {
						saveClient();
						saveEmpresa();
						activateIntent();
					} else if(!admin_ac.checkClientesExistentes(txtdni_ac.getText().toString()) && admin_ac.checkEmpresasExistentes(txtruc_ac.getText().toString())) {
						saveEmpresa();
						activateIntent();
						tools.showShortMessage("el cliente ya existe!");
					}else{
						tools.showShortMessage("el cliente ya existe!");
						tools.showShortMessage("la empresa ya existe!");
					}
		}else tools.showShortMessage("complete todo los campos");
	}
	
	//Almacena datos del cliente.
	private void saveClient(){
			try {
				ContentValues registroCliente = new ContentValues();
				registroCliente.put("nombre", txtnombrecliente_ac.getText().toString().toLowerCase());
				registroCliente.put("paterno", txtappaterno_ac.getText().toString().toLowerCase());
				registroCliente.put("materno", txtapmaterno_ac.getText().toString().toLowerCase());
				registroCliente.put("dni", txtdni_ac.getText().toString());
				registroCliente.put("telefono", txttelefono_ac.getText().toString());
				registroCliente.put("celular", txtcelular_ac.getText().toString());
				admin_ac.insertInTable("cliente", registroCliente);
				tools.showShortMessage("CLIENTE GUARDADO!");
			} catch (Exception e) {
				e.printStackTrace();
				tools.showShortMessage("Error de almacenamiento de cliente: "+e.getMessage());
			}	
		}	
	
	//almacena datos de la empresa. 
	private void saveEmpresa(){
			try {
				ContentValues registroEmpresa = new ContentValues();
				registroEmpresa.put("empresa", txtempresa_ac.getText().toString().toLowerCase());
				registroEmpresa.put("ruc", txtruc_ac.getText().toString());
				registroEmpresa.put("departamento", txtdepartamento_ac.getText().toString().toLowerCase());
				registroEmpresa.put("provincia", txtprovincia_ac.getText().toString().toLowerCase());
				registroEmpresa.put("distrito", txtdistrito_ac.getText().toString().toLowerCase());
				registroEmpresa.put("direccion", txtdireccion_ac.getText().toString());
				registroEmpresa.put("cliente", txtdni_ac.getText().toString());
				admin_ac.insertInTable("empresa", registroEmpresa);
				tools.showShortMessage("EMPRESA GUARDADA!");
			} catch (Exception e) {
				e.printStackTrace();
				tools.showShortMessage("Error de almacenamiento de empresa: "+e.getMessage());
			}		
	}
	
	/**
	 * 
	 * 
	 * SECUNDARY METHODS
	 * 
	 * 
	 * */
	
	//verifica si los EditTexts estan vacios.
	private boolean checkTextBoxes(){
    	return (!(txtempresa_ac.getText().toString().equals("")) && 
    			!(txtruc_ac.getText().toString().equals("")) && 
    			!(txtdepartamento_ac.getText().toString().equals("")) && 
    			!(txtprovincia_ac.getText().toString().equals("")) && 
    			!(txtdistrito_ac.getText().toString().equals("")) && 
    			!(txtdireccion_ac.getText().toString().equals("")) && 
    			!(txtnombrecliente_ac.getText().toString().equals("")) &&
    			!(txtappaterno_ac.getText().toString().equals("")) && 
    			!(txtapmaterno_ac.getText().toString().equals("")) && 
    			!(txtdni_ac.getText().toString().equals("")) && 
    			!(txtcelular_ac.getText().toString().equals("")) && 
    			!(txttelefono_ac.getText().toString().equals(""))) ;
    }
	
	private void clearAll(){
		txtempresa_ac.setText("");
		txtruc_ac.setText("");
		txtdepartamento_ac.setText("");
		txtprovincia_ac.setText("");
		txtdistrito_ac.setText("");
		txtdireccion_ac.setText("");
		txtnombrecliente_ac.setText("");
		txtappaterno_ac.setText("");
		txtapmaterno_ac.setText("");
		txtdni_ac.setText("");
		txtcelular_ac.setText("");
		txttelefono_ac.setText("");
	}
	
	//Activa intent, envia a lista de clientes.
	private void activateIntent(){
		Intent i=new Intent(getActivity(),ClientListActivity.class);
 		startActivity(i);
 		getActivity().overridePendingTransition(R.anim.setanim_in_fragment, R.anim.setanim_out_fragment);
		
	}

}
