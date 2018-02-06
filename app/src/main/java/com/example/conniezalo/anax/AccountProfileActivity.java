package com.example.conniezalo.anax;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AccountProfileActivity extends Activity {

	private TextView tvProfileUserName;
	private TextView tvProfileUserLevel;
	private TextView tvProfileUserClass;
	private TextView tvProfileUserPoints;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_profile);
		initVariables();
		
	}
	private void initVariables(){
		tvProfileUserName = (TextView) findViewById(R.id.tvProfileUserName);
		tvProfileUserLevel = (TextView) findViewById(R.id.tvProfileUserLevel);
		tvProfileUserClass = (TextView) findViewById(R.id.tvProfileUserClass);
		tvProfileUserPoints = (TextView) findViewById(R.id.tvProfileUserPoints);
		tvProfileUserName.setText(MyApplication.getUserOnline().toString());
		tvProfileUserPoints.setText("" + MyApplication.getUserRewardPoints());
		tvProfileUserClass.setText(MyApplication.getUserClass().toString());
		tvProfileUserLevel.setText("" + MyApplication.getUserLevel());
	}
}
