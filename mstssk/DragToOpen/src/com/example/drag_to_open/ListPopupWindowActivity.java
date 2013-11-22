package com.example.drag_to_open;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.ListPopupWindowCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;
import android.widget.Toast;

public class ListPopupWindowActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
	private ListPopupWindow listPopupWindow;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_popup_window);

		final View button = findViewById(R.id.button);
		button.setOnClickListener(this);

		final List<String> data = Arrays.asList("Foo", "Bar", "Piyo", "Fuga");
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
		listPopupWindow = new ListPopupWindow(this);
		listPopupWindow.setAdapter(adapter);
		listPopupWindow.setOnItemClickListener(this);
		listPopupWindow.setAnchorView(button);

		// KitKat未満では、createDragToOpenListenerはnullを返します。
		button.setOnTouchListener(ListPopupWindowCompat.createDragToOpenListener(listPopupWindow, button));
	}

	@Override
	public void onClick(final View v) {
		listPopupWindow.show(); // 普通にクリックされたらポップアップ表示
	}

	@Override
	public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
		Toast.makeText(this, (String) parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
		listPopupWindow.dismiss();
	}
}
