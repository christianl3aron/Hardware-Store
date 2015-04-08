package com.geekytheory.orderregister;

import com.example.android.navigationdrawerexample.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AddOrderActivity extends Activity {
	
	private DrawerLayout aoDrawerLayout;
    private ListView aoDrawerList;
    private ActionBarDrawerToggle aoDrawerToggle;

    private String[] aoOptionTitles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_order);
		
		//mTitle = mDrawerTitle = getTitle();
	       aoOptionTitles = getResources().getStringArray(R.array.ao_layouts_array);
	       aoDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_ao);
	       aoDrawerList = (ListView) findViewById(R.id.left_drawer_ao);

	       // set a custom shadow that overlays the main content when the drawer opens
	       aoDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
	       // set up the drawer's list view with items and click listener
	       aoDrawerList.setAdapter(new ArrayAdapter<String>(this,
	               R.layout.drawer_list_item, aoOptionTitles));
	       aoDrawerList.setOnItemClickListener(new DrawerItemClickListener());

	       // enable ActionBar app icon to behave as action to toggle nav drawer
	       getActionBar().setDisplayHomeAsUpEnabled(true);
	       getActionBar().setHomeButtonEnabled(true);

	       // ActionBarDrawerToggle ties together the the proper interactions
	       // between the sliding drawer and the action bar app icon
	       aoDrawerToggle = new ActionBarDrawerToggle(
	               this,                  /* host Activity */
	               aoDrawerLayout,         /* DrawerLayout object */
	               R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
	               R.string.drawer_open,  /* "open drawer" description for accessibility */
	               R.string.drawer_close  /* "close drawer" description for accessibility */
	               ) {
	           public void onDrawerClosed(View view) {
	               //getActionBar().setTitle(mTitle);
	               invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
	           }

	           public void onDrawerOpened(View drawerView) {
	               //getActionBar().setTitle(mDrawerTitle);
	               invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
	           }
	       };
	       aoDrawerLayout.setDrawerListener(aoDrawerToggle);

	       if (savedInstanceState == null) {
	       	Fragment fragment = new AddOrderFragment();
	           Bundle args = new Bundle();
	           fragment.setArguments(args);

	           FragmentManager fragmentManager = getFragmentManager();
	           fragmentManager.beginTransaction().replace(R.id.content_frame_ao, fragment).commit();
	       }
	       
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_order, menu);
		return true;
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (aoDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
	 private class DrawerItemClickListener implements ListView.OnItemClickListener {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        	 
	             switch (position){
	             case 0:{ Intent i=new Intent(AddOrderActivity.this,HistoryActivity.class);
	             		  startActivity(i);
	             		 overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);}break;
	             case 1:{ Intent i=new Intent(AddOrderActivity.this,CatalogActivity.class);
				   		  startActivity(i);
				   		  overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);}break;
	             case 2:{ Intent i=new Intent(AddOrderActivity.this,MyprofileActivity.class);
	             		startActivity(i);
	             		overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);}break;
	             case 3:{ Intent i=new Intent(AddOrderActivity.this,AddClientActivity.class);
	             		startActivity(i);
	             		overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);}break;
	             case 4:{ Intent i=new Intent(AddOrderActivity.this,ClientListActivity.class);
		   		  		startActivity(i);
	   		  			overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);}break;
	             }
	             
	             aoDrawerList.setItemChecked(position, true);
	             aoDrawerLayout.closeDrawer(aoDrawerList);
	             finish();
	        }
	    }
	 
	 @Override
	    protected void onPostCreate(Bundle savedInstanceState) {
	        super.onPostCreate(savedInstanceState);
	        // Sync the toggle state after onRestoreInstanceState has occurred.
	        aoDrawerToggle.syncState();
	    }

	    @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	        // Pass any configuration change to the drawer toggls
	        aoDrawerToggle.onConfigurationChanged(newConfig);
	    }
}
