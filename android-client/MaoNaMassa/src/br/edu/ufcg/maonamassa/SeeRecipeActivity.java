package br.edu.ufcg.maonamassa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import br.edu.ufcg.maonamassa.models.Recipe;
import br.edu.ufcg.maonamassa.models.Step;
import br.edu.ufcg.maonamassa.models.RecipeBook;

public class SeeRecipeActivity extends ActionBarActivity {
	
	private SessionManager session;
	private Menu menu;
	private ListView ingredientsView;
	private ListView stepsView;
	private Recipe recipe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_colecao);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container_ver_receita, new PlaceholderFragment())
					.commit();
		}
		session = new SessionManager(getApplicationContext());
		ingredientsView = (ListView) findViewById(R.id.ingredient_list);
		stepsView = (ListView) findViewById(R.id.step_list);
		Intent i = getIntent();
		recipe = new Recipe(0L, "NULL", null);
		recipe = recipe.desjsonify(i.getStringExtra("Recipe"));
		
		TextView title = (TextView) findViewById(R.id.textView1); // title
		TextView author = (TextView) findViewById(R.id.textView2); // author
		TextView ingredients = (TextView) findViewById(R.id.ingredientes); 
		TextView steps = (TextView) findViewById(R.id.modoDePreparo);
		
		title.setText(recipe.getName());
		author.setText(recipe.getAuthor().getName());
		ingredients.setText("Ingredientes:");
		steps.setText("Modo de preparo:");
		IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(this,
				recipe.getIngredients());
		StepsAdapter stepsAdapter = new StepsAdapter(this, recipe.getSteps());
		ingredientsView.setAdapter(ingredientsAdapter);
		stepsView.setAdapter(stepsAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.menu = menu;
		// Inflate the menu; this adds items to the action bar if it is present.
		
		getMenuInflater().inflate(R.menu.add_colecao, menu);
		//if(session.getBook().searchRecipe(recipe.getName()) != null){
			//menu.findItem(R.id.action_add_to_book).setIcon(R.drawable.ic_star_on);
		//}
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
		} else if (id == R.id.action_botao_mao_na_massa) {
			iniciaMaoNaMassa();
			return true;
		}else if (id == R.id.action_add_to_book) {
			if(session.getUserDetails().getBook().searchRecipe(recipe.getName()) == null){
				menu.findItem(R.id.action_add_to_book).setIcon(R.drawable.ic_star_on);
				session.addRecipeToBook(recipe);
			}
			
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private void iniciaMaoNaMassa() {
		Intent intent = new Intent(this, MaoNaMassaActivity.class);
		intent.putExtra("Recipe", recipe.jsonify());
		startActivity(intent);
	}

	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.see_recipe_fragment,
					container, false);
			return rootView;
		}
	}
}
