package com.enggemy22.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.enggemy22.afinal.DataBase.LOGINController;

public class MainActivity extends AppCompatActivity {
    EditText txt_name;
    EditText txt_password;
    Button log_in;
    TextView create_account;
    LOGINController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller=new LOGINController(getApplicationContext());
        controller.openDB();
        initialization();
        onClick();
    }
    private void initialization(){
        txt_name = findViewById(R.id.useer_name);
        txt_password = findViewById(R.id.password);
        log_in = findViewById(R.id.logIn);
        create_account = findViewById(R.id.create_Account);
    }
    private void onClick(){
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=txt_name.getText().toString();
                String pass=txt_password.getText().toString();
                if(controller.validation(user,pass)){
                    Toast.makeText(getApplicationContext(),"LOG IN SUCCESSFULLY",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(), posts.class);
                    intent.putExtra("user_name",txt_name.getText().toString());
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"INVALID USER OR PASSWORD",Toast.LENGTH_LONG).show();
                }
            }
        });
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, regrestration.class);
                startActivity(intent);
                finish();

            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        controller.closeDB();
    }
}
