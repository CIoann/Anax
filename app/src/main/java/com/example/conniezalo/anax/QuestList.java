package com.example.conniezalo.anax;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestList extends ArrayList<Quest> implements Parcelable {

	private static final long serialVersionUID = 663585476779879096L;

	public QuestList() {

	}

	public QuestList(Parcel in) {
		readFromParcel(in);
	}

	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public QuestList createFromParcel(Parcel in) {
			return new QuestList(in);
		}

		public Object[] newArray(int arg0) {
			return null;
		}

	};

	private void readFromParcel(Parcel in) {
		this.clear();

		int size = in.readInt();

		for (int i = 0; i < size; i++) {
			Quest c = new Quest();
			c.setId(in.readInt());
			c.setTitle(in.readString());
			c.setDescription(in.readString());
			c.setLat(in.readDouble());
			c.setLon(in.readDouble());
			c.setReward(in.readInt());
			c.setAddress(in.readString());
			c.setCategory(in.readString());
			this.add(c);
		}

	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel parce, int flags) {
		int size = this.size();
		// We have to write the list size, we need him recreating the list
		parce.writeInt(size);
		// We decided arbitrarily to write first the Name and later the Phone
		// Number.
		for (int i = 0; i < size; i++) {
			Quest c = this.get(i);
			parce.writeInt(c.getId());
			parce.writeString(c.getTitle());
			parce.writeString(c.getDescription());
			parce.writeDouble(c.getLat());
			parce.writeDouble(c.getLng());
			parce.writeInt(c.getReward());
			parce.writeString(c.getAddress());
			parce.writeString(c.getCategory());
		}
	}

}
