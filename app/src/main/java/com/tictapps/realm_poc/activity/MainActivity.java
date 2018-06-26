package com.tictapps.realm_poc.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tictapps.realm_poc.R;
import com.tictapps.realm_poc.adapter.AdapterUser;
import com.tictapps.realm_poc.helper.RealmHelper;
import com.tictapps.realm_poc.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RealmHelper helper;
    private List<User> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        data = new ArrayList<>();
        helper = new RealmHelper(MainActivity.this);

        recyclerView = (RecyclerView) findViewById(R.id.rvUser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddActivity.class));
                finish();
            }
        });

        setRecyclerView();
    }


    public void setRecyclerView() {
        try {
            data = helper.findAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AdapterUser adapter = new AdapterUser(data, new AdapterUser.OnItemClickListener() {
            @Override
            public void onClick(User item) {
                Intent i = new Intent(getApplicationContext(), EditActivity.class);
                i.putExtra("id", item.getId());
                i.putExtra("name", item.getName());
                i.putExtra("surname", item.getSurname());
                startActivity(i);
                finish();
            }
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            data = helper.findAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setRecyclerView();
    }
}

