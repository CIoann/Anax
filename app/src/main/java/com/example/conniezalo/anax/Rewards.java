package com.example.conniezalo.anax;
public class Rewards {
	private int _rid;
	private String _title;
	private String _description;
	private int _reward;
	private String _category;


	public Rewards() {

		_rid = -1;
		_title = null;
		_description = null;
		_reward = -1;
		_category = null;
	}

	public Rewards(int rid, String title, String des, int reward,
			String category) {

		_rid = rid;
		_title = title;
		_description = des;
		_reward = reward;
		_category = category;
	}

	public int getRID() {
		return _rid;
	}

	public String getTitle() {
		return _title;
	}

	public String getCategory() {
		return _category;
	}

	public String getDescription() {
		return _description;
	}

	public int getReward() {
		return _reward;
	}

	// ! Setters
	public void setRID(int rid) {
		this._rid = rid;
	}

	public void setCategory(String category) {
		this._category = category;
	}

	public void setTitle(String title) {
		this._title = title;
	}

	public void setDescription(String i) {
		this._description = i;
	}

	public void setReward(int reward) {
		this._reward = reward;
	}

	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return _title;
	}

}
