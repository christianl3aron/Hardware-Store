package com.geekytheory.orderregister;

import com.example.android.navigationdrawerexample.R;
import com.geekytheory.orderregister.libraries.AdminSQLiteOpenHelper;
import com.geekytheory.orderregister.libraries.Tools;

import android.app.Dialog;
import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


public class MyprofileFragment extends Fragment implements OnClickListener  {
    
	private EditText txtnombre_mp;
	private EditText txtappaterno_mp;
	private EditText txtapmaterno_mp;
	private EditText txtcodigo_mp;
	private EditText txttelefono_mp;
	private EditText txtemail_mp;
	private EditText txtpass_mp;
	private EditText txtconf_mp;
	private Button btncancelar_mp;
	private Button btnok_mp;
	private Button btncambiarpass_mp;
	private Button btncambiardatos_mp;
	private LinearLayout botonesecu_mp;
    
    private Tools tools;      
    private View mpRootView;
    private AdminSQLiteOpenHelper admin_mp;
    
    
    public MyprofileFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
    	
        mpRootView = inflater.inflate(R.layout.fragment_myprofile, container, false);
        admin_mp = new AdminSQLiteOpenHelper(getActivity(),"administracion", null, 1);
        tools = new Tools(getActivity().getApplicationContext());
        
        txtnombre_mp=(EditText) mpRootView.findViewById(R.id.txtnombre);
        txtappaterno_mp=(EditText) mpRootView.findViewById(R.id.txtapaterno);
        txtapmaterno_mp=(EditText) mpRootView.findViewById(R.id.txtapmaterno);
        txtcodigo_mp=(EditText) mpRootView.findViewById(R.id.txtcodigo);
        txttelefono_mp=(EditText) mpRootView.findViewById(R.id.txttelefono);
        txtemail_mp=(EditText) mpRootView.findViewById(R.id.txtemail);
        txtpass_mp=(EditText) mpRootView.findViewById(R.id.txtpass);
        txtconf_mp=(EditText) mpRootView.findViewById(R.id.txtconf);
        //Botones.
        botonesecu_mp=(LinearLayout) mpRootView.findViewById(R.id.botonessecumyprofile);
        btncancelar_mp=(Button) mpRootView.findViewById(R.id.btncancelar);
        btnok_mp=(Button) mpRootView.findViewById(R.id.btnok);
        btncambiarpass_mp=(Button) mpRootView.findViewById(R.id.btncambiarpass);
        btncambiardatos_mp=(Button) mpRootView.findViewById(R.id.btncambiardatos);
        btnok_mp.setOnClickListener(this);
        btncancelar_mp.setOnClickListener(this);
        btncambiarpass_mp.setOnClickListener(this);
        btncambiardatos_mp.setOnClickListener(this);
     
        if(admin_mp.checkTablaMisdatos()){
        	changeButtonState();
        	fillAllEditText();
        }
        return mpRootView;
    }
    
    public void onClick(View v) {
            switch (v.getId()) {
            case R.id.btnok:{saveData();}break;
            case R.id.btncancelar:{getActivity().finish(); }break;
            case R.id.btncambiarpass:{changePassDialog();}break;
            case R.id.btncambiardatos:{changeData();}break;
            }
    }
    
    /**
	 * 
	 * 
	 * PRINCIPAL METHODS
	 * 
	 * 
	 * */
    
    //Metodo muestro los datos en los Edittexts.
    private void fillAllEditText(){
    	Cursor filas=admin_mp.getSelect("select * from misdatos");
    	if(filas.moveToFirst()){
    		txtnombre_mp.setText(tools.titleize(filas.getString(0)));
    		txtappaterno_mp.setText(tools.titleize(filas.getString(1)));
    		txtapmaterno_mp.setText(tools.titleize(filas.getString(2)));
    		txttelefono_mp.setText(filas.getString(3));
    		txtemail_mp.setText(filas.getString(4));
    		txtcodigo_mp.setText(filas.getString(5));
    		txtpass_mp.setText(filas.getString(6));
    		txtconf_mp.setText(filas.getString(6));
    	}
    }
    
    //Guarda datos del vendedor
    private void saveData(){		
    	
    	if(matchPass() && checkTextBoxes() && !admin_mp.checkTablaMisdatos()){
			try {
				insertInTable( txtpass_mp.getText().toString(),"Datos guardados! ");
    			changeButtonState();
    		}catch (Exception e) {
    			tools.showShortMessage("Error en guardar! "+e.getMessage());
    		}
		}else{
				String message="";
				if(!matchPass()) message="los campos de contraseña no coinciden";
				else if(!checkTextBoxes()) message="complete todos los campos"; 
				tools.showShortMessage(message);
				txtconf_mp.setText("");
			}
    }
    
    //Metodo que guarda los nuevos datos.
    private void changeData(){
    	String current_pass=getCurrentPass();
    	if(checkTextBoxes()){
			try {
				admin_mp.executeSQL("drop table misdatos");
	        	admin_mp.executeSQL("create table misdatos(nombre text, paterno text, materno text, telefono text, email text, codigo text primary key, pass text, ruta text)");
	        	insertInTable( current_pass,"Datos actualizados! ");
    			//changeButtonState();
    		}catch (Exception e) {
    			tools.showShortMessage("Error en actulizar! "+e.getMessage());
    		}} 	
    }
    
    //Metodo que inserta en la tabla los datos de los EditText.
    private void insertInTable(String contrasena, String mensaje){
    	ContentValues registro = new ContentValues();
        registro.put("nombre", txtnombre_mp.getText().toString().toLowerCase());
        registro.put("paterno", txtappaterno_mp.getText().toString().toLowerCase());
        registro.put("materno", txtapmaterno_mp.getText().toString().toLowerCase());
        registro.put("telefono", txttelefono_mp.getText().toString());
        registro.put("email", txtemail_mp.getText().toString());
        registro.put("codigo", txtcodigo_mp.getText().toString());
        registro.put("pass", contrasena);
        registro.put("ruta", "nadaaun");
        admin_mp.insertInTable("misdatos", registro);
        tools.showShortMessage(mensaje);
    }
    
    /**
	 * 
	 * 
	 * SECUNDARY METHODS
	 * 
	 * 
	 * */
    
    private boolean matchPass(){
		return (txtpass_mp.getText().toString()).equals(txtconf_mp.getText().toString());	
    }
    
    private boolean checkTextBoxes(){
    	return (!(txtnombre_mp.getText().toString().equals("")) && 
    			!(txtappaterno_mp.getText().toString().equals("")) && 
    			!(txtapmaterno_mp.getText().toString().equals("")) && 
    			!(txttelefono_mp.getText().toString().equals("")) && 
    			!(txtemail_mp.getText().toString().equals("")) && 
    			!(txtcodigo_mp.getText().toString().equals("")) && 
    			!(txtpass_mp.getText().toString().equals("")) && 
    			!(txtconf_mp.getText().toString().equals(""))) ;
    }
    
    private void changeButtonState(){
    	btnok_mp.setVisibility(View.INVISIBLE);
    	Drawable salir = mpRootView.getResources().getDrawable( R.drawable.salir );
    	btncancelar_mp.setCompoundDrawablesWithIntrinsicBounds(salir, null, null, null);
    	botonesecu_mp.setVisibility(View.VISIBLE);
    	txtpass_mp.setEnabled(false);
    	txtconf_mp.setEnabled(false);
    }
    
    //Funcion que retorna una backup de la base de datos.
    private String getCurrentPass(){
		Cursor filas=admin_mp.getSelect("select * from misdatos");
    	if(filas.moveToFirst()) return filas.getString(6);
    	else return" ";
    }
    
    /**
	 * 
	 * 
	 * SPECIAL CODE
	 * 
	 * 
	 * */

    //Dialogo personalizado
    private void changePassDialog(){
    	
    	final Dialog dialog = new Dialog(getActivity());
		dialog.setContentView(R.layout.dialog_changepass);
		dialog.setTitle("Cambiar Contraseña");
		dialog.setCancelable(false);
		
		Button btncancel_dialog=(Button) dialog.findViewById(R.id.btncancel_dialog);
		btncancel_dialog.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {	dialog.dismiss();	}});
		
		Button btnok_dialog=(Button) dialog.findViewById(R.id.btnok_dialog);
		btnok_dialog.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText txtnewpass_dialog=(EditText) dialog.findViewById(R.id.txtnewpass_dialog);
				EditText txtconf_dialog=(EditText) dialog.findViewById(R.id.txtconf_dialog);
				EditText txtpass_dialog=(EditText) dialog.findViewById(R.id.txtpass_dialog);
				
				String newpass=txtnewpass_dialog.getText().toString();
				String conf=txtconf_dialog.getText().toString();
				String pass=txtpass_dialog.getText().toString();
				String passbd=getCurrentPass();
	   
				if(newpass.equals(conf) && !newpass.equals("") && !conf.equals("") && !pass.equals("") && pass.equals(passbd)){
					try {
						//String query = "update misdatos set pass='"+ newpass + "' where pass='" + passbd + "'";
						admin_mp.executeSQL("update misdatos set pass='"+ newpass + "' where pass='" + passbd + "'");
						tools.showShortMessage("CONTRASEÑA CAMBIADA CON EXITO!");
						dialog.dismiss();
					} catch (Exception e) {
						tools.showShortMessage("Error de actualizacion"+e.getMessage());
					}
				}
				else{
					String message="";
					if(newpass.equals("") || conf.equals("") || pass.equals("")) message="complete todos los campos";
					else if (!newpass.equals(conf)){ message="la nueva contraseña no coincide";
													txtconf_dialog.setText("");}
					else if(!pass.equals(passbd)) {message="la contraseña anterior es incorrecta";
													txtpass_dialog.setText("");} 
					tools.showShortMessage(message);			
				}
			}
		});
			
		dialog.show();
    }
    
}
