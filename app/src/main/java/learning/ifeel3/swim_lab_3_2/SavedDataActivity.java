package learning.ifeel3.swim_lab_3_2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SavedDataActivity extends AppCompatActivity {

    public static String FILENAME = "my_file_name";
    private String sex = "woman";
    LayoutInflater inflater;

    private Integer[] ageArray;
    private ArrayAdapter<Integer> arrayAdapter;

    private Spinner ageSpinner;
    private RadioGroup sexRadioGroup;
    private EditText nameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_data);

        ageSpinner = (Spinner) findViewById(R.id.ageSpinner);
        sexRadioGroup = (RadioGroup) findViewById(R.id.sexRadioButtons);
        nameEditText = (EditText) findViewById(R.id.nameEditText);

        // setting spinner
        setAgeArray(99);
        arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, ageArray);
        ageSpinner.setAdapter(arrayAdapter);

        // setting radiobuttons
        sexRadioGroup.check(R.id.radio_woman);



    }

    private void setAgeArray(int max) {
        ageArray = new Integer[max + 1];
        for (int i = 0; i <= max; i++) {
            ageArray[i] = i;
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_woman:
                if (checked) {
                    sex = "kobieta";
                }
                break;
            case R.id.radio_man:
                if (checked) {
                    sex = "mezczyzna";
                }
                break;
        }
    }

    public void doneButtonClicked(View view) {
        String string = getDataFromInputs();
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(string.getBytes());
            fos.close();
        } catch(IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
        Toast.makeText(this, getDataFromInputs(), Toast.LENGTH_SHORT).show();
    }

    private String getDataFromInputs() {
        String age = ageSpinner.getSelectedItem().toString();
        String name = nameEditText.getText().toString();
        return name + "\n" + sex + "\n" + age;
    }

}
