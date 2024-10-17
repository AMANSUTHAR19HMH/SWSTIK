package com.abc.agrios;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

public class MainActivity extends AppCompatActivity {
    private Translator translator;
    private static final String TAG = "MLKit";
    private static final String SHARED_PREFS = "MyAppPrefs";
    private static final String TOKEN_KEY = "auth_token";

    private TextView textView;
    private Button btnEnglish, btnFrench, btnSpanish;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        textView = findViewById(R.id.textView);
        btnEnglish = findViewById(R.id.btnEnglish);
        btnFrench = findViewById(R.id.btnFrench);
        btnSpanish = findViewById(R.id.btnSpanish);


        checkAuthentication();


        String textToTranslate = "Welcome to our application!";

        // Set button click listeners for language switching
        btnEnglish.setOnClickListener(v -> switchLanguage(TranslateLanguage.ENGLISH, textToTranslate));
        btnFrench.setOnClickListener(v -> switchLanguage(TranslateLanguage.FRENCH, textToTranslate));
        btnSpanish.setOnClickListener(v -> switchLanguage(TranslateLanguage.SPANISH, textToTranslate));
    }

    // Token-based navigation logic
    private void checkAuthentication() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String token = sharedPreferences.getString(TOKEN_KEY, null);

        if (token != null) {
            Intent intent = new Intent(MainActivity.this, homeActivity.class);
            intent.putExtra("token", token);
            startActivity(intent);
        } else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        finish();
    }

    // Initialize the Translator with the selected language
    private void initializeTranslator(String sourceLang, String targetLang) {
        if (translator != null) {
            translator.close();  // Release the old translator resources
        }

        TranslatorOptions options = new TranslatorOptions.Builder()
                .setSourceLanguage(sourceLang)
                .setTargetLanguage(targetLang)
                .build();
        translator = Translation.getClient(options);

        DownloadConditions conditions = new DownloadConditions.Builder()
                .requireWifi()
                .build();
        translator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener(unused -> Log.d(TAG, "Model downloaded"))
                .addOnFailureListener(e -> Log.e(TAG, "Error downloading model", e));
    }

    // Switch to the selected language and translate the text
    private void switchLanguage(String targetLang, String textToTranslate) {
        initializeTranslator(TranslateLanguage.ENGLISH, targetLang);  // English as the source

        translator.translate(textToTranslate)
                .addOnSuccessListener(translatedText -> textView.setText(translatedText))
                .addOnFailureListener(e -> Log.e(TAG, "Error translating text", e));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (translator != null) {
            translator.close();  // Release translator resources
        }
    }
}
