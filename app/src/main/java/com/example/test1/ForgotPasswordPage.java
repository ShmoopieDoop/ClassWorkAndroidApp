package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ForgotPasswordPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_page);
        
        findViewById(R.id.update_creds_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usernameField = findViewById(R.id.forgot_username_field);
                EditText oldPasswordField = findViewById(R.id.forgot_old_password_field);
                EditText newPasswordField = findViewById(R.id.forgot_new_password_field);
                EditText confirmNewPasswordField = findViewById(R.id.forgot_conf_new_password_field);
                
                String username = usernameField.getText().toString();
                String oldPassword = oldPasswordField.getText().toString();
                String newPassword = newPasswordField.getText().toString();
                String confirmNewPassword = confirmNewPasswordField.getText().toString();
                
                if (!newPassword.equals(confirmNewPassword)) return;
                
                DBHelper dbHelper = new DBHelper(ForgotPasswordPage.this);

                TextView result = findViewById(R.id.forgot_result_text);
                if (dbHelper.updateCredentials(username, oldPassword, newPassword)) {
                    result.setText("Password updated Successfully");
                }
                else {
                    result.setText("Password failed to update");
                }
                
                
                
            }
        });
    }
}