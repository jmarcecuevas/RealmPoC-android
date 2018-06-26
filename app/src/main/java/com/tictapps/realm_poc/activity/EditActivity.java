package com.tictapps.realm_poc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.tictapps.realm_poc.R;
import com.tictapps.realm_poc.helper.RealmHelper;

public class EditActivity extends AppCompatActivity {

    private int position;
    private EditText inputName, inputSurname;
    private RealmHelper helper;
    private String name, surname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        helper = new RealmHelper(EditActivity.this);
        position = getIntent().getIntExtra("id", 0);
        String intentName = getIntent().getStringExtra("name");
        String intentSurname = getIntent().getStringExtra("surname");


        Button deleteButton = findViewById(R.id.delete);
        Button saveButton = findViewById(R.id.save);

        inputName = findViewById(R.id.inputName);
        inputSurname = findViewById(R.id.inputSurname);

        inputName.setText(intentName);
        inputSurname.setText(intentSurname);

        deleteButton.setVisibility(View.VISIBLE);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.deleteData(position);

                startActivity(new Intent(EditActivity.this, MainActivity.class));
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = inputName.getText().toString();
                surname = inputSurname.getText().toString();

                helper.updateUser(position, name, surname);

                startActivity(new Intent(EditActivity.this, MainActivity.class));
                finish();
            }
        });
    }
} 