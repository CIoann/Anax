package com.example.conniezalo.anax;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class GameWelcomeSettingsActivity extends Activity implements
		OnCheckedChangeListener {

	ToggleButton tgbLanguage, tgbMusic, tgbSound;
	MediaPlayer mp;

	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_play_settings);

		initVariables();
		tgbSound.setOnCheckedChangeListener(this);
		tgbMusic.setOnCheckedChangeListener(this);
		tgbLanguage.setOnCheckedChangeListener(this);

	}

	private void initVariables() {
		tgbSound = (ToggleButton) findViewById(R.id.tgbSound);
		tgbMusic = (ToggleButton) findViewById(R.id.tgbMusic);
		tgbLanguage = (ToggleButton) findViewById(R.id.tgbLanguage);
	}

	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switch (buttonView.getId()) {
		case R.id.tgbSound: {
			sSoundsControl();
			break;
		}
		case R.id.tgbMusic: {
			sMusicControl();
			break;
		}
		case R.id.tgbLanguage: {
			tgbLanguageControl();
			break;
		}
		}

	}

	private void tgbLanguageControl() {
		Log.d("tgblanguage", "TOGGLED");
		if (tgbLanguage.isChecked()) {
			Log.d("Language", "set to greek");
		} else {
			Log.d("Language", "set to english");

		}
	}

	private void sSoundsControl() {
		Log.d("sounds", "aloha");
		if (tgbSound.isChecked()) {
			Log.d("sounds", "ON");
		} else
			Log.d("sounds", "OFF");
	}

	private void sMusicControl() {
		Log.d("music", "boomboom");
		if (tgbMusic.isChecked()) {
			Log.d("music", "ON");
		} else
			Log.d("music", "OFF");

	}

	protected void onPause() {
		super.onPause();
	}

}
