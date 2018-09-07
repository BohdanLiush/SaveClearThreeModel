package com.example.bohdan.saveclearthreemodel;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    EditText login, password, adress;
    String string, stringTwo, stringThree;
    Model model = new Model();
    public List<String> sample;
    public Set<String> setTwo;
    public ArrayList<String> listObjects = new ArrayList<>();

    Button saveBtn, restoreBtn, clearBtn, clearBtnSample;

    SharedPreferences sPref;
    SharedPreferences.Editor ed;
    String LOGIN_TEXT = "login_text";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        adress = findViewById(R.id.adress);

        login.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                model.setLogin(login.getText().toString());
                System.out.println(model.getLogin());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                model.setPassword(password.getText().toString());

                System.out.println(model.getPassword());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        adress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                model.setAdress(adress.getText().toString());
                System.out.println(model.getAdress());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        saveBtn = findViewById(R.id.button_save);
        restoreBtn = findViewById(R.id.button2);
        clearBtn = findViewById(R.id.button_clear);
        clearBtnSample = findViewById(R.id.buttonClearSampl);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveText();
            }
        });

        restoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadText();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSpref();
            }
        });

        clearBtnSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSample();
            }
        });
    }

    void saveText() {
        sPref = getSharedPreferences("MyPrefsFilesThree",0);
        ed = sPref.edit();
        listObjects.add(model.getLogin());
        listObjects.add(model.getPassword());
        listObjects.add(model.getAdress());

        Set<String> set = new HashSet<>();
        set.addAll(listObjects);
        ed.putStringSet(LOGIN_TEXT, set);
        ed.apply();
        Toast.makeText(this, "Login and password and adress saved", Toast.LENGTH_SHORT).show();
    }

    void loadText() {

         sPref = getSharedPreferences("MyPrefsFilesThree", 0);

         setTwo = sPref.getStringSet(LOGIN_TEXT, null);

         sample = new ArrayList<>(setTwo);

            login.setText(sample.get(0));
            password.setText(sample.get(1));
            adress.setText(sample.get(2));

        Toast.makeText(this, "Login and password and adress loaded", Toast.LENGTH_SHORT).show();
    }


    void clearSpref(){
        /*if (sPref==null){
            Toast.makeText(this, "all clear", Toast.LENGTH_SHORT).show();
        }*/
        login.getText().clear();
        password.getText().clear();
        adress.getText().clear();
        setTwo.clear();
        sample.clear();

        sPref.edit().remove(LOGIN_TEXT).apply();
    }

    void clearSample(){
        login.getText().clear();
        password.getText().clear();
        adress.getText().clear();
    }
}
