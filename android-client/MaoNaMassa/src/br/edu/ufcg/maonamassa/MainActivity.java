package br.edu.ufcg.maonamassa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	 MenuInflater inflater = getMenuInflater();
    	 inflater.inflate(R.menu.main, menu);
    	 return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
        case R.id.action_add_recipe:
        	addRecipe();
        	return true;
        case R.id.action_settings:
            openSettings();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }


	private void addRecipe() {
		Intent intent = new Intent(this, AddRecipeActivity.class);
	    startActivity(intent);
		
	}


	private void openSettings() {
		// TODO Auto-generated method stub
		
	}




}
