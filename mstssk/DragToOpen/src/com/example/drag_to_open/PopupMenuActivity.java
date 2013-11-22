package com.example.drag_to_open;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.PopupMenuCompat;
import android.view.View;
import android.widget.PopupMenu;

public class PopupMenuActivity extends Activity implements View.OnClickListener {
	private PopupMenu popupMenu;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup_menu);

		final View button = findViewById(R.id.button);
		button.setOnClickListener(this);

		popupMenu = new PopupMenu(this, button);
		popupMenu.getMenu().add("ACTION_MAIN").setIntent(new Intent(Intent.ACTION_MAIN));
		popupMenu.getMenu().add("ACTION_VIEW").setIntent(new Intent(Intent.ACTION_VIEW));
		popupMenu.getMenu().add("ACTION_PICK").setIntent(new Intent(Intent.ACTION_PICK));

		// KitKat未満では、getDragToOpenListenerはnullを返します。
		button.setOnTouchListener(PopupMenuCompat.getDragToOpenListener(popupMenu));
	}

	@Override
	public void onClick(final View button) {
		popupMenu.show(); // 普通にクリックされたらポップアップ表示
	}
}
