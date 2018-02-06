package com.example.conniezalo.anax;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class GameInitializeRewardsActivity extends Activity {

	Intent toPlay;
	private RewardList rList = null;
	private JSONParser jp = new JSONParser();
	private ProgressDialog pDialog;
	private QuestList qList = null;
	Bundle toGameInitUQA = null;
	JSONArray allrewards = null;
	private String from = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_initialize1);
		initVariables();
		new CreateRewards().execute();
	}

	private void initVariables() {
	
		toGameInitUQA = new Bundle();
		from = getIntent().getExtras().getString("from");
		
		if (from.equals(MyApplication._GIQA_)){
			Bundle extras = getIntent().getExtras(); // Get the intent's extras
			qList = extras.getParcelable("qList"); // get our quest list
			toGameInitUQA.putParcelable("qList", qList);
		}
		
		
		toPlay = new Intent(getApplicationContext(),
				GameInitializeLogInActivity.class);
		rList = new RewardList();
	}

	class CreateRewards extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(GameInitializeRewardsActivity.this);
			pDialog.setMessage("Creating rewards...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... args) {

			int rid = 0;
			String rtitle = "You can have";
			String rdes = "A free drink";
			int rcrones = 5;
			String rcategory = "1";


			for (int i = 0; i < 10; i++) {
				rid++;

				Rewards rd = new Rewards(rid, rtitle, rdes,
						rcrones, rcategory);
				rList.add(rd);
			}
			Log.d("size", "" + rList.size());

			toGameInitUQA.putParcelable("rList", rList);
			toPlay.putExtras(toGameInitUQA);
			MyApplication.setRList(rList);

			if (from.equals(MyApplication._GIQA_)) {
				finish();
				startActivity(toPlay);

			}
			if (from.equals(MyApplication._RAc_)) {

				Intent toR = new Intent(getApplicationContext(),
						RewardExchangeActivity.class);
				toR.putExtras(toGameInitUQA);
				finish();
				startActivity(toR);
			}



			/*
			Log.d("FROM!!", from);
			try {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				JSONObject json = jp.makeHttpRequest(RewardInfo.url_rewards,
						"GET", params);

				success = json.getInt(QuestInfo.TAG_SUCCESS);
				if (success == 1) {
					Log.d("successfully received data", json.toString());

					allrewards = json.getJSONArray("rs");

					for (int i = 0; i < allrewards.length(); i++) {
						JSONObject c = allrewards.getJSONObject(i);

						rid = c.getInt(RewardInfo.KEY_ID);
						rtitle = c.getString(RewardInfo.COLUMN_REWARD_TITLE);
						rdes = c.getString(RewardInfo.COLUMN_REWARD_DESCRIPTION);
						rcrones = c.getInt(RewardInfo.COLUMN_REWARD_POINTS);
						rcategory = c
								.getString(RewardInfo.COLUMN_REWARD_CATEGORY);
						Rewards reward = new Rewards(rid, rtitle, rdes,
								rcrones, rcategory);
						rList.add(reward);
					}
					Log.d("size", "" + rList.size());

					toGameInitUQA.putParcelable("rList", rList);
					toPlay.putExtras(toGameInitUQA);
					MyApplication.setRList(rList);

					if (from.equals(MyApplication._GIQA_)) {
						finish();
						startActivity(toPlay);

					}
					if (from.equals(MyApplication._RAc_)) {

						Intent toR = new Intent(getApplicationContext(),
								RewardExchangeActivity.class);
						toR.putExtras(toGameInitUQA);
						finish();
						startActivity(toR);
					}

					return json.getString(RewardInfo.TAG_MESSAGE);

				} else {
					Log.d("Login Failure!",
							json.getString(RewardInfo.TAG_MESSAGE));
					return json.getString(RewardInfo.TAG_MESSAGE);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}*/
			return null;
		}

		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
		}

	}
}
