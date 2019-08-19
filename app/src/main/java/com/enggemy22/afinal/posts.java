package com.enggemy22.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class posts extends AppCompatActivity {
    private RecyclerView recyclerView;   // recycle View
    private imageAdapter adapter;       // classImageAdpter
    private DatabaseReference mReference; //database
    private List<upload> uploads; //list
    private ProgressBar progressBar; // progressBar
    private Button btn_edit;
    private Button btn_add;
    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

            //Buttun
        btn_edit= findViewById(R.id.edit);
        btn_add= findViewById(R.id.addPost);

        Intent intent=getIntent();
        userName=intent.getStringExtra("user_name");
        //recyle View
        recyclerView= findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //array List
        uploads= new ArrayList<>();
        //progressbar
        progressBar=findViewById(R.id.progress);
        progressBar.setVisibility(View.INVISIBLE);
        //database get item from databas;
        mReference= FirebaseDatabase.getInstance().getReference("uploads");
        //EventVAlueEventListener as set on click listner
        //take to function on changes as contain for loop to get Image
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    upload up= snapshot.getValue(upload.class);
                    uploads.add(up);
                }
                adapter= new imageAdapter(posts.this,uploads);
                recyclerView.setAdapter(adapter);

                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
            //move to editActivity
            //exist proplem here
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), edit_account.class);
                intent.putExtra("user_name",userName);
                startActivity(intent);
            }
        });
        //move to add Post
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(posts.this,CreatePost.class);
                startActivity(intent);
            }
        });

    }
}
