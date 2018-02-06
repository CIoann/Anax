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
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AccountRegisterActivity extends Activity {
	private final static String _NO_ERROR_ = "NO ERROR";
	private final static String _ERROR_PHONE_ = "ERROR PHONE";
	private final static String _ERROR_NAME_ = "ERROR USERNAME";
	private final static String _ERROR_UNDEFINED_ = "ERROR UNDEFINED";

	private JSONParser jp1 = new JSONParser();
	private ProgressDialog pDialog;

	private Button bsubmit, bcancel;
	private EditText etName, etPhone, etPassword, etEmail;
	private TextView tVemail, tVname, tVphone, tVpassword;
	private Bundle registerInfo;
	Bundle toGameInitUQA;
	private QuestList qList = null;
	private RewardList rList = null;
	private Intent toPlayTOU;
	private CheckBox cbRecEmail;

	private AccountInfo newUser;

	private String name = null;
	private String password = null;
	private String email = null;
	private int phone = 0;
	private boolean recEmail = false;

	private Intent cancelRegister;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_register);
		transferedVariables();
		initVariables();

		bsubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				readValues();
				if (checkValues()) {
					gatherInfo();
					new CreateNewUser().execute();
				}
			}
		});
		bcancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				startActivity(cancelRegister);

			}
		});

	}

	private void transferedVariables() {
		Bundle infoReceived = getIntent().getExtras();
		qList = infoReceived.getParcelable("qList");
		rList = infoReceived.getParcelable("rList");
		toGameInitUQA = new Bundle();
		toGameInitUQA.putParcelable("qList", qList);
		toGameInitUQA.putParcelable("rList", rList);
		toPlayTOU = new Intent(getApplicationContext(),
				AccountTOUActivity.class);
		toPlayTOU.putExtras(toGameInitUQA);
		cancelRegister = new Intent(getApplicationContext(),
				GameInitializeLogInActivity.class);
		cancelRegister.putExtras(toGameInitUQA);
	}

	private void initVariables() {
		cbRecEmail = (CheckBox) findViewById(R.id.cbRecEmail);
		tVname = (TextView) findViewById(R.id.tVname);
		tVphone = (TextView) findViewById(R.id.tVphone);
		tVpassword = (TextView) findViewById(R.id.tVpassword);
		tVemail = (TextView) findViewById(R.id.tVemail);
		bcancel = (Button) findViewById(R.id.bcancel);
		bsubmit = (Button) findViewById(R.id.bsubmit);
		etName = (EditText) findViewById(R.id.etName);
		etPassword = (EditText) findViewById(R.id.etPassword);
		etPhone = (EditText) findViewById(R.id.etPhone);
		etEmail = (EditText) findViewById(R.id.etEmail);

	}

	private void readValues() {
		String txtphone = etPhone.getText().toString();
		name = etName.getText().toString();
		password = etPassword.getText().toString();
		email = etEmail.getText().toString();

		if (txtphone.equals("")) {
			phone = 0;
		} else {
			phone = Integer.parseInt(txtphone);
		}

		if (cbRecEmail.isChecked()) {
			recEmail = true;
		}
	}

	private void gatherInfo() {
		registerInfo = new Bundle();
		registerInfo.putString("username", name);
		registerInfo.putString("password", password);
		registerInfo.putInt("phone", phone);
		registerInfo.putString("email", email);
		registerInfo.putBoolean("recEmail", recEmail);
		newUser = new AccountInfo(0, name, phone, password, email, 1);
	}

	private boolean checkValues() {

		boolean nameOk = false;
		boolean phoneOk = false;
		boolean passwordOk = false;

		if (AccountInfo.validatePhoneLength(phone)) {
			validInput(tVphone, etPhone);
			phoneOk = true;
		} else {
			invalidInput(tVphone, etPhone);

		}
		if (AccountInfo.validateNameLength(name)) {
			validInput(tVname, etName);
			AccountInfo.isUniqueName(name);
			nameOk = true;
		} else {
			invalidInput(tVname, etName);
		}

		if (AccountInfo.validatePasswordLength(password)) {
			validInput(tVpassword, etPassword);
			passwordOk = true;
		} else {
			invalidInput(tVpassword, etPassword);
		}
		boolean emailOk = false;
		if (AccountInfo.validateEmail(email)) {
			validInput(tVemail, etEmail);
			emailOk = true;
		} else {
			invalidInput(tVemail, etEmail);
		}

		if (((passwordOk && nameOk) && (phoneOk && emailOk)))
			return true;
		else
			return false;
	}

	private void validInput(TextView tvValid, EditText etValid) {
		etValid.setHint("");
		tvValid.setTextColor(Color.rgb(256, 256, 256));
	}

	private void invalidInput(TextView tvInvalid, EditText etInvalid) {
		tvInvalid.setTextColor(Color.rgb(200, 0, 0));
		etInvalid.setText("");
		etInvalid.setHint("Invalid input");

		switch (etInvalid.getId()) {
		case R.id.etName: {

			etInvalid.setHint("invalid username");
			break;
		}
		case R.id.etPassword: {

			etInvalid.setHint("invalid pass");
			break;
		}
		case R.id.etPhone: {
			etInvalid.setHint("Invalid phone");
			break;
		}
		case R.id.etEmail: {
			etInvalid.setHint("invalid email");
			break;
		}
		}
	}

	class CreateNewUser extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(AccountRegisterActivity.this);
			pDialog.setMessage("Initializing Account...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
		/*	int success = 0;
			int error = 0;
			try {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair(
						AccountInfo.COLUMN_ACCOUNT_NAME, newUser.getName()));
				params.add(new BasicNameValuePair(
						AccountInfo.COLUMN_ACCOUNT_PASSWORD, ""
								+ newUser.getPassword()));
				params.add(new BasicNameValuePair(
						AccountInfo.COLUMN_ACCOUNT_PHONE, " "
								+ newUser.getPhone()));
				params.add(new BasicNameValuePair(
						AccountInfo.COLUMN_ACCOUNT_EMAIL, newUser.getEmail()));
				MyApplication.setDefault(newUser.getName());
				

				Log.d("request!", "starting");

				JSONObject json = jp1.makeHttpRequest(AccountInfo.url_REGISTER,
						"POST", params);

				success = json.getInt(AccountInfo.TAG_SUCCESS);
				error = json.getInt(AccountInfo.TAG_ERROR);
				if (success == 1) {
					Log.d("User Created!", json.toString());*/
					toPlayTOU.putExtra("username", newUser.getName());
					finish();
					startActivity(toPlayTOU);

					return _NO_ERROR_;
/*
				} else {
					if (error == -1) {

						return _ERROR_NAME_;
					}
					if (error == -2) {
						runOnUiThread(new Runnable() {
							public void run() {
//it shows this one
								Toast.makeText(getApplicationContext(),
										"Invalid Phone", Toast.LENGTH_SHORT)
										.show();
							}
						});

						return _ERROR_PHONE_;
					}
					Log.d("Login Failure!",
							json.getString(AccountInfo.TAG_MESSAGE));
					return _ERROR_UNDEFINED_;

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return _NO_ERROR_;
*/
		}

		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
		}

	}

	@Override
	public void onBackPressed() {
		finish();
		startActivity(cancelRegister);
	}
}
