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

public class RewardActivity extends Activity {
	private RewardList rList;

	private int rid = -1;
	private JSONParser jp = null;
	private ProgressDialog pDialog;

	Intent returnREAIntent;
	Intent refreshRListIntent;
	Bundle returnREABundle;
	Bundle refreshRListBundle;

	private TextView tvTitleRwd;
	private TextView tvPlaceRwd;
	private TextView tvDesRwd;
	private TextView tvPointsRwd;

	private Button btnRejectRwd;
	private Button btnAcceptRwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reward);
		initVariables();
		setUpView();
		btnAcceptRwd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				prepareRefresh();
				new AddRewardToUser().execute();
			}
		});
		btnRejectRwd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				prepareReturn();
			}
		});

	}

	private void initVariables() {

		jp = new JSONParser();

		tvTitleRwd = (TextView) findViewById(R.id.tVtitleReward);
		tvPlaceRwd = (TextView) findViewById(R.id.tVplaceReward);
		tvDesRwd = (TextView) findViewById(R.id.tVdesReward);
		tvPointsRwd = (TextView) findViewById(R.id.tVpointsReward);

		btnAcceptRwd = (Button) findViewById(R.id.bAcceptReward);
		btnRejectRwd = (Button) findViewById(R.id.bRejectReward);

	}

	private void setUpView() {
		Bundle rds_extra = getIntent().getExtras();
		rList = rds_extra.getParcelable("rList");

		rid = rds_extra.getInt("rid");
		tvTitleRwd.setText(rList.get(rid).getTitle().toString());
		tvPlaceRwd.setText(rList.get(rid).getTitle().toString());
		tvDesRwd.setText(rList.get(rid).getDescription().toString());
		tvPointsRwd.setText("" + rList.get(rid).getReward());
	}

	private void prepareReturn() {

		returnREAIntent = new Intent(getApplicationContext(),
				RewardExchangeActivity.class);
		returnREABundle = new Bundle();
		returnREABundle.putParcelable("rList", rList);
		returnREAIntent.putExtras(returnREABundle);
		finish();
		startActivity(returnREAIntent);

	}

	private void prepareRefresh() {
		refreshRListIntent = new Intent(getApplicationContext(),
				GameInitializeRewardsActivity.class);
		refreshRListBundle = new Bundle();
		refreshRListBundle.putString("from", MyApplication._RAc_);
		refreshRListIntent.putExtras(refreshRListBundle);
	}

	class AddRewardToUser extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(RewardActivity.this);
			pDialog.setMessage("Applying rewards...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... args) {


			finish();
			startActivity(refreshRListIntent);
/*
			int success = 0;
			try {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair(RewardInfo.COLUMN_UTR_UID, MyApplication.getUserOnline()));
				params.add(new BasicNameValuePair(RewardInfo.COLUMN_UTR_RID, ""
						+ rList.get(rid).getRID()));
				params.add(new BasicNameValuePair(RewardInfo.COLUMN_UTR_DATE_START, "" + 1234));
				params.add(new BasicNameValuePair(RewardInfo.COLUMN_UTR_TITLE, rList.get(rid).getTitle()));
				params.add(new BasicNameValuePair(RewardInfo.COLUMN_UTR_DESCRIPTION, rList.get(rid).getDescription()));
				params.add(new BasicNameValuePair(RewardInfo.COLUMN_UTR_CATEGORY, rList.get(rid).getCategory()));
				params.add(new BasicNameValuePair(RewardInfo.COLUMN_UTR_POINTS, rList.get(rid).getReward()+""));
				params.add(new BasicNameValuePair(RewardInfo.COLUMN_UTR_COMPANY, "" + 1));
				params.add(new BasicNameValuePair(RewardInfo.COLUMN_UTR_DATE_END, "" + 1234));

				JSONObject json = jp.makeHttpRequest(
						RewardInfo.url_rewards_addrewarduser, "POST", params);

				success = json.getInt(RewardInfo.TAG_SUCCESS);
				if (success == 1) {
					Log.d("Successfully added Reward Now send message",
							json.toString());
					finish();
					startActivity(refreshRListIntent);
					return json.getString(RewardInfo.TAG_MESSAGE);

				} else {
					Log.d("Failure to Accept Reward the quest!",
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
	@Override
	public void onBackPressed() {
		prepareReturn();
	}
}
