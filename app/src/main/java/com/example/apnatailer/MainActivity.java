package com.example.apnatailer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvLSignup;
    EditText etLMobile, etLPassword;
    Button btnLLogin;
    FirebaseDatabase database;
    DatabaseReference reff;
    Member member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences("uName", MODE_PRIVATE);
        String as = sp.getString("Mobile", "not Found");
        if (!as.equals("not Found")) {
            startActivity(new Intent(MainActivity.this, Home.class));
            MainActivity.this.finish();
        } else {
            setContentView(R.layout.activity_main);

            database = FirebaseDatabase.getInstance();
            reff = database.getReference("Users");


            etLMobile = (EditText) findViewById(R.id.etLMobile);
            etLPassword = (EditText) findViewById(R.id.etLPassword);
            btnLLogin = (Button) findViewById(R.id.btnLLogin);
            tvLSignup = findViewById(R.id.tvLSignup);


            tvLSignup.setOnClickListener(MainActivity.this);
            btnLLogin.setOnClickListener(MainActivity.this);


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLLogin:
                if (etLMobile.getText().toString().isEmpty() || etLPassword.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();

                } else {
                    login(etLMobile.getText().toString(), etLPassword.getText().toString());
                    break;
                }
                break;

            case R.id.tvLSignup:
                startActivity(new Intent(MainActivity.this, Signup.class));
                this.finish();
                break;
        }
    }

    private void login(final String mobile, final String password) {
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(mobile).exists())
                {
                    if (!mobile.isEmpty())
                    {
                        Member login=dataSnapshot.child(mobile).getValue(Member.class);
                        if (login.getPassword().equals(password)) {
                            Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            SharedPreferences sp=getSharedPreferences("uName",MODE_PRIVATE);
                            SharedPreferences.Editor spedit = sp.edit();
                            spedit.putString("Mobile",etLMobile.getText().toString());
                            spedit.commit();
                            startActivity(new Intent(MainActivity.this,Home.class));
                            MainActivity.this.finish();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Please Fill All Field", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "User is not Registerd", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
