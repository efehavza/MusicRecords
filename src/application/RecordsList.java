package application;

import java.util.ArrayList;

public class RecordsList {
	private ArrayList<Records> recList = new ArrayList<Records>();

	public RecordsList() {

	}

	public void add(Records rec) {
		recList.add(rec);
	}

	public Records get(int index) {
		try {
			return recList.get(index); // returns object at valid index
		} catch (Exception ex) {
			return null; // returning null object
		}
	}

	public int length() {
		return recList.size();
	}

}
