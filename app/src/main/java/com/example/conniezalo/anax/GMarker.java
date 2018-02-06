package com.example.conniezalo.anax;
import com.google.android.gms.maps.model.Marker;

public class GMarker {
	private Marker _marker;
	private String _category;

	public GMarker(Marker _marker, String _category) {
		this._marker = _marker;
		this._category = _category;
	}

	public Marker getMarker() {
		return _marker;
	}

	public String getCategory() {
		return _category;
	}

	public void setCategory(String _category) {
		this._category = _category;
	}

	public void setMarker(Marker _marker) {
		this._marker = _marker;
	}
}
