package edu.cs.birzeit.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String NAME="NAME";
    public static final String HEIGHT="HEIGHT";
    public static final String WEIGHT="WEIGHT";
    public static final String GENDER="GENDER";
    public static final String FLAG = "FLAG";


    private Button secAct;
    private Spinner editGender;
    private EditText name, height, weight;
    private String calculation, BMIresult;
    private TextView result;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

        setupSharedPrefs();

        result=findViewById(R.id.result);
        secAct = findViewById(R.id.secAct);
        secAct.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    addToSpinner();
        checkPrefs();
    }
    private void setupViews(){
        editGender=findViewById(R.id.editGender);
        weight=findViewById(R.id.weight);
        height=findViewById(R.id.height);
        name=findViewById(R.id.name);

    }
    private void checkPrefs(){
        boolean flag=prefs.getBoolean(FLAG,false);
        if(flag){
            String namess=prefs.getString(NAME,"");
            String genders=prefs.getString(GENDER,"");
            String heights=prefs.getString(HEIGHT,"");
            String weights=prefs.getString(WEIGHT,"");
            name.setText(namess);
            height.setText(heights);
            weight.setText(weights);
            editGender.setSelected(Boolean.parseBoolean(genders));


        }
    }

    private void setupSharedPrefs(){
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor=prefs.edit();
    }





    private void addToSpinner(){
        String[] arraySpinner = new String[] {
                "Male", "Female"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editGender.setAdapter(adapter);
    }


    public void OnSave(View view) {
        String namee=name.getText().toString();
        String gen = editGender.getSelectedItem().toString();
        String weightValue=weight.getText().toString();
        String heightValue=height.getText().toString();
        editor.putString(NAME,namee);
        editor.putString(GENDER,gen);
        editor.putString(WEIGHT,weightValue);
        editor.putString(HEIGHT,heightValue);
        editor.putBoolean(FLAG,true);
        editor.commit();
    }

    public void CalculateBMI(View view) {
        String s1= weight.getText().toString();
        String s2= height.getText().toString();

        float weightValue =Float.parseFloat(s1);
        float heightValue =Float.parseFloat(s2 )/ 100;
        float bmi= weightValue / (heightValue * heightValue);

        if(bmi < 16){
            BMIresult="Severely Under Weight";
        }else if(bmi<18.5){
            BMIresult="Under Weight";
        }else if (bmi >=18.5 && bmi <=24.9){
            BMIresult="Normal Weight";
        }else if(bmi >= 25 && bmi <= 29.9){
            BMIresult ="OverWeight";
        }else{
            BMIresult ="Obese";
        }
        String gen=editGender.getSelectedItem().toString();
        String namee =name.getText().toString();
        calculation="Result:\n\n"+"Gender is "+" "+gen+"\n"+ namee+" " +"BMI Is"+" "+bmi + "\n" + BMIresult;
        result.setText(calculation);


    }


}