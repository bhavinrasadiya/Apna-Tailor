package com.example.apnatailer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class Signup extends AppCompatActivity implements View.OnClickListener {

    EditText etSName, etSMobile, etSEmail, etSPassword, etSConfirmpassword;
    Button btnSSingup;
    TextView tvLogin;
    FirebaseDatabase database;
    DatabaseReference reff;
    Member member;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    long maxid = 0;
    private String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etSName = (EditText) findViewById(R.id.etSName);
        etSMobile = (EditText) findViewById(R.id.etSMobile);
        etSEmail = (EditText) findViewById(R.id.etSEmail);
        etSPassword = (EditText) findViewById(R.id.etSPassword);
        etSConfirmpassword = (EditText) findViewById(R.id.etSConfirmpassword);
        btnSSingup = (Button) findViewById(R.id.btnSSingup);
        tvLogin = (TextView) findViewById(R.id.tvLogin);
        mobile = etSMobile.getText().toString();
        btnSSingup.setOnClickListener(Signup.this);
        tvLogin.setOnClickListener(Signup.this);
        database=FirebaseDatabase.getInstance();
        reff=database.getReference("Users");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSSingup:
                Register();
                break;
            case R.id.tvLogin:
                startActivity(new Intent(Signup.this, MainActivity.class));
                break;
        }
    }

    private void Register() {
        final Member member=new Member(etSName.getText().toString(),etSMobile.getText().toString(),etSEmail.getText().toString(),etSPassword.getText().toString());
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(member.getMobile()).exists()) {
                    Toast.makeText(Signup.this, "Already Exist", Toast.LENGTH_SHORT).show();

                }
                else {
                    reff.child(member.getMobile()).setValue(member);
                    Toast.makeText(Signup.this, "Successfully Register", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Signup.this,MainActivity.class));
                    Signup.this.finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
