package com.geekytheory.orderregister;



import com.example.android.navigationdrawerexample.R;
import com.geekytheory.orderregister.libraries.AdminSQLiteOpenHelper;
import com.geekytheory.orderregister.libraries.Tools;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	private TextView btnmisdatos;
	private TextView btnhistory;
	private TextView btnaddclient;
	private TextView btnaddorder;
	private TextView btncatalog;
	private TextView btnclientlist;
	//Animaciones
	private Animation shake;
	private Animation scale_inv;
	private Animation rotate;
	private Animation scale;
	
	private Tools tools;
	private AdminSQLiteOpenHelper admin_ma;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0099CC"));
		//getActionBar().setBackgroundDrawable(colorDrawable); 
		setContentView(R.layout.activity_main);
		
		admin_ma = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
		tools = new Tools(getApplicationContext());
		
		btnmisdatos=(TextView)findViewById(R.id.btnmisdatos);
		btnmisdatos.setOnClickListener(this);
		btnhistory=(TextView)findViewById(R.id.btnhistory);
		btnhistory.setOnClickListener(this);
		btnaddclient=(TextView)findViewById(R.id.btnaddclient);
		btnaddclient.setOnClickListener(this);
		btnclientlist=(TextView)findViewById(R.id.btnclientlist);
		btnclientlist.setOnClickListener(this);
		btnaddorder=(TextView)findViewById(R.id.btnaddorder);
		btnaddorder.setOnClickListener(this);
		btncatalog=(TextView)findViewById(R.id.btncatalog);
		btncatalog.setOnClickListener(this);
		
		scale_inv=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scaleinverse);
		rotate=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
		scale=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
		shake=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
		//animacion inicial
		btnclientlist.startAnimation(scale_inv);
		btnhistory.startAnimation(scale);
		btncatalog.startAnimation(rotate);
		btnaddorder.startAnimation(scale);
		btnaddclient.startAnimation(scale_inv);
		btnmisdatos.startAnimation(rotate);
		
		if(admin_ma.checkTablaProducto()){
        	admin_ma.insertProducts();
			tools.showShortMessage("llena tabla producto");
        }
	}

public void onClick(View v) {
	
		
		
        switch (v.getId()) {
        case R.id.btnmisdatos:{	
					        	btnmisdatos.startAnimation(shake);	
					        	shake.setAnimationListener(new AnimationListener() {
				                    public void onAnimationStart(Animation anim)
				                    {
				                    };
				                    public void onAnimationRepeat(Animation anim)
				                    {
				                    };
				                    public void onAnimationEnd(Animation anim)
				                    {
				                    	Intent i=new Intent(getBaseContext(),MyprofileActivity.class);
							        	startActivity(i);
							        	overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);
				                    };
				                });                  
        						
        						}break;
        case R.id.btnaddclient:{
    							btnaddclient.startAnimation(shake);
    							shake.setAnimationListener(new AnimationListener() {
				                    public void onAnimationStart(Animation anim)
				                    {
				                    };
				                    public void onAnimationRepeat(Animation anim)
				                    {
				                    };
				                    public void onAnimationEnd(Animation anim)
				                    {
				                    	Intent i=new Intent(getBaseContext(),AddClientActivity.class);
							        	startActivity(i);
							        	overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);
				                    };
				                });                  
					        	
								}break;
        case R.id.btnaddorder:{
    							btnaddorder.startAnimation(shake);
    							shake.setAnimationListener(new AnimationListener() {
				                    public void onAnimationStart(Animation anim)
				                    {
				                    };
				                    public void onAnimationRepeat(Animation anim)
				                    {
				                    };
				                    public void onAnimationEnd(Animation anim)
				                    {
				                    	Intent i=new Intent(getBaseContext(),AddOrderActivity.class);
							        	startActivity(i);
							        	overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);
				                    };
				                });     
					        	
								}break;
        case R.id.btnclientlist:{
    							btnclientlist.startAnimation(shake);
    							shake.setAnimationListener(new AnimationListener() {
				                    public void onAnimationStart(Animation anim)
				                    {
				                    };
				                    public void onAnimationRepeat(Animation anim)
				                    {
				                    };
				                    public void onAnimationEnd(Animation anim)
				                    {
				                    	Intent i=new Intent(getBaseContext(),ClientListActivity.class);
							        	startActivity(i);
							        	overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);
				                    };
				                });     
    							
					        	
								}break;
        case R.id.btncatalog:{
								btncatalog.startAnimation(shake);
								shake.setAnimationListener(new AnimationListener() {
				                    public void onAnimationStart(Animation anim)
				                    {
				                    };
				                    public void onAnimationRepeat(Animation anim)
				                    {
				                    };
				                    public void onAnimationEnd(Animation anim)
				                    {
				                    	Intent i=new Intent(getBaseContext(),CatalogActivity.class);
							        	startActivity(i);
							        	overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);
				                    };
				                });     
								
					        	
								}break;
        case R.id.btnhistory:{
								btnhistory.startAnimation(shake);
								shake.setAnimationListener(new AnimationListener() {
				                    public void onAnimationStart(Animation anim)
				                    {
				                    };
				                    public void onAnimationRepeat(Animation anim)
				                    {
				                    };
				                    public void onAnimationEnd(Animation anim)
				                    {
				                    	Intent i=new Intent(getBaseContext(),HistoryActivity.class);
							        	startActivity(i);
							        	overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);
				                    };
				                });     
								
					        	
								}break;
        					
        }
	}//finish ONcLICK
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		try {
			overridePendingTransition(R.anim.setanimback_in, R.anim.setanimback_out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
