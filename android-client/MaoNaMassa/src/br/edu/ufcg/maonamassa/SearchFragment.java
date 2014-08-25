package br.edu.ufcg.maonamassa;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.edu.ufcg.maonamassa.models.Recipe;
import br.edu.ufcg.maonamassa.utils.HttpURLCon;
import br.edu.ufcg.maonamassa.utils.Routes;

public class SearchFragment extends Fragment {

	ListView recipesView;

	
	public SearchFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater
				.inflate(R.layout.fb_fragment, container, false);
		recipesView = (ListView) rootView.findViewById(R.id.listView1);
		ProgressDialog dialog = ProgressDialog.show(this.getActivity(), "",
				"Carregando gostosuras...", true);
		Intent data = this.getActivity().getIntent();
		if(data.hasExtra("query")){
			new HttpAsyncTask(this.getActivity(), dialog, data.getStringExtra("query")).execute();
		} else {
			new HttpAsyncTask(this.getActivity(), dialog).execute();
		}
		data.removeExtra("query");
		
		
		
		return rootView;
	}

	private class HttpAsyncTask extends AsyncTask<String, Void, String> {

		private Activity that;
		private ProgressDialog dialog;
		private String query;

		public HttpAsyncTask(Activity that, ProgressDialog dialog, String query) {
			this.that = that;
			this.dialog = dialog;
			this.query = query;
		}
		
		public HttpAsyncTask(Activity that, ProgressDialog dialog) {
			this.that = that;
			this.dialog = dialog;
			this.query="";
		}

		@Override
		protected String doInBackground(String... urls) {

			return HttpURLCon
					.GET(Routes.SERVER_URL + "/" + Routes.SEARCH_ROUTE + "?query=" + query);
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			// Toast.makeText(getBaseContext(), "Received!",
			// Toast.LENGTH_LONG).show();
			Recipe recipe = new Recipe(0L, "NULL", null);
			List<Recipe> listOfRecipes = recipe.desjsonifyList(result);

			final LazyAdapter recipeAdapter = new LazyAdapter(that,
					listOfRecipes);
			recipesView.setAdapter(recipeAdapter);
			recipesView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					seeRecipeDetails(recipeAdapter.getItem(position));

				}
			});
			dialog.cancel();
		}
	}

	private void seeRecipeDetails(Recipe recipe) {
		Intent intent = new Intent(this.getActivity(), SeeRecipeActivity.class);
		intent.putExtra("Recipe", recipe.jsonify());
		startActivity(intent);

	}

}
