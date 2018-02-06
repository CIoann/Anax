package com.example.conniezalo.anax;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccountForgetPasswordActivity extends Activity {
	Button bRetrievePassword;
	EditText etRetrieveEmail;
	private JSONParser jp1 = new JSONParser();
	private ProgressDialog pDialog;
	String frgEmail = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_forget_password);
		initVariables();
		frgEmail = etRetrieveEmail.getText().toString();
		bRetrievePassword.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				frgEmail = etRetrieveEmail.getText().toString();
				if (frgEmail.length() >= 4) {
					new ForgetPassword().execute();
					Toast.makeText(getApplicationContext(), "Password send",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getApplicationContext(), "Invalid Input",
							Toast.LENGTH_SHORT).show();

				}

			}
		});

	}

	private void initVariables() {
		etRetrieveEmail = (EditText) findViewById(R.id.etForgetEmail);
		bRetrievePassword = (Button) findViewById(R.id.bsendForgetEmail);
	}

	class ForgetPassword extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(AccountForgetPasswordActivity.this);
			pDialog.setMessage("Searching for password...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
/*
			int success = 0;
			try {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair(
						AccountInfo.COLUMN_ACCOUNT_EMAIL, frgEmail));
				JSONObject json = jp1.makeHttpRequest(
						AccountInfo.url_FORGET_PASSWORD, "POST", params);
				success = json.getInt(AccountInfo.TAG_SUCCESS);
				if (success == 0) {
					Log.d("hello", "asdf");

				} else
					Log.d("found", "hello");
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
