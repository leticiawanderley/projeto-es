package br.edu.ufcg.maonamassa;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.maonamassa.models.Recipe;
import br.edu.ufcg.maonamassa.models.User;
import br.edu.ufcg.maonamassa.utils.HttpURLCon;
import br.edu.ufcg.maonamassa.utils.Routes;
import br.edu.ufcg.maonamassa.utils.StorableQuery;

public class MainActivity extends ActionBarActivity {
	
	
	private ListView recipesView;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // LISTING THE RECIPES
        recipesView = (ListView) findViewById(R.id.listView1);
		
		new HttpAsyncTask(this).execute();
	    
       
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
	
	private void seeRecipeDetails(Recipe recipe) {
		Intent intent = new Intent(this, SeeRecipeActivity.class);
		intent.putExtra("Recipe", recipe.jsonify());
	    startActivity(intent);
		
	}


	private void openSettings() {
		// TODO Auto-generated method stub
		
	}

private class HttpAsyncTask extends AsyncTask<String, Void, String> {
		
		Activity that;
		
		public HttpAsyncTask(Activity that) {
			this.that = that;
		}
		
	    @Override
	    protected String doInBackground(String... urls) {
	        return HttpURLCon.GET(Routes.SERVER_URL + "/" + Routes.SEARCH_ROUTE);
	    }
	    // onPostExecute displays the results of the AsyncTask.
	    @Override
	    protected void onPostExecute(String result) {
	        //Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
	        Recipe recipe = new Recipe(0L, "NULL", null);
	        List<Recipe> listOfRecipes = recipe.desjsonifyList(result);
	        
	        final LazyAdapter recipeAdapter = new LazyAdapter(that, listOfRecipes);
	        recipesView.setAdapter(recipeAdapter); 
	        recipesView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					   
						seeRecipeDetails( recipeAdapter.getItem(position));
					
				}
			});
	   }
	}
	
	



}
