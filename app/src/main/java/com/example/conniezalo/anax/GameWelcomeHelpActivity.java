package com.example.conniezalo.anax;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GameWelcomeHelpActivity extends Activity implements
		View.OnClickListener {

	EditText etEmailName, etSubject, etComments;
	String name, subject, comments;
	String emailAddr = "con.ioannou@idalion.com";
	Button sendEmail;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_play_help);
		initVariables();
		sendEmail.setOnClickListener(this);

	}

	private void initVariables() {
		etEmailName = (EditText) findViewById(R.id.etEmailName);
		etSubject = (EditText) findViewById(R.id.etEmailSubject);
		etComments = (EditText) findViewById(R.id.etEmailComments);
		sendEmail = (Button) findViewById(R.id.bsendEmail);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		convertETtoStr();
		String emailAddress[] = { emailAddr };
		String message = "Well Hello, " + "\n\n" + "My name is " + name
				+ "\n\nI wanted to talk about " + subject + "\n\n" + comments
				+ "\n\n\n" + "Thank you!";
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailAddress);
		emailIntent
				.putExtra(android.content.Intent.EXTRA_SUBJECT, "ANAX_EMAIL");
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
		startActivity(emailIntent);

	}

	private void convertETtoStr() {
		name = etEmailName.getText().toString();
		subject = etSubject.getText().toString();
		comments = etComments.getText().toString();
	}

	protected void onPause() {
		super.onPause();
		finish();
	}
}
