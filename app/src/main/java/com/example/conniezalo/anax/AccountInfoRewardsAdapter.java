package com.example.conniezalo.anax;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AccountInfoRewardsAdapter extends ArrayAdapter<String> {
	private final Context context;
	private String[] titles;
	private String[] des;
	private String[] cat;

	public AccountInfoRewardsAdapter(Context context, String[] userRewardtitles, String[] userRewarddes,
			String[] userRewardcat) {
		super(context, R.layout.item_airr,userRewardtitles);
		this.context = context;
		this.titles = userRewardtitles;
		this.des = userRewarddes;
		this.cat = userRewardcat;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.item_ra, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.userline);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		TextView tx2 = (TextView) rowView.findViewById(R.id.usersline);
		textView.setText(titles[position]);
		tx2.setText(des[position]);

		imageView.setImageResource(Quest.configureCategoryAllQuest(
				cat[position], false));

		return rowView;
	}
}