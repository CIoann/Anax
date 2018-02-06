package com.example.conniezalo.anax;
import android.app.TabActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.content.Intent;

@SuppressWarnings("deprecation")
public class AccountInfoActivity extends TabActivity {

	private static final String _ACCOUNT_PROFILE_ = "My Profile";
	private static final String _ACCOUNT_REWARDS_ = "My Rewards";

	private TabHost th;

	private ListView lsUserRewards;
	private RewardList rList;
@Override 
protected void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_account_info);
	
	  th = getTabHost();

	  TabSpec profile_spec;
      profile_spec = th.newTabSpec(_ACCOUNT_PROFILE_);
      profile_spec.setIndicator(_ACCOUNT_PROFILE_, getResources().getDrawable(R.drawable.icon_profile));
      Intent toAccProf = new Intent(getApplicationContext(), AccountProfileActivity.class);
      profile_spec.setContent(toAccProf);
     
      TabSpec rewards_spec;
      rewards_spec = th.newTabSpec(_ACCOUNT_REWARDS_);
      rewards_spec.setIndicator(_ACCOUNT_REWARDS_, getResources().getDrawable(R.drawable.icon_rewards));
      Intent toAccRwd = new Intent(getApplicationContext(), AccountRewardActivity.class);
      rewards_spec.setContent(toAccRwd);
      
      th.addTab(profile_spec);
      th.addTab(rewards_spec);
      
}
/*
	 * 
	 * private void initVariables() {
	 * 
	 * lsUserRewards = (ListView) findViewById(R.id.lsUserRewards);

	 * 
	 * 
	 * initlistview(); }
	 * 
	 * 
	 * 
	 * private void initlistview() { // Bundle extras = getIntent().getExtras();
	 * // Get the intent's extras rList = MyApplication.getRList(); String
	 * rtitles[]; int rpoints[]; String rdes[]; String rcategories[];
	 * 
	 * rtitles = new String[rList.size()]; rpoints = new int[rList.size()]; rdes
	 * = new String[rList.size()]; rcategories = new String[rList.size()];
	 * lsUserRewards.setAdapter(new
	 * AccountInfoRewardsAdapter(getApplicationContext(), rtitles, rdes,
	 * rcategories));
	 * 
	 * for (int i = 0; i < rList.size(); i++) { rdes[i] =
	 * rList.get(i).getDescription().toString(); rpoints[i] =
	 * rList.get(i).getReward(); rtitles[i] =
	 * rList.get(i).getTitle().toString(); rcategories[i] =
	 * rList.get(i).getCategory().toString();
	 * 
	 * } }
	 */

}
