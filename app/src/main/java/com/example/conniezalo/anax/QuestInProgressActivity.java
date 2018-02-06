package com.example.conniezalo.anax;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestInProgressActivity extends Activity {

	private final static int _ABANDON_QUEST_ = 0;
	private final static int _NO_CHANGE_ = 1;
	private final static int _INSERT_CODE_ = 2;
	
	private ProgressDialog pDialog;
	private JSONParser jp;
	private QuestList qList = null;

	private int remove_lqid = -1;
	private TextView tvQIPTitle;
	private TextView tvQIPDes;
	private TextView tvQIPRwd;
	private TextView tvQIPPlace;
	private Button bInsertCode;
	private Button bAbandonQuest;

	Intent rmQtoMapViewIntent;
	Bundle rmQtoMapViewBundle;
	
	Intent toInsertCodeIntent;
	
	Intent noCtoMapViewIntent;
	Bundle noCtoMapviewBundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quest_in_progress);
		jp = new JSONParser();
		initVariables();
		toInsertCodeIntent = new Intent(getApplicationContext(),
				QuestInProgressInsertCodeActivity.class);

		bInsertCode.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(toInsertCodeIntent);

			}
		});
		bAbandonQuest.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				abandonQuest();

			}
		});

	}

	private void initVariables() {
		qList = new QuestList();

		qList = getIntent().getExtras().getParcelable("qlist");
		remove_lqid = getIntent().getExtras().getInt("list_qid");
		Log.d("lqid", remove_lqid + "");

		tvQIPTitle = (TextView) findViewById(R.id.tvQIPTitle);
		tvQIPDes = (TextView) findViewById(R.id.tvQIPDescription);
		tvQIPRwd = (TextView) findViewById(R.id.tvQIPReward);
		tvQIPPlace = (TextView) findViewById(R.id.tvQIPPlace);

		bInsertCode = (Button) findViewById(R.id.bInsertBarCode);
		bAbandonQuest = (Button) findViewById(R.id.bAbandonQuest);

		tvQIPTitle.setText(qList.get(remove_lqid).getTitle().toString());
		tvQIPDes.setText(qList.get(remove_lqid).getDescription().toString());
		tvQIPPlace.setText(qList.get(remove_lqid).getAddress().toString());
		tvQIPRwd.setText("" + qList.get(remove_lqid).getReward());

	}

	private void prepareAbandonQuest() {
		rmQtoMapViewIntent = new Intent();
		rmQtoMapViewBundle = new Bundle();
		rmQtoMapViewBundle.putInt("result", _ABANDON_QUEST_);
		rmQtoMapViewBundle.putInt("removed_list", remove_lqid);
		rmQtoMapViewIntent.putExtras(rmQtoMapViewBundle);
	}

	private void updateAbandonQuest(){
		
	}
	private void prepareNoChange(){
		noCtoMapviewBundle = new Bundle();
		noCtoMapViewIntent = new Intent(getApplicationContext(),MapViewActivity1.class);
		noCtoMapviewBundle.putInt("result", _NO_CHANGE_);
		noCtoMapViewIntent.putExtras(noCtoMapviewBundle);
	}
	
	
	private void abandonQuest() {

		Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Do you really want to Abandon this Quest?");
		builder.setCancelable(true);
		builder.setPositiveButton("Abandon", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				updateAbandonQuest();
				new AbandonQuest().execute();
			}
		});
		builder.setNegativeButton("Cancel", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				return;
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();

	}

	class AbandonQuest extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(QuestInProgressActivity.this);
			pDialog.setMessage("Removing Quest...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... args) {

			prepareAbandonQuest();
			setResult(RESULT_OK, rmQtoMapViewIntent);
			finish();
/*
			int success = 0;
			try {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("user_id", MyApplication
						.getUserOnline()));

				params.add(new BasicNameValuePair("quest_id", ""
						+ qList.get(remove_lqid).getId()));

				JSONObject json = jp.makeHttpRequest(
						QuestInfo.url_RemoveQuestFromUser, "POST", params);

				success = json.getInt(QuestInfo.TAG_SUCCESS);
				if (success == 1) {
					Log.d("Successfully REMOVED QUEST", json.toString());
					prepareAbandonQuest();
					setResult(RESULT_OK, rmQtoMapViewIntent);
					finish();

					return json.getString(QuestInfo.TAG_MESSAGE);

				} else {
					Log.d("Failure to REMOVE the quest!",
							json.getString(QuestInfo.TAG_MESSAGE));
					return json.getString(QuestInfo.TAG_MESSAGE);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			*/
			return null;
		}

		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
		}
	}

	@Override
	public void onBackPressed() {
		prepareNoChange();
		setResult(RESULT_OK,noCtoMapViewIntent );
		finish();
	}
}
