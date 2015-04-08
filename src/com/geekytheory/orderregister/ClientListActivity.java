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

public class ClientListActivity extends Activity {

	private DrawerLayout clDrawerLayout;
    private ListView clDrawerList;
    private ActionBarDrawerToggle clDrawerToggle;

    private String[] clOptionTitles;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_client_list);
		
		 //mTitle = mDrawerTitle = getTitle();
       clOptionTitles = getResources().getStringArray(R.array.cl_layouts_array);
       clDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_cl);
       clDrawerList = (ListView) findViewById(R.id.left_drawer_cl);

       // set a custom shadow that overlays the main content when the drawer opens
       clDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
       // set up the drawer's list view with items and click listener
       clDrawerList.setAdapter(new ArrayAdapter<String>(this,
               R.layout.drawer_list_item, clOptionTitles));
       clDrawerList.setOnItemClickListener(new DrawerItemClickListener());

       // enable ActionBar app icon to behave as action to toggle nav drawer
       getActionBar().setDisplayHomeAsUpEnabled(true);
       getActionBar().setHomeButtonEnabled(true);

       // ActionBarDrawerToggle ties together the the proper interactions
       // between the sliding drawer and the action bar app icon
       clDrawerToggle = new ActionBarDrawerToggle(
               this,                  /* host Activity */
               clDrawerLayout,         /* DrawerLayout object */
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
       clDrawerLayout.setDrawerListener(clDrawerToggle);

       if (savedInstanceState == null) {
       	Fragment fragment = new ClientListFragment();
           Bundle args = new Bundle();
           fragment.setArguments(args);

           FragmentManager fragmentManager = getFragmentManager();
           fragmentManager.beginTransaction().replace(R.id.content_frame_cl, fragment).commit();
       }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.client_list, menu);
		return true;
	}


	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (clDrawerToggle.onOptionsItemSelected(item)) {
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
	             case 0:{ Intent i=new Intent(ClientListActivity.this,AddOrderActivity.class);
	             		  startActivity(i);
	             		 overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);}break;
	             case 1:{ Intent i=new Intent(ClientListActivity.this,HistoryActivity.class);
				   		  startActivity(i);
				   		  overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);}break;
	             case 2:{ Intent i=new Intent(ClientListActivity.this,CatalogActivity.class);
				   		  startActivity(i);
				   		overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);}break;
	             case 3:{ Intent i=new Intent(ClientListActivity.this,MyprofileActivity.class);
	             		startActivity(i);
	             		overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);}break;
	             case 4:{ Intent i=new Intent(ClientListActivity.this,AddClientActivity.class);
	             		startActivity(i);
	             		overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);}break;
	             }
	             
	             clDrawerList.setItemChecked(position, true);
	             clDrawerLayout.closeDrawer(clDrawerList);
	             finish();
	        }
	    }
	 
	 @Override
	    protected void onPostCreate(Bundle savedInstanceState) {
	        super.onPostCreate(savedInstanceState);
	        // Sync the toggle state after onRestoreInstanceState has occurred.
	        clDrawerToggle.syncState();
	    }

	    @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	        // Pass any configuration change to the drawer toggls
	        clDrawerToggle.onConfigurationChanged(newConfig);
	    }
	    
	    public void onBackPressed() {

	        finish();
	        overridePendingTransition(R.anim.setanimback_in_fragment, R.anim.setanimback_out_fragment);

	            return;
	   }
}
