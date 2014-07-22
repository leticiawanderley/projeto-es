package br.edu.ufcg.maonamassa;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import br.edu.ufcg.maonamassa.models.Recipe;

public class SeeRecipeActivity extends ActionBarActivity {
	

	private List<IngredientItem> ingredientItems;
	private IngredientsAdapter adapter;
	private ListView ingredientsView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_colecao);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container_ver_receita, new PlaceholderFragment()).commit();
		}
		
		ingredientsView = (ListView) findViewById(R.id.ingredient_list);
		Intent i = getIntent();
		Recipe recipe = new Recipe(0L, "NULL", null);
		recipe = recipe.desjsonify(i.getStringExtra("Recipe"));
		recipe.addIngredient("novoIngrediente");
		recipe.addIngredient("novoIngrediente2");
		TextView title = (TextView)findViewById(R.id.textView1); // title
        TextView author = (TextView)findViewById(R.id.textView2); // author name
        title.setText(recipe.getName());
        author.setText(recipe.getAuthor().getName());
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(this, recipe.getIngredients());
        ingredientsView.setAdapter(ingredientsAdapter); 

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_colecao, menu);
		return true;
	}
	
	
	 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.see_recipe_fragment, container,
					false);
			return rootView;
		}
	}
}
