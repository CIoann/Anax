package com.example.conniezalo.anax;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class RewardExchangeActivity extends Activity {

	private RewardList rList;
	private String[] rtitles;
	private String[] rcategories;
	private int[] rpoints;
	private String[] rdes;
	private String userOnline;
	private ListView mylist;

	public void onCreate(Bundle myBundle) {
		super.onCreate(myBundle);
		setContentView(R.layout.activity_exchange);
		initVariables();
		userOnline = getIntent().getExtras().getString("user_id");
		mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.d("selected item", "id  " + id + " " + "position"
						+ position);

				Log.d("the item title", "id  " + rList.get((int) id).getTitle());
				int rid = (int) id;

				Bundle reward_extras = new Bundle();

				reward_extras.putParcelable("rList", rList);
				reward_extras.putInt("rid", rid);
				reward_extras.putString("user_id", userOnline);

				Intent toRewardShow = new Intent(getApplicationContext(),
						RewardActivity.class);
				toRewardShow.putExtras(reward_extras);
				startActivity(toRewardShow);
				finish();

			}
		});

	}

	private void initVariables() {
		Bundle extras = getIntent().getExtras(); // Get the intent's extras
		rList = extras.getParcelable("rList"); // get our quest list
		rtitles = new String[rList.size()];
		rpoints = new int[rList.size()];
		rdes = new String[rList.size()];
		rcategories = new String[rList.size()];
		mylist = (ListView) findViewById(R.id.mylist);
		mylist.setAdapter(new RewardAdapter(getApplicationContext(), rtitles,
				rdes, rcategories));

		for (int i = 0; i < rList.size(); i++) {
			rdes[i] = rList.get(i).getDescription().toString();
			rpoints[i] = rList.get(i).getReward();
			rtitles[i] = rList.get(i).getTitle().toString();
			rcategories[i] = rList.get(i).getCategory().toString();

		}
	}
	

}
