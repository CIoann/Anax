package com.example.conniezalo.anax;
import android.provider.BaseColumns;
import java.util.Date;

public class RewardInfo implements BaseColumns {

	public static final String url_rewards = "http://www.anaxcyprus.com/rewards_show_App.php";
	public static final String url_rewards_remove = "http://www.anaxcyprus.com/rewards_delete.php";
	public static final String url_utq_addq = "http://www.anaxcyprus.com/qtu_addq.php";
	public static final String url_rewards_addrewarduser = "http://www.anaxcyprus.com/rewards_addtouser.php";
	public static final String url_utr_show="http://www.anaxcyprus.com/rewards_show_users_rewards.php";

	// id ROWS

	public static final String KEY_ID = "RID";
	public static final String TAG_MESSAGE = "message";
	public static final String TAG_SUCCESS = "success";
	public static final String UTR_KEY = "utr"; 

	public static final String COLUMN_REWARD_TITLE = "rTitle";
	public static final String COLUMN_REWARD_DESCRIPTION = "rDescription";
	public static final String COLUMN_REWARD_POINTS = "rRewardCrones";
	public static final String COLUMN_REWARD_CATEGORY = "rCategory";
	public static final String COLUMN_REWARD_COMPANY = "rCompany";
	public static final String COLUMN_REWARD_DATE_START = "rDateStart";
	public static final String COLUMN_REWARD_DATE_END = "rDateEnd";
	
	public static final String COLUMN_UTR_UID = "utr_uid";
	public static final String COLUMN_UTR_RID = "utr_rid";
	public static final String COLUMN_UTR_TITLE = "utr_rtitle";
	public static final String COLUMN_UTR_DESCRIPTION ="utr_rdescription";
	public static final String COLUMN_UTR_CATEGORY ="utr_rcategory";
	public static final String COLUMN_UTR_POINTS ="utr_rrewardcrones";
	public static final String COLUMN_UTR_COMPANY ="utr_rcid";
	public static final String COLUMN_UTR_DATE_START = "utr_start_date";
	public static final String COLUMN_UTR_DATE_END = "utr_end_date";

	public RewardInfo() {

	}
}
