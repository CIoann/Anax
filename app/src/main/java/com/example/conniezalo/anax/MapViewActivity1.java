package com.example.conniezalo.anax;
import java.util.ArrayList;
import java.util.Date;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.R.interpolator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class MapViewActivity1 extends Activity implements
		OnInfoWindowClickListener {
	private static final int CODE_QA = 0;
	private static final int CODE_QIPA = 1;

	private CheckBox cbcoffee;
	private CheckBox cbcinema;
	private CheckBox cbclub;
	private CheckBox cbrestaurant;
	private CheckBox cbbar;

	private Button buserInfo;
	private Button bexchange;

	Intent toUserInfo;
	Intent toQuestLog;
	Intent toExchange;
	Intent showQInfo;
	Intent showQInProgress;

	private Bundle toQA;

	private GoogleMap Gmap;
	private QuestList qList;
	private QuestList qUserLogList;
	private RewardList rList;
	private ArrayList<GMarker> Gmap_markers;

	private String userOnline = null;

	private int qid = -1;
	private int lqid = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_view);
		Gmap_markers = new ArrayList<GMarker>();
		initVariables();
		initMap();

		buserInfo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivity(toUserInfo);
			}
		});
		bexchange.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle extras = new Bundle();
				extras.putParcelable("rList", rList);
				extras.putString("user_id", userOnline);
				toExchange.putExtras(extras);
				startActivity(toExchange);
			}
		});

		cbbar.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (cbbar.isChecked()) {

					hideMarker("Bar");
				} else
					showMarker("Bar");
			}
		});
		cbclub.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (cbclub.isChecked()) {
					hideMarker("Club");
				} else {
					showMarker("Club");
				}
			}
		});
		cbcinema.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (cbcinema.isChecked()) {
					hideMarker("Cinema");
				} else {
					showMarker("Cinema");
				}
			}
		});
		cbcoffee.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (cbcoffee.isChecked()) {
					hideMarker("Coffee");
				} else {

					showMarker("Coffee");
				}
			}
		});
		cbrestaurant.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (cbrestaurant.isChecked()) {

					hideMarker("Restaurant");
				} else {
					showMarker("Restaurant");
				}
			}
		});

		Gmap.setOnInfoWindowClickListener(this);

	}

	private void showMarker(String category) {
		for (int i = 0; i < Gmap_markers.size(); i++) {
			if (Gmap_markers.get(i).getCategory().toString().equals(category)) {
				Gmap_markers.get(i).getMarker().setVisible(true);
			}
		}
	}

	private void hideMarker(String category) {
		for (int i = 0; i < Gmap_markers.size(); i++) {
			if (Gmap_markers.get(i).getCategory().toString().equals(category)) {
				Gmap_markers.get(i).getMarker().setVisible(false);
			}
		}
	}

	private void initVariables() {
		qList = MyApplication.getQList();
		rList = MyApplication.getRList();
		qUserLogList = MyApplication.getUserQList();
		userOnline = MyApplication.getUserOnline();

		buserInfo = (Button) findViewById(R.id.buInfo);
		bexchange = (Button) findViewById(R.id.bTrade);

		cbbar = (CheckBox) findViewById(R.id.cbbar);
		cbrestaurant = (CheckBox) findViewById(R.id.cbrestaurant);
		cbcinema = (CheckBox) findViewById(R.id.cbcinema);
		cbcoffee = (CheckBox) findViewById(R.id.cbcoffee);
		cbclub = (CheckBox) findViewById(R.id.cbclub);

		toExchange = new Intent(getApplicationContext(),
				RewardExchangeActivity.class);
		toUserInfo = new Intent(getApplicationContext(),
				AccountInfoActivity.class);
		showQInfo = new Intent(getApplicationContext(), QuestActivity.class);
		toQA = new Bundle();
		showQInProgress = new Intent(getApplicationContext(),
				QuestInProgressActivity.class);

	}

	private void initMap() {
		Gmap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		LatLngBounds CYPRUS = new LatLngBounds(
				new LatLng(34.723555, 32.227535), new LatLng(35.817813,
						34.628048));

		Gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(CYPRUS.getCenter(), 7));
		Gmap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		Gmap.getUiSettings().setRotateGesturesEnabled(false);
//		Gmap.setMyLocationEnabled(true);
		markQuests();
	}

	private boolean isUserQuestbyInt(int count) {
		if (MyApplication.isUserQListEmpty()) {
			return false;
		} else {
			for (int k = 0; k < qUserLogList.size(); k++) {
				if (qList.get(count).getTitle()
						.equals(qUserLogList.get(k).getTitle())) {
					return true;
				}
			}
		}
		return false;
	}

	private void markQuests() {
		String category = null;
		int category_icon = 0;
		boolean UserQuest = false;
		for (int i = 0; i < qList.size(); i++) {
			category = qList.get(i).getCategory();
			UserQuest = isUserQuestbyInt(i);
			category_icon = Quest
					.configureCategoryAllQuest(category, UserQuest);
			putMarker(i, category_icon);
		}
	}

	private void toQuestActivity(Marker marker) {
		showQInfo.putExtras(toQA);
		startActivityForResult(showQInfo, CODE_QA);
	}

	private void toQuestInProgressActivity(Marker marker) {
		showQInProgress.putExtras(toQA);
		startActivityForResult(showQInProgress, CODE_QIPA);

	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		getQuestId(marker.getTitle().toString());
		toQA.putParcelable("qlist", qList);
		toQA.putInt("qid", qid);
		toQA.putInt("list_qid", lqid);

		if (isUserQuestbyInt(lqid)) {
			toQuestInProgressActivity(marker);

		} else {
			toQuestActivity(marker);
		}
	}

	private void getQuestId(String title) {

		for (int i = 0; i < qList.size(); i++) {
			if (qList.get(i).getTitle().toString().equals(title)) {
				qid = qList.get(i).getId();
				lqid = i;
			}
		}
	}

	private int getQuestbyId(int id_removed) {
		for (int i = 0; i < qUserLogList.size(); i++) {
			if (id_removed == qUserLogList.get(i).getId()) {
				return i;
			}
		}
		return -1;
	}

	private void putMarker(int qid, int icon) {
		Marker marker = Gmap.addMarker(new MarkerOptions()
				.position(
						new LatLng(qList.get(qid).getLng(), qList.get(qid)
								.getLat()))
				.icon(BitmapDescriptorFactory.fromResource(icon))
				.title(qList.get(qid).getTitle())
				.snippet(qList.get(qid).getAddress()));
		GMarker newGMarker = new GMarker(marker, qList.get(qid).getCategory());
		Gmap_markers.add(newGMarker);
		Log.d("MARKER", "" + newGMarker.getCategory());
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == CODE_QA) {
			int result = data.getExtras().getInt("result");

			if (result == 1) {
				int qid_accepted = data.getExtras().getInt("listqid");
				int qid_category = Quest.configureCategoryAllQuest(
						qList.get(qid_accepted).getCategory(), true);
				putMarker(qid_accepted, qid_category);
				qUserLogList.add(qList.get(qid_accepted));

			}
		}
		if (resultCode == RESULT_OK && requestCode == CODE_QIPA) {

			int result = data.getExtras().getInt("result");
			if (result == 0) {
				Log.d("ABANDONED QUEST", "GOOD");
				int lqid_removed = data.getExtras().getInt("removed_list");
				int lqid_category = Quest.configureCategoryAllQuest(
						qList.get(lqid_removed).getCategory(), false);
				putMarker(lqid_removed, lqid_category);
				qUserLogList.remove(getQuestbyId(qList.get(lqid_removed)
						.getId()));

			}
			if (result == 1) {
				Log.d("NOTHING HAPPENED", "NOTHING");
			}
			if (result == 2) {
				Log.d("CODE INSERTED", "SEND MESSAGE");
				// code inserted
			}
		}
	}
}
