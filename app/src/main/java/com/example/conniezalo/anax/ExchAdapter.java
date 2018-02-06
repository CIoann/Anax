package com.example.conniezalo.anax;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExchAdapter extends ArrayAdapter<String> {
	private final Context context;
	private String[] titles;
	private int[] points;
	private String[] rdes;

	public ExchAdapter(Context context, int[] rpoints, String[] rtitles, String[] rdes) {
		super(context, R.layout.item2, rtitles);
		this.context = context;
		this.titles = rtitles;
		this.points = rpoints;
		this.rdes = rdes;	
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.item2, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.line);
		TextView tx3 = (TextView) rowView.findViewById(R.id.tline);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		TextView tx2 = (TextView) rowView.findViewById(R.id.sline);
		textView.setText(titles[position]);
		tx2.setText(rdes[position]);
		tx3.setText(""+points[position]);
		
		imageView.setImageResource(R.drawable.bexchange);

		return rowView;
	}
}