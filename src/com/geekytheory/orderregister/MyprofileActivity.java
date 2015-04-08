package com.geekytheory.orderregister;

import com.example.android.navigationdrawerexample.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MyprofileActivity extends Activity {
    private DrawerLayout mpDrawerLayout;
    private ListView mpDrawerList;
    private ActionBarDrawerToggle mpDrawerToggle;
    private String[] mpOptionTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        mpOptionTitles = getResources().getStringArray(R.array.myprofile_layouts_array);
        mpDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mpDrawerList = (ListView) findViewById(R.id.left_drawer);

        mpDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mpDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mpOptionTitles));
        mpDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mpDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mpDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mpDrawerLayout.setDrawerListener(mpDrawerToggle);

        if (savedInstanceState == null) {
        	Fragment fragment = new MyprofileFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);

            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mpDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch(item.getItemId()) {
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        	 
             switch (position){
             case 0:
             		{ Intent i=new Intent(MyprofileActivity.this,AddOrderActivity.class);
             		  startActivity(i);
             		 overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);}break;
             case 1:
			      	{ Intent i=new Intent(MyprofileActivity.this,HistoryActivity.class);
			   		  startActivity(i);
			   		overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);}break;
             case 2:
		             { Intent i=new Intent(MyprofileActivity.this,CatalogActivity.class);
			   		  startActivity(i);
			   		overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);}break;
             case 3:
             		{ Intent i=new Intent(MyprofileActivity.this,AddClientActivity.class);
             		startActivity(i);
             		overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);}break;
             case 4:
             		{ Intent i=new Intent(MyprofileActivity.this,ClientListActivity.class);
             		startActivity(i);
             		overridePendingTransition(R.anim.setanim_in, R.anim.setanim_out);}break;
             }
             
             mpDrawerList.setItemChecked(position, true);
             mpDrawerLayout.closeDrawer(mpDrawerList);
             finish();
        }
    }

   
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mpDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mpDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**public void onBackPressed()
    {
        // Close
        finish();
    }
    
    
     * Fragment that appears in the "content_frame", shows a planet
     */
}
// acaba el MYPROFILE ACTIVITY ACTIVITY
