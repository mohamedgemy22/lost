package com.enggemy22.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.enggemy22.afinal.DataBase.AccountClass;
import com.enggemy22.afinal.DataBase.LOGINController;

public class edit_account extends AppCompatActivity {

    TextView user;
    EditText name;
    EditText pass;
    EditText phone;
    TextView gender;
    Button btn_savedata;
    Button btn_delete;
    LOGINController controller;
    AccountClass account;
    String userName;
    long res=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        controller=new LOGINController(getApplicationContext());
        controller.openDB();
        initialization();
        onClick();
    }
    private void initialization(){
        name=findViewById(R.id.edt_name);
        pass=findViewById(R.id.edt_pass);
        phone=findViewById(R.id.edt_phone);
        gender=findViewById(R.id.tv_gender);
        btn_savedata=findViewById(R.id.btn_savedata);
        btn_delete=findViewById(R.id.btn_delete);
        user=findViewById(R.id.tv_user);
        Intent intent=getIntent();
        userName=intent.getStringExtra("user_name");
        user.setText(userName);
        account=new AccountClass();
        account=controller.getAccount(userName);
        name.setText(account.getFullName());
        pass.setText(account.getPassword());
        phone.setText(String.valueOf(account.getNumber()));
        gender.setText(account.getGender());
    }
    private void onClick(){
        btn_savedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                res=controller.updateAccount(userName,name.getText().toString(),pass.getText().toString(),
                        Integer.parseInt(phone.getText().toString()));
                if(res>0){
                    Toast.makeText(getApplicationContext(),"DONE",Toast.LENGTH_LONG).show();
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"FAILED",Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.delete(userName);
                Toast.makeText(getApplicationContext(),"Account Deleted Successfully",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        controller.closeDB();
    }


}
