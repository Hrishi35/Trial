package com.example.owner.trial;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1, btn2, btn3,btn4,btn5,btn6,btn7;
    TextView t1, t2, t3;
    SQLiteDatabase db;
    String name, id, desg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        t1 = (TextView) findViewById(R.id.txt1);
        t2 = (TextView) findViewById(R.id.txt2);
        t3 = (TextView) findViewById(R.id.txt3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                try {
                    db = openOrCreateDatabase("testing.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                    db.execSQL("Create table test (name text, id integer PRIMARY KEY AUTOINCREMENT, designation text)");
                    Toast.makeText(getApplicationContext(), "Created!!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn2:
                ContentValues values = new ContentValues();
                values.put("name", "RAMBO");
                values.put("designation", "CEO");
                db.insert("test", null, values);
                break;
            case R.id.btn3:
                try {
                    db = openOrCreateDatabase("testing.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                    Cursor c = db.rawQuery("Select * from test", null);
                    c.moveToFirst();
                    while (!c.isAfterLast()) {
                        name = c.getString(0);
                        id = c.getString(1);
                        desg = c.getString(2);
                        c.moveToNext();
                    }
                    t1.setText(name);
                    t2.setText(id);
                    t3.setText(desg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn4:
                Intent intent = new Intent(getApplicationContext(),JsonActivity.class);
                startActivity(intent);
                break;
            case R.id.btn5:
                final Dialog dialog = new Dialog(getApplicationContext());
                dialog.setContentView(R.layout.login);
                dialog.setTitle("Login");
                final EditText editTextUserName=(EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
                final  EditText editTextPassword=(EditText)dialog.findViewById(R.id.editTextPasswordToLogin);
                Button btnSignIn=(Button)dialog.findViewById(R.id.buttonSignIn);
                btnSignIn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userName=editTextUserName.getText().toString();
                        String password=editTextPassword.getText().toString();
                        String storedPassword = getSinlgeEntry(userName);
                    }
                });
                break;
            case R.id.btn6:
                Intent intent1 = new Intent(getApplicationContext(),SharedActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn7:
                Intent intent2 = new Intent(getApplicationContext(),MaPActivity.class);
                startActivity(intent2);
                break;

        }
    }

     public  String getSinlgeEntry(String userName) {
//        db = openOrCreateDatabase("testing.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
//        Cursor c = db.rawQuery("Select desg from test where name ="+userName, null);
return userName;
    }
}
