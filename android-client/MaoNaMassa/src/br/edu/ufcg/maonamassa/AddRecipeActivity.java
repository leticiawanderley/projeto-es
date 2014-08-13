package br.edu.ufcg.maonamassa;

import java.util.List;

import br.edu.ufcg.maonamassa.models.Recipe;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.service.textservice.SpellCheckerService.Session;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class AddRecipeActivity extends ActionBarActivity
implements AddIngredientDialog.IngredientDialogListener{
	
	private Recipe newRecipe = new Recipe(0L, null, null);//manager.getUserDetails());
	private ListView ingredientsView;
	private IngredientsAdapter ingredientsAdapter;
	private String novoIngrediente = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_recipe);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
			.add(R.id.container_add_receita, new PlaceholderFragment()).commit();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_recipe, menu);
		return true;
	}

	public void addIngrediente(View view){
		DialogFragment dialog = new AddIngredientDialog();
		dialog.show(getFragmentManager(), "AddIngredientDialog");
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



	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.primary_recipe_fragment,
					container, false);
			return rootView;
		}
	}



	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		
		if(!(novoIngrediente.trim().length() < 2)){
			newRecipe.addIngredient(novoIngrediente);
			ingredientsView = (ListView) findViewById(R.id.new_ingredient_list);
			ingredientsAdapter = new IngredientsAdapter(this,
					newRecipe.getIngredients());
			ingredientsView.setAdapter(ingredientsAdapter);
		}
		
	}
	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		dialog.dismiss();
		
	}

}
