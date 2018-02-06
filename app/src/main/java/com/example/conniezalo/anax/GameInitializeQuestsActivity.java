package com.example.conniezalo.anax;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class GameInitializeQuestsActivity extends Activity {

	LatLng position_mark;
	private QuestList qList = null;
	private JSONParser jp = new JSONParser();
	private ProgressDialog pDialog;
	JSONArray quests = null;
	Intent toPlay = null;
	private Bundle toGameInitRA = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_initialize);
		initVariables();
		new CreateQuests().execute();
	}

	private void initVariables() {
		qList = new QuestList();
		toGameInitRA = new Bundle();
		toPlay = new Intent(getApplicationContext(),
				GameInitializeRewardsActivity.class);
	}

	class CreateQuests extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(GameInitializeQuestsActivity.this);
			pDialog.setMessage("Initializing The Game...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
			int qid = 0;
			String qtitle = "This is a quest";
			String qdes = "Quest your task is:";
			Double qlat = 32.623555;
			Double qlon = 34.727535;
			int qreward = 0;
			String qaddress = "myaddr";
			String qcategory = "1";


			for (int i = 0; i < 15; i++) {
				qid ++;
				Quest nq = new Quest(qid, qtitle, qdes, qlat, qlon,
						qreward, qaddress, qcategory);
				qList.add(nq);

				qlat-= 0.0002;
				qlon+= 0.0002;
			}
			Log.d("size", "" + qList.size());

			toGameInitRA.putParcelable("qList", qList);
			toGameInitRA.putString("from", MyApplication._GIQA_);

			toPlay.putExtras(toGameInitRA);

			MyApplication.setQList(qList);
			finish();
			startActivity(toPlay);

			return null;

/*
			int success = 0;
			try {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				JSONObject json = jp.makeHttpRequest(QuestInfo.url_quests,
						"GET", params);

				success = json.getInt(QuestInfo.TAG_SUCCESS);
				if (success == 1) {
					Log.d("successfully received data", json.toString());

					quests = json.getJSONArray("qs");

					for (int i = 0; i < quests.length(); i++) {
						JSONObject c = quests.getJSONObject(i);

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
						qList.add(nq);
					}
					Log.d("size", "" + qList.size());

					toGameInitRA.putParcelable("qList", qList);
					toGameInitRA.putString("from",MyApplication._GIQA_);

					toPlay.putExtras(toGameInitRA);
					
					MyApplication.setQList(qList);
					finish();
					//startActivity(toPlay);

					return json.getString(QuestInfo.TAG_MESSAGE);

				} else {
					Log.d("Login Failure!",
							json.getString(QuestInfo.TAG_MESSAGE));
					return json.getString(QuestInfo.TAG_MESSAGE);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

*/
		}
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
		}

	}
}