package com.example.conniezalo.anax;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameWelcomeActivity extends Activity {
	private Button bPlay;
	private Button bSettings;
	private Button bHelp;
	private Button bQuit;
	private Button bCredits;
	private Bundle toMVA;
	Intent toSettings;
	Intent svcBmusic;
	Intent toHelp;
	Intent toCredits;
	Intent toPlay;
	private QuestList qList = null;
	private QuestList qUserLogList = null;
	private RewardList rList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_play);
		initVariables();
		bPlay.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				toPlay.putExtras(toMVA);
				startActivity(toPlay);

			}
		});
		bSettings.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(toSettings);
			}
		});
		bHelp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(toHelp);
			}
		});
		bCredits.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(toCredits);
			}
		});
		bQuit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				exit();
			}
		});

	}

	private void initVariables() {
		bPlay = (Button) findViewById(R.id.bplay);
		bSettings = (Button) findViewById(R.id.bSettings);
		bHelp = (Button) findViewById(R.id.bHelp);
		bQuit = (Button) findViewById(R.id.bQuit);
		bCredits = (Button) findViewById(R.id.bCredits);

		toSettings = new Intent(GameWelcomeActivity.this,
				GameWelcomeSettingsActivity.class);
		toHelp = new Intent(GameWelcomeActivity.this,
				GameWelcomeHelpActivity.class);
		toCredits = new Intent(GameWelcomeActivity.this,
				GameWelcomeCreditsActivity.class);

		toPlay = new Intent(GameWelcomeActivity.this, MapViewActivity1.class);
		Bundle extras = getIntent().getExtras();

		qList = extras.getParcelable("qList");
		rList = extras.getParcelable("rList");
		qUserLogList = extras.getParcelable("qUserLogList");
		toMVA = new Bundle();
		toMVA.putParcelable("qList", qList);
		toMVA.putParcelable("rList", rList);
		toMVA.putParcelable("qUserLogList", qUserLogList);
		toMVA.putString("user_id", extras.getString("user_id"));

	}

	@Override
	public void onBackPressed() {
		exit();
	}

	private void exit() {
		Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Do you really want to exit?");
		builder.setCancelable(true);
		builder.setPositiveButton("Quit", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				// stopService(svcBmusic);
				finish();
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

}
