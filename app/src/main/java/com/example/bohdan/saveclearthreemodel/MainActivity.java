package com.example.bohdan.saveclearthreemodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    EditText login, password, adress;
    String string, stringTwo, stringThree;
    Model model = new Model();
    public List<String> sample;
    public Set<String> set;
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

       /* model.setLogin(login.getText().toString());
        System.out.println(model.getPassword());

        model.setPassword(password.getText().toString());
        System.out.println(model.getPassword());

        model.setAdress(adress.getText().toString());
        System.out.println(model.getAdress());*/

        login.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                model.setLogin(login.getText().toString());
                //string = login.getText().toString();
                //System.out.println(string);
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
                stringTwo = password.getText().toString();
               // System.out.println(stringTwo);
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
                /*stringThree = adress.getText().toString();
                System.out.println(stringThree);*/
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
        //sPref = getApplicationContext().getSharedPreferences("MyPrefsFilesFour",Context.MODE_PRIVATE);
        sPref = getApplicationContext().getSharedPreferences("MyPrefsFilesFive",Context.MODE_PRIVATE);

        ed = sPref.edit();

        string = model.getLogin()+","+ model.getPassword()+","+model.getAdress();
        System.out.println("hi major");
        //set = new HashSet<>();

        /*set.add(model.getLogin());
        set.add(model.getPassword());
        set.add(model.getAdress());

        ed.putStringSet(LOGIN_TEXT, set);
        ed.apply();*/

        ed.putString(LOGIN_TEXT,string);
        ed.apply();
        Toast.makeText(this, "Login and password and address saved", Toast.LENGTH_SHORT).show();
    }

    void loadText() {
        //sPref = getApplicationContext().getSharedPreferences("MyPrefsFilesFour",Context.MODE_PRIVATE);
        System.out.println("load");
        sPref = getApplicationContext().getSharedPreferences("MyPrefsFilesFive",Context.MODE_PRIVATE);

        //setTwo = sPref.getStringSet(LOGIN_TEXT, null);
        /*if (setTwo == null) {
            Toast.makeText(this, "all clear", Toast.LENGTH_SHORT).show();
        } else {

            *//*Iterator itr = setTwo.iterator();
            while (itr.hasNext()){
                login.setText((CharSequence) itr.next());
                password.setText((CharSequence) itr.next());
                address.setText((CharSequence) itr.next());
            }*//*

            sample = new ArrayList<>(setTwo);
            System.out.println("hello");
            login.setText(sample.get(0));
            password.setText(sample.get(1));
            adress.setText(sample.get(2));
            Toast.makeText(this, "Login and password and address loaded", Toast.LENGTH_SHORT).show();
        }*/

        stringTwo = sPref.getString(LOGIN_TEXT,null);
        if (stringTwo==null){
            Toast.makeText(this, "all clear", Toast.LENGTH_SHORT).show();
        }else {
            String[]massiv = stringTwo.split(",");
            for (int i = 0; i < massiv.length; i++){
                if (massiv[0]==null) {
                    massiv[0] = "empty";
                } if (massiv[1]==""){
                    massiv[1]="empty2";
                } if (massiv[2]==null){
                    massiv[2]="empty3";
                }
                else {
                    login.setText(massiv[0]);
                    password.setText(massiv[1]);
                    adress.setText(massiv[2]);
                }
            }

           /* login.setText(massiv[0]);
            password.setText(massiv[1]);
            adress.setText(massiv[2]);*/

        }


    }


    void clearSpref() {
        if (sPref == null) {
            Toast.makeText(this, "all clear", Toast.LENGTH_SHORT).show();
        } else {
            login.getText().clear();
            password.getText().clear();
            adress.getText().clear();

            sPref.edit().clear().apply();
        }

    }

    void clearSample(){
        login.getText().clear();
        password.getText().clear();
        adress.getText().clear();
    }

    public void clearSharedPreferences(){
        File dir = new File(this.getFilesDir().getParent() + "/shared_prefs/");
        String[] children = dir.list();
        for (int i = 0; i < children.length; i++) {
            // clear each of the prefrances
            this.getSharedPreferences(children[i].replace(".xml", ""), Context.MODE_PRIVATE).edit().clear().commit();
        }
        // Make sure it has enough time to save all the commited changes
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        for (int i = 0; i < children.length; i++) {
            // delete the files
            new File(dir, children[i]).delete();
        }
    }
}
