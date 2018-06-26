package com.tictapps.realm_poc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.tictapps.realm_poc.R;
import com.tictapps.realm_poc.helper.RealmHelper;

public class AddActivity extends AppCompatActivity {

    private RealmHelper realmHelper;
    private EditText inputName;
    private EditText inputSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        realmHelper = new RealmHelper(AddActivity.this);

        inputName = findViewById(R.id.inputName);
        inputSurname = findViewById(R.id.inputSurname);
        Button saveButton = findViewById(R.id.save);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, surname;

                name = inputName.getText().toString();
                surname = inputSurname.getText().toString();

                realmHelper.addUser(name, surname);

                finish();

                Intent i = new Intent(AddActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
} 