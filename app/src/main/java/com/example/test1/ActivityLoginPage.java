package com.example.test1;

import static com.example.test1.DBHelper.tableName;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityLoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        
        findViewById(R.id.login_submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usernameField = findViewById(R.id.login_username_field);
                EditText passwordField = findViewById(R.id.login_password_field);
                
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();

                SQLiteDatabase sqdb = (new DBHelper(ActivityLoginPage.this)).getReadableDatabase();
                Cursor cursor = sqdb.query(tableName, null,
                        DBHelper.userName + "= ? AND " + DBHelper.userPassword + "= ?"
                        , new String[] {username, password}, null, null, null, null);
                cursor.moveToFirst();
                if (cursor.getCount() != 0) {
                    TextView output = findViewById(R.id.user_output_text);
                    User user = DBHelper.getCurrentUser(cursor);
                    
                    output.setText(user.toString());
                }
                cursor.close();
                sqdb.close();
            }
        });
        
        findViewById(R.id.forgot_password_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityLoginPage.this, ForgotPasswordPage.class);
                startActivity(intent);
            }
        });
    }
}