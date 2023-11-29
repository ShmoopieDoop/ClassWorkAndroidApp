package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class showAllUsers extends AppCompatActivity {
    
    ArrayList<User> usersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_users);
        DBHelper dbHelper = new DBHelper(showAllUsers.this);
        usersList = dbHelper.getUsers();
        
        ListView usersListView = findViewById(R.id.users_list);
        ArrayAdapter<User> adapter = new ArrayAdapter<>(this,
                R.layout.list_view_item, usersList);
        usersListView.setAdapter(adapter);
    }
}