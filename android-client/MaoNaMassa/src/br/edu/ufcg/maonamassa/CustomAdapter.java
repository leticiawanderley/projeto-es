package br.edu.ufcg.maonamassa;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

	Context context;
	List<RowItem> rowItem;

	CustomAdapter(Context context, List<RowItem> rowItem) {
		this.context = context;
		this.rowItem = rowItem;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(position == 0) {
			if (convertView == null) {
				LayoutInflater mInflater = (LayoutInflater) context
						.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
				convertView = mInflater.inflate(R.layout.user_info_item, null);
			}
			ImageView imgIcon = (ImageView) convertView.findViewById(R.id.userPhoto);
			TextView txtTitle = (TextView) convertView.findViewById(R.id.userName);
			new ImageDownload(imgIcon).execute("https://lh6.googleusercontent.com/-AlQuyllLocY/AAAAAAAAAAI/AAAAAAAADsw/Q3EK4Y5bvbs/s120-c/photo.jpg");
			RowItem row_pos = rowItem.get(position);
			// setting the image resource and title
			
			txtTitle.setText(row_pos.getTitle());
			
		} else {
			
			if (convertView == null) {
				LayoutInflater mInflater = (LayoutInflater) context
						.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
				convertView = mInflater.inflate(R.layout.list_item, null);
			}	
				ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
				TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
		
				RowItem row_pos = rowItem.get(position);
				// setting the image resource and title
				imgIcon.setImageResource(row_pos.getIcon());
				txtTitle.setText(row_pos.getTitle());
			
		}
		
		return convertView;
	}

	@Override
	public int getCount() {
		return rowItem.size();
	}

	@Override
	public Object getItem(int position) {
		return rowItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		return rowItem.indexOf(getItem(position));
	}

}
