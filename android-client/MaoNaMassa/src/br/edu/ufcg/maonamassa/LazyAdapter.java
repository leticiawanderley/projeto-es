package br.edu.ufcg.maonamassa;

import br.edu.ufcg.maonamassa.models.Recipe;

 
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
 
public class LazyAdapter extends BaseAdapter {
 
    private Activity activity;
    private List<Recipe> data;
    private static LayoutInflater inflater=null;
 
    public LazyAdapter(Activity a, List<Recipe> your_array_list) {
        activity = a;
        data=your_array_list;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
    }
 
    @Override
	public int getCount() {
        return data.size();
    }
 
    @Override
	public Recipe getItem(int position) {
        return data.get(position);
    }
 
    @Override
	public long getItemId(int position) {
        return position;
    }
 
    
    
    
    
    @Override
	@SuppressLint("InflateParams")
	public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.rowlayout, null);
 
        TextView title = (TextView)vi.findViewById(R.id.label); // title
        TextView author = (TextView)vi.findViewById(R.id.author); // artist name
        //ImageView thumb_image=(ImageView)vi.findViewById(R.id.icon); // TODO
 
        Recipe recipe = data.get(position);
 
        // Setting all values in listview
        title.setText(recipe.getName());
        author.setText(recipe.getAuthor().getName());
        return vi;
    }
}
