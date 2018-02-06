package com.example.conniezalo.anax;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AccountRewardActivity extends ListActivity {
	private ProgressDialog pDialog;
	private JSONParser jp;
	private ListAdapter adapter;
	private JSONArray rewards = null;
private ListView lsRewards;
	private ArrayList<HashMap<String, String>> inboxList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_reward);
		lsRewards = (ListView)findViewById(R.id.lsUserRewards);
		jp = new JSONParser();
		inboxList = new ArrayList<HashMap<String, String>>();
		new LoadAccountRewards().execute();
		
		
	}

	class LoadAccountRewards extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(AccountRewardActivity.this);
			pDialog.setMessage("Loading Rewards ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
			// Building Parameters
	//		int success;
//			List<NameValuePair> params = new ArrayList<NameValuePair>();
//			params.add(new BasicNameValuePair("u_id", MyApplication.getUserOnline()));
//
//			JSONObject json = jp.makeHttpRequest(RewardInfo.url_utr_show,
//					"POST", params);
			HashMap<String, String> map;
			for(int i=0; i<3; i++) {
//			try {
//				success = json.getInt(RewardInfo.TAG_SUCCESS);
//			if (success == 1) {
//				Log.d("successfully received data", json.toString());
//
//				rewards = json.getJSONArray("utr");
//
//					for (int i = 0; i < rewards.length(); i++) {
//						JSONObject c = rewards.getJSONObject(i);
				String rid = "1";// c.getString(RewardInfo.COLUMN_UTR_RID);
				String rtitle = "Reward"; //c
				String rdes = "description";
				String rcat = "bar";
				String endDate = "10.10.17";
//								.getString(RewardInfo.COLUMN_UTR_TITLE);
//						String rdes = c
//								.getString(RewardInfo.COLUMN_UTR_DESCRIPTION);
//						String rcat = c
//								.getString(RewardInfo.COLUMN_UTR_CATEGORY);
//						String endDate = c
//								.getString(RewardInfo.COLUMN_UTR_DATE_END);
//

				// creating new HashMap
				map = new HashMap<String, String>();

				map.put(RewardInfo.COLUMN_UTR_RID, rid);
				map.put(RewardInfo.COLUMN_UTR_TITLE, rtitle);
				map.put(RewardInfo.COLUMN_UTR_DESCRIPTION, rdes);
				map.put(RewardInfo.COLUMN_UTR_CATEGORY, rcat);
				map.put(RewardInfo.COLUMN_UTR_DATE_END, endDate);

				inboxList.add(map);
			}
					//}
				
			//}
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
			if (inboxList.size()==0){
				map = new HashMap<String,String>();
				map.put(RewardInfo.COLUMN_UTR_RID, "0");
				map.put(RewardInfo.COLUMN_UTR_TITLE, "No Rewards Found");
				map.put(RewardInfo.COLUMN_UTR_DESCRIPTION, "");
				map.put(RewardInfo.COLUMN_UTR_CATEGORY, "Cinema");
				map.put(RewardInfo.COLUMN_UTR_DATE_END, "000");
				inboxList.add(map);
			}

			return null;
		}

		protected void onPostExecute(String file_url) {
			pDialog.dismiss();

			runOnUiThread(new Runnable() {
				public void run() {
					 adapter = new SimpleAdapter(
							AccountRewardActivity.this, inboxList,
							R.layout.ls_reward_item, new String[] {
									RewardInfo.COLUMN_UTR_TITLE,
									RewardInfo.COLUMN_UTR_DESCRIPTION,
									RewardInfo.COLUMN_UTR_DATE_END
									},
							new int[] { R.id.from, R.id.subject, R.id.date });
					// updating listview
					setListAdapter(adapter);
				}
			});

		}

	}
}
