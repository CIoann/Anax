package com.example.conniezalo.anax;
import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

	public final static String _AFPA_ = "AccountForgetPasswordActivity";
	public final static String _AI_ = "AccountInfo";
	public final static String _AIA_ = "AccountInfoActivity";
	public final static String _AIRA_ = "AccountInfoRewardsAdapter";
	public final static String _ARA_ = "AccountRegisterActivity";
	public final static String _ATA_ = "AccountTOUActivity";
	public final static String _BM_ = "BackgroundMusic";
	public final static String _GILIA_ = "GameInitializeLogInActivity";
	public final static String _GIQA_ = "GameInitializeQuestActivity";
	public final static String _GIRA_ = "GameInitializeRewardActivity";
	public final static String _GIUQA_ = "GameInitializeUsersQuestActivity";
	public final static String _GWA_ = "GameWelcomeActivity";
	public final static String _GWCA_ = "GameWelcomeCreditsActivity";
	public final static String _GWHA_ = "GameWelcomeHelpActivity";
	public final static String _GWSA_ = "GameWelcomeSettingsActivity";
	public final static String _JP_ = "JSONParser";
	public final static String _MVA_ = "MapViewActivity";
	public final static String _MA_ = "MyApplication";
	public final static String _Q_ = "Quest";
	public final static String _QA_ = "QuestActivity";
	public final static String _QI_ = "QuestInfo";
	public final static String _QIPA_ = "QuestInProgressActivity";
	public final static String _QL_ = "QuestList";
	public final static String _QLA_ = "QuestLogActivity";
	public final static String _RAc_ = "RewardActivity";
	public final static String _RAd_ = "RewardAdapter";
	public final static String _REA_ = "RewardExchangeActivity";
	public final static String _RI_ = "RewardInfo";
	public final static String _RL_ = "RewardList";
	public final static String _R_ = "Rewards";
	public final static String _default_class_ = "General";
	public final static int _default_lvl_ = 1;

	private static QuestList _questList;
	private static RewardList _rewardList;
	private static QuestList _userQList;

	private static String _userOnline = "";
	private static int _userRewardPoints = 0;
	private static int _userLevel = _default_lvl_;
	private static String _userClass = _default_class_;

	public static int getUserRewardPoints() {
		return _userRewardPoints;
	}

	public static int getUserLevel() {
		return _userLevel;
	}

	public static String getUserClass() {
		return _userClass;
	}

	public static String getUserOnline() {
		Log.d("useronline", _userOnline);
		return _userOnline;
	}

	public static void setUserOnline(String uo) {
		_userOnline = uo;
		Log.d("useronline", _userOnline);
	}

	public static void setUserLevel(int UL) {
		_userLevel = UL;
	}

	public static void setUserClass(String UC) {
		_userClass = UC;
	}

	public static void setUserRewardPoints(int urp) {
		_userRewardPoints = urp;
	}

	public static QuestList getQList() {
		return _questList;
	}

	public static void setQList(QuestList ql) {
		_questList = ql;
		Log.d("the q list", "" + _questList.get(2).getTitle().toString());
	}

	public static RewardList getRList() {
		return _rewardList;
	}

	public static void setRList(RewardList rl) {
		_rewardList = rl;
	}

	public static QuestList getUserQList() {
		return _userQList;
	}

	public static void setUserQList(QuestList uql) {
		_userQList = uql;
	}

	public static boolean isUserQListEmpty() {
		if (_userQList.size() == 0) {
			return true;
		} else
			return false;
	}

	public static void setDefault(String name) {
		_userClass = _default_class_;
		_userLevel = _default_lvl_;
		_userOnline = name;
	}

}