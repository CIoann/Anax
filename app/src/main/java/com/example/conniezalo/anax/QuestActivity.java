package com.example.conniezalo.anax;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestActivity extends Activity {
	
	ProgressDialog pDialog;
	JSONParser jp = new JSONParser();
	Button bAcceptQuest;
	Button bRejectQuest;
	private int qid;
	private int current_qid;
	private QuestList qList;
	private String userOnline = null;
	TextView tvPlace;
	TextView tvDescription;
	TextView tvReward;
	TextView tvTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quest);
		initVariables();

		bAcceptQuest.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// make the assignment User - Quest.
				Intent intent = new Intent();
				Bundle a = new Bundle();
				a.putInt("result", 1);
				a.putInt("qid", qid);
				a.putInt("listqid", current_qid);
				Log.d("current qid ",qList.get(current_qid).getTitle().toString() + current_qid );
				a.putString("acceptedTitle", qList.get(current_qid).getTitle()
						.toString());
				intent.putExtras(a);
				setResult(RESULT_OK, intent);
				new AddQuestToUser().execute();

			}
		});
		bRejectQuest.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				Bundle a = new Bundle();
				a.putInt("accepted", 0);
				a.putString("acceptedTitle", "");
				intent.putExtras(a);
				setResult(RESULT_OK, intent);
				finish();
				return;
			}
		});
	}

	private void initVariables() {
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		tvReward = (TextView) findViewById(R.id.tvReward);
		tvDescription = (TextView) findViewById(R.id.tvDescription);
		tvPlace = (TextView) findViewById(R.id.tvPlace);
		qList = getIntent().getExtras().getParcelable("qlist");
		qid = getIntent().getExtras().getInt("qid");
		userOnline = MyApplication.getUserOnline();
		current_qid = getIntent().getExtras().getInt("list_qid");
		tvTitle.setText(qList.get(current_qid).getTitle().toString());
		tvReward.setText("" + qList.get(current_qid).getReward());
		tvDescription.setText(qList.get(current_qid).getDescription()
				.toString());
		tvPlace.setText(qList.get(current_qid).getAddress().toString());

		bAcceptQuest = (Button) findViewById(R.id.bAcceptQuest);
		bRejectQuest = (Button) findViewById(R.id.bRejectQuest);
	}

	class AddQuestToUser extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(QuestActivity.this);
			pDialog.setMessage("Saving Quest...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
			finish();/*
			int success = 0;
			try {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("user_id", userOnline));
				params.add(new BasicNameValuePair("quest_id", "" + qid));
				params.add(new BasicNameValuePair("utq_date", "" + 1234));

				JSONObject json = jp.makeHttpRequest(RewardInfo.url_utq_addq,
						"POST", params);

				success = json.getInt(QuestInfo.TAG_SUCCESS);
				if (success == 1) {
					Log.d("Successfully added to utq", json.toString());
					finish();
					return json.getString(QuestInfo.TAG_MESSAGE);

				} else {
					Log.d("Failure to add the quest!",
							json.getString(QuestInfo.TAG_MESSAGE));
					return json.getString(QuestInfo.TAG_MESSAGE);

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
