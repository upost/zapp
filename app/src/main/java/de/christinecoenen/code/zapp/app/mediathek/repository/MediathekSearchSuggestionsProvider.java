package de.christinecoenen.code.zapp.app.mediathek.repository;

import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.provider.SearchRecentSuggestions;


public class MediathekSearchSuggestionsProvider extends SearchRecentSuggestionsProvider {

	private final static String AUTHORITY = "de.christinecoenen.code.zapp.MediathekSearchSuggestionsProvider";
	private final static int MODE = DATABASE_MODE_QUERIES;

	public static void saveQuery(Context context, String query) {
		SearchRecentSuggestions suggestions = new SearchRecentSuggestions(context, AUTHORITY, MODE);
		suggestions.saveRecentQuery(query, null);
	}

	public static void deleteAllQueries(Context context) {
		SearchRecentSuggestions suggestions = new SearchRecentSuggestions(context, AUTHORITY, MODE);
		suggestions.clearHistory();
	}

	public MediathekSearchSuggestionsProvider() {
		setupSuggestions(AUTHORITY, MODE);
	}
}
