package com.example.double_touch_drag;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ScaleGestureDetectorCompat;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ScaleGestureDetectorによるImageViewの拡大縮小
		final ImageView image = (ImageView) findViewById(R.id.image);
		final ScaleGestureDetector detector = new ScaleGestureDetector(this, new SimpleOnScaleGestureListener() {
			@Override
			public boolean onScale(final ScaleGestureDetector detector) {
				final float newScale = detector.getScaleFactor();
				image.setScaleX(image.getScaleX() * newScale);
				image.setScaleY(image.getScaleY() * newScale);
				image.invalidate();
				return true;
			}
		});

		// 画面のコンテンツ領域でのジェスチャーを検知する
		findViewById(android.R.id.content).setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(final View v, final MotionEvent event) {
				return detector.onTouchEvent(event);
			}
		});

		// KitKat以上の場合、デフォルトでDouble touch dragの検知が有効なので無効にする
		ScaleGestureDetectorCompat.setQuickScaleEnabled(detector, false);

		// Double touch drag(QuickScale)が有効かどうかToast表示。KitKat未満なら常にfalse
		final boolean quickScaleEnabled = ScaleGestureDetectorCompat.isQuickScaleEnabled(detector);
		Toast.makeText(this, "QuickScaleEnabled:" + quickScaleEnabled, Toast.LENGTH_LONG).show();
	}
}
