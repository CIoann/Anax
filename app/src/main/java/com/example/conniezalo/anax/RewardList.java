package com.example.conniezalo.anax;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class RewardList extends ArrayList<Rewards> implements Parcelable {

	private static final long serialVersionUID = 663585476779879096L;

	public RewardList() {

	}

	public RewardList(Parcel in) {
		readFromParcel(in);
	}

	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public RewardList createFromParcel(Parcel in) {
			return new RewardList(in);
		}

		public Object[] newArray(int arg0) {
			return null;
		}

	};

	private void readFromParcel(Parcel in) {
		this.clear();

		int size = in.readInt();

		for (int i = 0; i < size; i++) {

			Rewards r = new Rewards();
			r.setRID(in.readInt());
			r.setTitle(in.readString());
			r.setDescription(in.readString());
			r.setReward(in.readInt());
			r.setCategory(in.readString());
			this.add(r);

		}

	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel parce, int flags) {
		int size = this.size();
		parce.writeInt(size);

		for (int i = 0; i < size; i++) {
			Rewards r = this.get(i);
			parce.writeInt(r.getRID());
			parce.writeString(r.getTitle());
			parce.writeString(r.getDescription());
			parce.writeInt(r.getReward());
			parce.writeString(r.getCategory());
		}
	}

}
