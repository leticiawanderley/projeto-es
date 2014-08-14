package br.edu.ufcg.maonamassa;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.edu.ufcg.maonamassa.models.Step;

public class StepsAdapter extends BaseAdapter {
	private Activity activity;
	private List<Step> data;
	private static LayoutInflater inflater = null;

	public StepsAdapter(Activity a, List<Step> your_array_list) {
		activity = a;
		data = your_array_list;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public String getItem(int position) {
		return data.get(position).getDescription();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	@SuppressLint("InflateParams")
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.ingredientlayout, null);

		TextView newIngredient = (TextView) vi
				.findViewById(R.id.new_ingredient); // ingrediente

		String ingredient = getItem(position);

		// Setting all values in listview
		newIngredient.setText(ingredient);
		return vi;
	}
}
