package br.edu.ufcg.maonamassa;

import br.edu.ufcg.maonamassa.AddRecipeActivity.PlaceholderFragment;
import br.edu.ufcg.maonamassa.models.Recipe;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SeeRecipeActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_colecao);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container_ver_receita, new PlaceholderFragment()).commit();
		}
		
		Intent i = getIntent();
		Recipe recipe = new Recipe(0L, "NULL", null);
		recipe = recipe.desjsonify(i.getStringExtra("Recipe"));
		
		TextView title = (TextView)findViewById(R.id.textView1); // title
        TextView author = (TextView)findViewById(R.id.textView2); // author name
        title.setText(recipe.getName());
        author.setText(recipe.getAuthor().getName());

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
