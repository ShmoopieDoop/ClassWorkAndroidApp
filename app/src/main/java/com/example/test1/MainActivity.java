package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView testText;
    EditText username, password, confirmPassword, email, phone;
    CheckBox allow;
    Button register, btnAllUsers;
    RadioGroup gender;
    RadioButton rdoMale, rdoFemale, rdoOther;
    User user;
    DBHelper dbh;
    Intent AllUsersScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        dbh = new DBHelper(MainActivity.this);

        btnAllUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllUsersScreen = new Intent(MainActivity.this, showAllUsers.class);
                startActivity(AllUsersScreen);
            }
        });



        allow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                String gendertxt = rdoMale.getText().toString();
                //Toast.makeText(MainActivity.this, allow.getText().toString(), Toast.LENGTH_SHORT).show();
                //rdoMale = findViewById(i);
                //int rdoI = gender.getCheckedRadioButtonId();

            }
        });





        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new User();
                SQLiteDatabase sqdb = dbh.getReadableDatabase();
                Cursor cursor = sqdb.query(DBHelper.tableName, null, null, null, null, null, null, null);

                user.setId("id" + cursor.getCount());
                sqdb.close();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                user.setPhone(phone.getText().toString());
                user.setEmail(email.getText().toString());

                user.setAllowContact("");

                if (allow.isChecked()) {
                    user.setAllowContact("Allowed");
                }

                if (rdoMale.isChecked()) {
                    user.setGender(rdoMale.getText().toString());
                } else if (rdoFemale.isChecked()) {
                    user.setGender(rdoFemale.getText().toString());
                } else if (rdoOther.isChecked()) {
                    user.setGender(rdoOther.getText().toString());
                }

                testText.setText(dbh.addUser(user));

                Animation animation = new AlphaAnimation(1.0f,0.0f);
                animation.setDuration(1000);
                register.startAnimation(animation);

            }
        });

        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityLoginPage.class);
                startActivity(intent);
            }
        });

    }

    private void initComponents() {
        btnAllUsers = findViewById(R.id.btnAllUsers);
        username = findViewById(R.id.etUserName);
        password = findViewById(R.id.etPassword);
        confirmPassword = findViewById(R.id.etConfirmPassword);
        email = findViewById(R.id.etEmail);
        phone = findViewById(R.id.etPhone);
        gender = (RadioGroup)findViewById(R.id.gender);
        rdoMale = findViewById(R.id.rdoMale);
        rdoFemale = findViewById(R.id.rdoFemale);
        rdoOther = findViewById(R.id.rdoOther);
        allow = (CheckBox)findViewById(R.id.cbAllow);
        register = findViewById(R.id.btnRegister);
        testText = findViewById(R.id.TestText);
    }
}