package com.example.conniezalo.anax;
import android.provider.BaseColumns;
import android.util.Log;

public class AccountInfo implements BaseColumns {

	public static final String url_REGISTER = "http://www.anaxcyprus.com/users_register.php";
	public static final String url_LOGIN = "http://www.anaxcyprus.com/users_login.php";
	public static final String url_FORGET_PASSWORD = "http://www.anaxcyprus.com/user_forget_password.php";

	

	public static final String KEY_ID = "PID";
	public static final String TAG_MESSAGE = "message";
	public static final String TAG_SUCCESS = "success";
	public static final String TAG_ERROR = "error";

	public static final String COLUMN_ACCOUNT_NAME = "uName";
	public static final String COLUMN_ACCOUNT_PASSWORD = "uPassword";
	public static final String COLUMN_ACCOUNT_PHONE = "uPhone";
	public static final String COLUMN_ACCOUNT_EMAIL = "uEmail";
	public static final String COLUMN_ACCOUNT_IMGLVL = "uImgLvl";
	public static final String COLUMN_ACCOUNT_LVL = "uLvl";
	public static final String COLUMN_ACCOUNT_REWARD_POINTS="uRewardPoints";

	public static final String COLUMN_ACCOUNT_CLASS = "uClass";

	
	
	private static final int PHONE_LENGTH = 8;

	private long _id;
	private String _username;
	private int _phone;
	private String _password;
	private String _email;
	private int _receive_email;

	public AccountInfo(int id, String name, int phone, String password,
			String email, int recEmail) {
		this._id = id;
		this._username = name;
		this._phone = phone;
		this._password = password;
		this._email = email;
		this._receive_email = recEmail;
	}

	public AccountInfo() {

	}

	public AccountInfo(int id, String name, String password) {
		this._id = id;
		this._username = name;
		this._phone = 0;
		this._password = password;
	}

	// ! Getters
	public long getId() {
		return _id;
	}

	public int getRecEmail() {
		return _receive_email;
	}

	public String getName() {
		return _username;
	}

	public int getPhone() {
		return _phone;
	}

	public String getPassword() {
		return _password;
	}

	public String getEmail() {
		return _email;
	}

	// ! Setters
	public void setId(long id) {
		this._id = id;
	}

	public void setRecEmail(int recEmail) {
		this._receive_email = recEmail;
	}

	public void setName(String name) {
		this._username = name;
	}

	public void setPhone(int phone) {
		this._phone = phone;
	}

	public void setPassword(String password) {
		this._password = password;
	}

	public void setEmail(String email) {
		this._email = email;
	}

	public static boolean validateNameLength(String name) {
		if (name.length() > 20 || name.length() <= 4) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean validatePhoneLength(int phone) {
		if (String.valueOf(phone).length() != PHONE_LENGTH) {
			Log.d("invalid phone", "invalid phone ");
			return false;
		} else {
			Log.d("valid phone", "valid phone ");
			return true;
		}
	}

	public static boolean validatePasswordLength(String password) {
		if (password.length() > 20 || password.length() <= 4) {
			Log.d("invalid password", "invalid password");
			return false;
		} else {
			Log.d("valid password", "valid password");
			return true;
		}
	}

	/** Not Implementet yet **/
	public static boolean validateEmail(String email) {
		return true;
	}

	public static boolean isUniqueName(String name) {
		// create my sql lite to save
		return true;
	}

}
