package com.app.appbelajarkomik.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.app.appbelajarkomik.R;
import com.app.appbelajarkomik.model.UserModel;
import com.app.appbelajarkomik.utils.Constant;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText edtEmail, edtPassword;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setAds();

        if (!getIntent().hasExtra("isDetail")) {
            if (!Constant.getIsFirstTime(this)) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

            Constant.setIsFirstTime(this, false);

            if (Constant.getId(this)!=null) {
                startActivity(new Intent(this, MainActivity.class));
            }
        }


        findViewById(R.id.btnClose)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    }
                });


        mDatabase = FirebaseDatabase.getInstance().getReference();
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
    }

    private void setAds() {
        Constant.setBannerId(this,"22c95a7a8f8a4503");
        Constant.setInterId(this, "58a7b2c937ba1c2f");
        Constant.setNativeId(this,"58a7b2c937ba1c2f");
    }

    public void regsiter(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void login(View view) {
        if (edtEmail.getText().toString().isEmpty()) {
            edtEmail.setText("Masih kosong");
            return;
        }
        if (edtPassword.getText().toString().isEmpty()) {
            edtPassword.setText("Masih kosong");
            return;
        }


        mDatabase.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    UserModel userModel = postSnapshot.getValue(UserModel.class);
                    userModel.setKey(postSnapshot.getKey());

                    if (userModel.getEmail().equals(edtEmail.getText().toString())
                        && userModel.getPassword().equals(edtPassword.getText().toString())) {
                        Constant.setId(LoginActivity.this, userModel.getKey());
                        Toast.makeText(LoginActivity.this, "Login berhasil", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        return;
                    }

                    Toast.makeText(LoginActivity.this, "email atau password salah atau tidak terdaftar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(LoginActivity.this, "terjadi kesalahan silahkan coba lagi", Toast.LENGTH_SHORT).show();
            }
        });
    }


}