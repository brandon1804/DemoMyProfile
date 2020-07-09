package sg.edu.rp.c346.id18044455.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    Button btnS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextN);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        btnS = findViewById(R.id.buttonSave);


        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = etName.getText().toString();
                float GPA = Float.parseFloat(etGPA.getText().toString());
                int gID = rgGender.getCheckedRadioButtonId();

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                SharedPreferences.Editor prefEdit = prefs.edit();

                prefEdit.putString("username", strName);
                prefEdit.putFloat("gpa", GPA);
                prefEdit.putInt("id", gID);

                prefEdit.commit();
            }
        });


    }//end of main method

    @Override
    protected void onPause() {
        super.onPause();
        String strName = etName.getText().toString();
        float GPA = Float.parseFloat(etGPA.getText().toString());
        int gID = rgGender.getCheckedRadioButtonId();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putString("username", strName);
        prefEdit.putFloat("gpa", GPA);
        prefEdit.putInt("id", gID);

        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String strName = prefs.getString("username","Name");
        Float gpa = prefs.getFloat("gpa", 0);
        Integer gID = prefs.getInt("id", R.id.radioButtonM);

        etName.setText(strName);
        etGPA.setText(gpa + "");
        rgGender.check(gID);
    }


}//end of class