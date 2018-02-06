package com.example.conniezalo.anax;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ExchangeActivity extends ListActivity {

	private final static int CODE_REWARD = 1;
	private RewardList rList;
	private String[] rtitles;
	private int[] rpoints;
	private String[] rdes;

	public void onCreate(Bundle myBundle) {
		super.onCreate(myBundle);
		Bundle extras = getIntent().getExtras(); // Get the intent's extras
		rList = extras.getParcelable("rds"); // get our quest list
		rtitles = new String[rList.size()];
		rpoints = new int[rList.size()];
		rdes = new String[rList.size()];

		Log.d("REWARDS", rList.get(1).getRID()
				+ rList.get(0).getTitle().toString());
		for (int i = 0; i < rList.size(); i++) {
			rdes[i] = rList.get(i).getDescription().toString();
			rpoints[i] = rList.get(i).getReward();
			rtitles[i] = rList.get(i).getTitle().toString();

		}

		ExchAdapter adapter = new ExchAdapter(this, rpoints, rtitles, rdes);
		setListAdapter(adapter);
	}

	private int getRewardsId(String item) {
		int rid = -1;
		for (int j = 0; j < rList.size(); j++) {
			if (rList.get(j).getTitle().equals(item)) {
				rid = rList.get(j).getRID();
			}
		}
		Log.d("id reward", "" + rid);
		return rid;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		Toast.makeText(this, item + " selected" + id + position,
				Toast.LENGTH_LONG).show();
		// create a bundle with the item's info.
		int rid = -1;
		rid = getRewardsId(item);

		Bundle reward_extras = new Bundle();

		reward_extras.putParcelable("rList", rList);
		reward_extras.putInt("rid", rid);
		reward_extras.putInt("rListId", (int) id);
		Intent toRewardShow = new Intent(getApplicationContext(),
				RewardActivity.class);
		toRewardShow.putExtras(reward_extras);
		startActivityForResult(toRewardShow, CODE_REWARD);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == CODE_REWARD) {
			int result = data.getExtras().getInt("accepted");

			if (result == 1) {
				Toast.makeText(this, "Reward Accepted ", Toast.LENGTH_SHORT)
						.show();
				// start asycn task to remove the reward from list.
			}
			if (result == 0) {
				Toast.makeText(this, "Reward Rejected ", Toast.LENGTH_SHORT)
						.show();
			}

		}

	}
}
