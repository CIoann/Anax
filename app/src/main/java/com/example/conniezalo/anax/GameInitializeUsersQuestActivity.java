package com.example.conniezalo.anax;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class GameInitializeUsersQuestActivity extends Activity {
	Intent toPlay;
	private RewardList rList = null;
	private JSONParser jp = new JSONParser();
	private ProgressDialog pDialog;
	private QuestList qUserLogList = null;
	private QuestList qList = null;
	private String userOnline = null;
	Bundle toGameInitWA = null;
	JSONArray qLog = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_initialize1);
		initVariables();
		new CreateQuestLog().execute();

	}

	private void initVariables() {
		qUserLogList = new QuestList();
		MyApplication.setUserQList(qUserLogList);
		toPlay = new Intent(getApplicationContext(), GameWelcomeActivity.class);
		Bundle extras = getIntent().getExtras();
		qList = extras.getParcelable("qList");
		rList = extras.getParcelable("rList");
		toGameInitWA = new Bundle();
		toGameInitWA.putParcelable("qList", qList);
		toGameInitWA.putParcelable("rList", rList);
		userOnline = getIntent().getExtras().getString("userOnline");

	}

	class CreateQuestLog extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(GameInitializeUsersQuestActivity.this);
			pDialog.setMessage("Saving Quest...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... args) {

			int qid = 0;
			String qtitle = "This is a quest";
			String qdes = "Your task is:";
			Double qlat = 32.623555;
			Double qlon = 34.727535;
			int qreward = 15;
			String qaddress = "Address";
			String qcategory = "2";

			for (int i = 0; i < 1; i++) {
				Quest nq = new Quest(qid, qtitle, qdes, qlat, qlon,
						qreward, qaddress, qcategory);
				qid++;
				qlat+= 0.0003;
				qlon+= 0.0007;

			//	qUserLogList.add(nq);
			}
			toGameInitWA.putString("user_id", userOnline);
			toGameInitWA.putParcelable("qUserLogList", qUserLogList);
			toPlay.putExtras(toGameInitWA);
			MyApplication.setUserQList(qUserLogList);
			finish();
			startActivity(toPlay);




		/*	int success = 0;
			try {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("user_id", userOnline));

				JSONObject json = jp.makeHttpRequest(QuestInfo.url_QUESTLOG,
						"POST", params);

				success = json.getInt(QuestInfo.TAG_SUCCESS);
				if (success == 1) {

					qLog = json.getJSONArray("questJOIN");

					for (int i = 0; i < qLog.length(); i++) {
						JSONObject c = qLog.getJSONObject(i);

						qid = c.getInt(QuestInfo.KEY_ID);
						qtitle = c.getString(QuestInfo.COLUMN_TITLE);
						qdes = c.getString(QuestInfo.COLUMN_DESCRIPTION);
						qlat = c.getDouble(QuestInfo.COLUMN_LATITUDE);
						qlon = c.getDouble(QuestInfo.COLUMN_LONGITUDE);
						qreward = c.getInt(QuestInfo.COLUMN_REWARD);
						qaddress = c.getString(QuestInfo.COLUMN_ADDRESS);
						qcategory = c.getString(QuestInfo.COLUMN_CAT);

						Quest nq = new Quest(qid, qtitle, qdes, qlat, qlon,
								qreward, qaddress, qcategory);

						qUserLogList.add(nq);
					}
					Log.d("at least i tried","");
					toGameInitWA.putString("user_id", userOnline);
					toGameInitWA.putParcelable("qUserLogList", qUserLogList);
					toPlay.putExtras(toGameInitWA);
					MyApplication.setUserQList(qUserLogList);
					finish();
					startActivity(toPlay);

				} else {
					toGameInitWA.putString("user_id", userOnline);
					toGameInitWA.putParcelable("qUserLogList", qUserLogList);
					toPlay.putExtras(toGameInitWA);
					finish();
					startActivity(toPlay);
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
