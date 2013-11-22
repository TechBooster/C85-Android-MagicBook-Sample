package com.example.drag_to_open;

import java.util.Arrays;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final List<String> list = Arrays.asList("PopupMenu", "ListPopupWindow");
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
	}

	@Override
	protected void onListItemClick(final ListView l, final View v, final int position, final long id) {
		switch (position) {
		case 0:
			startActivity(new Intent(this, PopupMenuActivity.class));
			break;
		case 1:
			startActivity(new Intent(this, ListPopupWindowActivity.class));
			break;
		default:
			break;
		}
	}
}
