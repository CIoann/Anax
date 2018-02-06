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

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class GameInitializeLogInActivity extends Activity {
	private ProgressDialog pd;
	private JSONParser jp = new JSONParser();
	private ImageButton bRegister;
	private Button bLogIn;
	private Button bForgetPassword;
	private EditText etUserName;
	private EditText etUserPassword;
	Bundle toGameInitUQA;
	private String username;
	private String userpass;
	private QuestList qList = null;
	private RewardList rList = null;
	private Intent toRegister;
	private Intent toPlayGame;
	private Intent toForgotPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_initialize_log_in);
		initInfo();
		initVariables();
		bLogIn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (vLogIn()) {
					new LogInVerification().execute();
				} else {
					Toast.makeText(getApplicationContext(), "Invalid Input",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		bRegister.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				toRegister.putExtras(toGameInitUQA);
				finish();
				startActivity(toRegister);
			}
		});
		bForgetPassword.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(toForgotPassword);
			}
		});

	}

	private void initInfo() {
		Bundle extras = getIntent().getExtras();
		qList = extras.getParcelable("qList");
		rList = extras.getParcelable("rList");
		toGameInitUQA = new Bundle();
		toGameInitUQA.putParcelable("qList", qList);
		toGameInitUQA.putParcelable("rList", rList);

	}

	private void initVariables() {
		bRegister = (ImageButton) findViewById(R.id.imgbRegister);
		bForgetPassword = (Button) findViewById(R.id.bForgotPassword);
		bLogIn = (Button) findViewById(R.id.blogin);
		etUserName = (EditText) findViewById(R.id.etUsername);
		etUserPassword = (EditText) findViewById(R.id.etUserPassword);
		username = null;
		userpass = null;
		toForgotPassword = new Intent(getApplicationContext(),
				AccountForgetPasswordActivity.class);
		toRegister = new Intent(getApplicationContext(),
				AccountRegisterActivity.class);
		toPlayGame = new Intent(getApplicationContext(),
				GameInitializeUsersQuestActivity.class);
	}

	private boolean vLogIn() {
		username = etUserName.getText().toString();
		userpass = etUserPassword.getText().toString();
		if ((!username.equals(null)) && (!username.equals(""))) {
			if (username.length() <= 3) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	class LogInVerification extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = new ProgressDialog(GameInitializeLogInActivity.this);
			pd.setMessage("Processing...");
			pd.setIndeterminate(false);
			pd.setCancelable(false);
			pd.show();
		}

		@Override
		protected String doInBackground(String... args) {

			toPlayGame.putExtra("userOnline", username);
			toPlayGame.putExtras(toGameInitUQA);

			finish();
			startActivity(toPlayGame);
/*
			int success = 0;
			Log.d("user", username);
			Log.d("pass", userpass);
			try {

				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("USERNAME", username));
				params.add(new BasicNameValuePair("PASSWORD", userpass));

				JSONArray userInfo;
				JSONObject json = jp.makeHttpRequest(AccountInfo.url_LOGIN,
						"POST", params);
				userInfo = json.getJSONArray("users");
				for (int i = 0; i < userInfo.length(); i++) {
					JSONObject c = userInfo.getJSONObject(i);
					MyApplication.setUserOnline(c
							.getString(AccountInfo.COLUMN_ACCOUNT_NAME));
					MyApplication.setUserRewardPoints(c
							.getInt(AccountInfo.COLUMN_ACCOUNT_REWARD_POINTS));
					MyApplication.setUserClass(c.getString(AccountInfo.COLUMN_ACCOUNT_CLASS));
					MyApplication.setUserLevel(c.getInt(AccountInfo.COLUMN_ACCOUNT_LVL));
				}

				success = json.getInt(AccountInfo.TAG_SUCCESS);
				if (success == 1) {

					Log.d("Login Successful!", json.toString());
					toPlayGame.putExtra("userOnline", username);
					toPlayGame.putExtras(toGameInitUQA);

					finish();
					startActivity(toPlayGame);
					return json.getString(AccountInfo.TAG_MESSAGE);
				} else {
					Log.d("Login Failure!",
							json.getString(AccountInfo.TAG_MESSAGE));
					return json.getString(AccountInfo.TAG_MESSAGE);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
*/
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
		}

	}
}
