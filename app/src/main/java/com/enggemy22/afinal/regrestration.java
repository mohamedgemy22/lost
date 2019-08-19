package com.enggemy22.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.enggemy22.afinal.DataBase.LOGINController;

public class regrestration extends AppCompatActivity {
    EditText txt_name2;
    EditText txt_passwpord2;
    EditText txt_email;
    EditText txt_phone;
    Button btn_sign;
    RadioButton male;
    RadioButton fmale;
    RadioGroup radioGroup;
    String valueRadiobutton;
    LOGINController controller;
    long res=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regrestration);
        controller=new LOGINController(getApplicationContext());
        controller.openDB();
        initialization();
        onClick();
    }
    private void initialization(){
        txt_name2=findViewById(R.id.useer_name2);
        txt_passwpord2=findViewById(R.id.password2);
        txt_email=findViewById(R.id.email);
        txt_phone=findViewById(R.id.phone);
        btn_sign= findViewById(R.id.signup);
        male= findViewById(R.id.r_male);
        fmale= findViewById(R.id.r_famale);
        radioGroup= findViewById(R.id.radio);

    }
    private void onClick(){
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(male.isChecked())
                {
                    valueRadiobutton="Male";
                }
                else if (fmale.isChecked()){
                    valueRadiobutton="Female";
                }
                if(txt_name2.getText().equals("")||txt_email.getText().equals("")
                        ||txt_passwpord2.getText().equals("")||
                        txt_phone.getText().equals("")||(!male.isChecked()&&!fmale.isChecked())){
                    Toast.makeText(getApplicationContext(),"PLEASE ENTRE ALL DATA",Toast.LENGTH_LONG).show();
                }
                else{
                    res=controller.insertAccount(txt_name2.getText().toString(),txt_email.getText().toString(),txt_passwpord2.getText().toString(),
                            Integer.parseInt(txt_phone.getText().toString()),valueRadiobutton);
                    if(res>0) {
                        Toast.makeText(getApplicationContext(), "ACCOUNT CREATED SUCCESSFULLY!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), posts.class);
                        intent.putExtra("user_name",txt_email.getText().toString());
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Entre Another User Name", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        controller.closeDB();
    }

}
