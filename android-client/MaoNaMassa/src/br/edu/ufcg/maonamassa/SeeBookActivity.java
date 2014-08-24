package br.edu.ufcg.maonamassa;

import java.util.ArrayList;
import java.util.List;
import br.edu.ufcg.maonamassa.models.Recipe;
import br.edu.ufcg.maonamassa.models.RecipeBook;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

@SuppressLint("Recycle")
public class SeeBookActivity extends ActionBarActivity {
	private TypedArray menuIcons;
	private ListView recipeList;
	private LazyAdapter adapter;
	private List<RowItem> rowItems;
	private SessionManager session;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_see_book);
		session = new SessionManager(getApplicationContext());
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container_book, new PlaceholderFragment()).commit();
		}
		RecipeBook book = session.getBook();
		List<Recipe> recipes = book.getRecipes();
		menuIcons = getResources().obtainTypedArray(R.array.icons);
		rowItems = new ArrayList<RowItem>();
		for (int i = 0; i < recipes.size(); i++) {
			RowItem items = new RowItem(recipes.get(i).getName(), 
					menuIcons.getResourceId(i, -1));
			rowItems.add(items);
		}
		System.out.println(recipes.size());
		recipeList = (ListView) findViewById(R.id.book_list2);
		adapter = new LazyAdapter(getApplicationContext(), recipes);
		recipeList.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.see_book, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_see_book,
					container, false);
			return rootView;
		}
	}

}
