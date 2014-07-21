package br.edu.ufcg.maonamassa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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

public class MainActivity extends ActionBarActivity {
	
	
	private ListView recipesView;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // LISTING THE RECIPES
        recipesView = (ListView) findViewById(R.id.listView1);
        
        final List<Recipe> listOfRecipes = new ArrayList<Recipe>();
        User user = new User(23223L, "joopeeds@gmail.com", "Joao Pedro", "3782392jadoasjhdks293823");
		Recipe that = new Recipe(121893792L, "teste", user);
        listOfRecipes.add(that);
        listOfRecipes.add(that);
        listOfRecipes.add(that);
        listOfRecipes.add(that);
        listOfRecipes.add(that);
        listOfRecipes.add(that);
        listOfRecipes.add(that);
        listOfRecipes.add(that);
        listOfRecipes.add(that);
        listOfRecipes.add(that);
        
        LazyAdapter recipeAdapter = new LazyAdapter(this, listOfRecipes);
        recipesView.setAdapter(recipeAdapter); 
        //TODO Fazer essa merda funcionar
        recipesView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					seeRecipeDetails(listOfRecipes.get(position));
				
			}
		});
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
	    startActivity(intent);
		
	}


	private void openSettings() {
		// TODO Auto-generated method stub
		
	}




}
