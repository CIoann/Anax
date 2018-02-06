package com.example.conniezalo.anax;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RewardAdapter extends ArrayAdapter<String> {
	private final Context context;
	private String[] titles;
	private String[] rdes;
	private String[] rcategories;

	public RewardAdapter(Context context, String[] rtitles, String[] rdes,
			String[] rcategories) {
		super(context, R.layout.item_ra, rtitles);
		this.context = context;
		this.titles = rtitles;
		this.rdes = rdes;
		this.rcategories = rcategories;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.item_ra, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.line);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		TextView tx2 = (TextView) rowView.findViewById(R.id.sline);
		textView.setText(titles[position]);
		tx2.setText(rdes[position]);

		imageView.setImageResource(Quest.configureCategoryAllQuest(
				rcategories[position], false));

		return rowView;
	}
}