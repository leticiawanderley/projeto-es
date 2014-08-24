package br.edu.ufcg.maonamassa;

import java.util.ArrayList;
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
import br.edu.ufcg.maonamassa.models.RecipeBook;
import br.edu.ufcg.maonamassa.utils.HttpURLCon;
import br.edu.ufcg.maonamassa.utils.Routes;

public class BookFragment extends Fragment {

	ListView recipesView;
	SessionManager session;
	ProgressDialog dialog;

	
	public BookFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		session = new SessionManager(getActivity());
		View rootView = inflater
				.inflate(R.layout.fb_fragment, container, false);
		recipesView = (ListView) rootView.findViewById(R.id.listView1);
		dialog = ProgressDialog.show(this.getActivity(), "",
				"Carregando gostosuras...", true);
		openRecipes();
		
		
		
		return rootView;
	}

	private void openRecipes() {
		RecipeBook book = session.getBook();
		List<Recipe> recipes = book.getRecipes();
		System.out.println(recipes.size());
		final LazyAdapter adapter = new LazyAdapter(getActivity(), recipes);
		recipesView.setAdapter(adapter);
		recipesView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				seeRecipeDetails(adapter.getItem(position));

			}
		});
		dialog.cancel();
	}


	private void seeRecipeDetails(Recipe recipe) {
		Intent intent = new Intent(this.getActivity(), SeeRecipeActivity.class);
		intent.putExtra("Recipe", recipe.jsonify());
		startActivity(intent);

	}

}
