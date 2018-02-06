package com.example.conniezalo.anax;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class AccountTOUActivity extends Activity {

	
	private String username = null;
	Bundle toGameInitUQA;
	private QuestList qList = null;
	private RewardList rList = null;
	private Intent toPlayGame, cancelTOU;
	Button bAcceptTOU;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_tou);
		transferedVariables();
		initVariables();
		
		bAcceptTOU.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				startActivity(toPlayGame);
				
			}
		});
		
	}
	
	private void initVariables(){
		bAcceptTOU = (Button)findViewById(R.id.bacceptTOU);
	}
	private void transferedVariables() {
		Bundle infoReceived = getIntent().getExtras();
		qList = infoReceived.getParcelable("qList");
		rList = infoReceived.getParcelable("rList");
		toGameInitUQA = new Bundle();
		toGameInitUQA.putParcelable("qList", qList);
		toGameInitUQA.putParcelable("rList", rList);
		toPlayGame = new Intent(getApplicationContext(),
				GameInitializeUsersQuestActivity.class);
		toPlayGame.putExtras(toGameInitUQA);
		cancelTOU = new Intent(getApplicationContext(),
				GameInitializeLogInActivity.class);
		cancelTOU.putExtras(toGameInitUQA);
		username = getIntent().getExtras().getString("username");
		toPlayGame.putExtra("userOnline", username);
		Log.d("username",""+username);
	}
	@Override
	public void onBackPressed() {
		finish();
		startActivity(cancelTOU);
	}
}
