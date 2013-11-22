package com.example.print_helper;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.print.PrintHelper;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);
		if (PrintHelper.systemSupportsPrint()) { // KitKat未満では常にfalse
			button.setText("画像を印刷");
		} else {
			button.setEnabled(false);
			button.setText("印刷をサポートしていません");
		}
	}

	@Override
	public void onClick(final View v) {
		final Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/jpeg");
		startActivityForResult(intent, 85);
	}

	@Override
	protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
		if (requestCode == 85 && resultCode == RESULT_OK) {
			final PrintHelper helper = new PrintHelper(this);
			helper.setColorMode(PrintHelper.COLOR_MODE_MONOCHROME); // デフォルトはCOLOR_MODE_COLOR
			helper.setScaleMode(PrintHelper.SCALE_MODE_FIT); // デフォルトはSCALE_MODE_FILL
			try {
				helper.printBitmap("test", data.getData());
			} catch (final FileNotFoundException e) {
			}
		}
	}
}
