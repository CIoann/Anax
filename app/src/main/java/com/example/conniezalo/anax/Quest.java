package com.example.conniezalo.anax;
public class Quest {
	private String _title;
	private String _description;
	private int _reward;
	private int _id;
	private String _address;
	private double _lat, _lon;
	private String _category;

	public Quest() {
		_id = -1;
		_title = null;
		_description = null;
		_lat = 0.0;
		_lon = 0.0;
		_reward = 0;
		_address = null;
		_category = null;
	}

	public Quest(int id, String title, String description, double lat,
			double lon, int reward, String address, String category) {
		this._title = title;
		this._id = id;
		this._description = description;
		this._lat = lat;
		this._lon = lon;
		this._reward = reward;
		this._address = address;
		this._category = category;
	}

	public Quest(int id, String title, String description, double lat,
			double lon, int reward, String address) {
		this._title = title;
		this._id = id;
		this._description = description;
		this._lat = lat;
		this._lon = lon;
		this._reward = reward;
		this._address = address;
	}

	public int getId(){
		return _id;
	}
	public void setId(int _id){
		this._id = _id;
	}
	public String getTitle() {
		return _title;
	}

	public String getCategory() {
		return _category;
	}

	public double getLat() {
		return _lat;
	}

	public double getLng() {
		return _lon;
	}

	public String getDescription() {
		return _description;
	}

	public int getReward() {
		return _reward;
	}

	public String getAddress() {
		return _address;
	}

	// ! Setters

	public void setTitle(String title) {
		this._title = title;
	}

	public void setCategory(String category) {
		this._category = category;
	}

	public void setDescription(String i) {
		this._description = i;
	}

	public void setLat(double lat) {
		_lat = lat;
	}

	public void setLon(double lon) {
		_lon = lon;
	}

	public void setReward(int reward) {
		this._reward = reward;
	}

	public void setAddress(String address) {
		_address = address;
	}
	
	public static int configureCategoryAllQuest(String category, boolean userQuest) {
		int category_icon = 0;
		switch (category) {
		case QuestInfo.CATEGORY_BAR: {
			if (userQuest) {
				category_icon = R.drawable.winebar2;
			} else
				category_icon = R.drawable.winebar;
			break;
		}
		case QuestInfo.CATEGORY_CINEMA: {
			if (userQuest) {
				category_icon = R.drawable.cinema2;
			} else
				category_icon = R.drawable.cinema;
			break;
		}
		case QuestInfo.CATEGORY_CLUB: {
			if (userQuest) {
				category_icon = R.drawable.bar_coktail2;

			} else
				category_icon = R.drawable.bar_coktail;
			break;
		}
		case QuestInfo.CATEGORY_COFFEE: {
			if (userQuest) {
				category_icon = R.drawable.coffee2;
			} else
				category_icon = R.drawable.coffee;
			break;
		}
		case QuestInfo.CATEGORY_RESTAURANT: {
			if (userQuest) {
				category_icon = R.drawable.restaurant2;
			} else
				category_icon = R.drawable.restaurant;
			break;
		}
		default:
			category_icon = R.drawable.tower;

		}
		return category_icon;
	}
}
