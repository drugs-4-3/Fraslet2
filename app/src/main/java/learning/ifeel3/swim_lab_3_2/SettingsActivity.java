package learning.ifeel3.swim_lab_3_2;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.renderscript.Sampler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    public static final String SETTINGS_PREFERENCES_NAME = "SETTINGS_PREFERENCES_NAME";
    private Spinner welcomeTextsSpinner;
    private SeekBar colorSeekBar;
    private TextView title;
    private EditText titleEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        welcomeTextsSpinner = (Spinner) findViewById(R.id.welcomeTextSpinner);
        colorSeekBar = (SeekBar) findViewById(R.id.colorSeekBar);

        setWelcomeTextsSpinner(welcomeTextsSpinner);
        setColorSeekBar(colorSeekBar);
        title = (TextView) findViewById(R.id.settingsTitle);
        titleEditText = (EditText) findViewById(R.id.setSettingsTitleEditText);


        setWelcomeTextsSpinner(welcomeTextsSpinner);
        setColorSeekBar(colorSeekBar);
        setTitleEditText(titleEditText);

        String titleText = getTitleText();
        title.setText(titleText);
        titleEditText.setText(titleText);

        // set seekbar value and background color
        int value = getColorValue();
        setBackgroundColor(value);
        colorSeekBar.setProgress(value);
        welcomeTextsSpinner.setSelection(getWelcomeTextId());
    }

    private void setWelcomeTextsSpinner(Spinner spinner) {
        // setting spinner content

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.welcome_texts, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // saves chosen welcome_text to SharedPreferences
                String text = (String) parent.getSelectedItem();
                SharedPreferences settings = getSharedPreferences(SETTINGS_PREFERENCES_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("welcome_text", text);
                editor.putInt("welcome_text_id", position);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setColorSeekBar(SeekBar seekBar) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences settings = getSharedPreferences(SETTINGS_PREFERENCES_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("color", progress);
                editor.commit();
                setBackgroundColor(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setTitleEditText(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                for(int i = s.length(); i > 0; i--) {
                    if(s.subSequence(i-1, i).toString().equals("\n"))
                        s.replace(i-1, i, "");
                }

                // String myTextString = s.toString();
            }
        });
    }

    private void setBackgroundColor(int value) {
        View someView = findViewById(R.id.welcomeTextTextView);
        View root = someView.getRootView();
        root.setBackgroundColor(Color.rgb(value, 255, 0));
    }

    private int getColorValue() {
        // Restore preferences
        SharedPreferences settings = getSharedPreferences(SETTINGS_PREFERENCES_NAME, 0);
        return settings.getInt("color", 0);
    }

    private int getWelcomeTextId() {
        SharedPreferences settings = getSharedPreferences(SETTINGS_PREFERENCES_NAME, 0);
        return settings.getInt("welcome_text_id", 0);
    }

    private String getTitleText() {
        SharedPreferences settings = getSharedPreferences(SETTINGS_PREFERENCES_NAME, 0);
        return settings.getString("settings_title", "Settings");
    }


    public void settingsTitleButtonClicked(View view) {
        TextView title = (TextView) findViewById(R.id.settingsTitle);
        TextView input = (EditText) findViewById(R.id.setSettingsTitleEditText);
        String text = input.getText().toString();

        SharedPreferences settings = getSharedPreferences(SETTINGS_PREFERENCES_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("settings_title", text);
        editor.commit();

        title.setText(text);

    }

}
