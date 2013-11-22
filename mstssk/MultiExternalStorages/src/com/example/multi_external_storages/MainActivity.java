package com.example.multi_external_storages;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.EnvironmentCompat;
import android.util.Log;
import com.example.multi_external_storages.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final StringBuilder sb = new StringBuilder();
		sb.append("\ngetExternalFilesDirs: ");
		for (final File file : ContextCompat.getExternalFilesDirs(this, null)) {
			sb.append(file != null ? file.getAbsolutePath() : file);
			sb.append("\n\tstate: ");
			sb.append(EnvironmentCompat.getStorageState(file));
			sb.append('\n');
		}
		sb.append("\ngetExternalCacheDirs: ");
		for (final File file : ContextCompat.getExternalCacheDirs(this)) {
			sb.append(file != null ? file.getAbsolutePath() : file);
			sb.append("\n\tstate: ");
			sb.append(EnvironmentCompat.getStorageState(file));
			sb.append('\n');
		}
		sb.append("getObbDirs: ");
		for (final File file : ContextCompat.getObbDirs(this)) {
			sb.append(file);
			sb.append("\n\tstate: ");
			sb.append(EnvironmentCompat.getStorageState(file));
			sb.append('\n');
		}
		Log.d("test", sb.toString());
	}
}
