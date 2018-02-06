package com.example.conniezalo.anax;
import android.provider.BaseColumns;

public class QuestInfo implements BaseColumns {
	
	public static final String url_quests = "http://www.anaxcyprus.com/quests_show_App_2.php";

	public static final String url_QUESTLOG = "http://www.anaxcyprus.com/questlog.php";
	
	public static final String url_RemoveQuestFromUser ="http://www.anaxcyprus.com/qtu_removeq.php";
	
	// id ROWS

	public static final String KEY_ID = "QID";
	public static final String TAG_MESSAGE = "message";
	public static final String TAG_SUCCESS = "success";

	// Columns Name: Column user and column password

	public static final String COLUMN_TITLE = "qTitle";
	public static final String COLUMN_DESCRIPTION = "qDescription";
	public static final String COLUMN_LATITUDE = "qLatitude";
	public static final String COLUMN_LONGITUDE = "qLongitude";
	public static final String COLUMN_REWARD = "qRewardCrones";
	public static final String COLUMN_ADDRESS= "qAddress";
	public static final String COLUMN_CAT= "qCategory";
	
	//Categories of Quests
	
	public static final String CATEGORY_BAR = "Bar";

	public static final String CATEGORY_CINEMA = "Cinema";

	public static final String CATEGORY_RESTAURANT = "Restaurant";

	public static final String CATEGORY_COFFEE = "Coffee";

	public static final String CATEGORY_CLUB = "Club";
	

	public QuestInfo() {

	}
}
