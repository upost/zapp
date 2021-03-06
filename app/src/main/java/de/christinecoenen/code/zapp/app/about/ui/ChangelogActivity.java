package de.christinecoenen.code.zapp.app.about.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import de.christinecoenen.code.zapp.R;
import de.christinecoenen.code.zapp.databinding.ActivityChangelogBinding;
import ru.noties.markwon.Markwon;
import timber.log.Timber;

public class ChangelogActivity extends AppCompatActivity {

	public static Intent getStartIntent(Context context) {
		return new Intent(context, ChangelogActivity.class);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActivityChangelogBinding binding = ActivityChangelogBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		try (InputStream inputStream = getResources().openRawResource(R.raw.changelog)) {
			String markdown = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
			Markwon.setMarkdown(binding.txtChangelog, markdown);
		} catch (IOException e) {
			Timber.e(e);
		}
	}
}
