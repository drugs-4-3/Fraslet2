package learning.ifeel3.swim_lab_3_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //showWelcomeToast();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        showWelcomeToast();
    }

    public void settingsButtonClicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void savedDataButtonClicked(View view) {
        Intent intent = new Intent(this, SavedDataActivity.class);
        startActivity(intent);
    }

    public void customListViewButtonClicked(View view) {
        Intent intent = new Intent(this, CustomListView.class);
        startActivity(intent);
    }

    private void showWelcomeToast() {
        SharedPreferences settings = getSharedPreferences(SettingsActivity.SETTINGS_PREFERENCES_NAME, 0);
        String welcomeText = settings.getString("welcome_text", "Witaj!");
        Toast.makeText(getApplicationContext(), welcomeText, Toast.LENGTH_SHORT).show();
    }
}
