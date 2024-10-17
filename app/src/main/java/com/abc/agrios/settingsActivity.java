package com.abc.agrios;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import java.util.Locale;

public class settingsActivity extends AppCompatActivity {

    private static final String TAG = "SettingsActivity";
    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Load the saved language preference
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String selectedLanguage = sharedPreferences.getString("language_preference", "en");
        setLocale(selectedLanguage, false);  // Avoid recreation on initial load

        setContentView(R.layout.activity_settings);

        // Load the settings fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings_container, new SettingsFragment())
                    .commit();
        }

        // Register a listener for shared preferences changes
        preferenceChangeListener = (sharedPreferences1, key) -> {
            if (key.equals("language_preference")) {
                String newLanguage = sharedPreferences1.getString(key, "en");
                Log.d(TAG, "Language changed to: " + newLanguage);
                setLocale(newLanguage, true);  // Recreate activity on language change
            }
        };
        sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener);
    }

    private void setLocale(String langCode, boolean recreateActivity) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());

        // Restart the activity to apply the language change if needed
        if (recreateActivity) {
            recreate();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister preference change listener to prevent memory leaks
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            // Load preferences from an XML resource
            setPreferencesFromResource(R.xml.preferences, rootKey);
        }
    }
}
