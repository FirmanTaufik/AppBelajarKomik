package com.app.appbelajarkomik.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.appbelajarkomik.R;
import com.app.appbelajarkomik.model.UserModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText edtEmail,edtNama,edtPassword1,edtPassword2;
    private CheckBox checkbox;
    private DatabaseReference mDatabase;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        edtEmail = findViewById(R.id.edtEmail);
        edtNama = findViewById(R.id.edtNama);
        edtPassword1 = findViewById(R.id.edtPassword1);
        edtPassword2 = findViewById(R.id.edtPassword2);
        checkbox = findViewById(R.id.checkbox);
        progressBar = findViewById(R.id.progressBar);
    }

    public void post(View view) {
        if (edtEmail.getText().toString().isEmpty()) {
            edtEmail.setError("Masih Kosong");
            return;
        }

        if (edtNama.getText().toString().isEmpty()) {
            edtNama.setError("Masih Kosong");
            return;
        }


        if (edtPassword1.getText().toString().isEmpty()) {
            edtPassword1.setError("Masih Kosong");
            return;
        }


        if (edtPassword2.getText().toString().isEmpty()) {
            edtPassword2.setError("Masih Kosong");
            return;
        }

        if (!edtPassword1.getText().toString().equals(edtPassword2.getText().toString())) {
            Toast.makeText(this, "password tidak sama", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!checkbox.isChecked()) {
            Toast.makeText(this, "anda meyetujui segala ketentuan aplikasi", Toast.LENGTH_SHORT).show();
            return;
        }

        sendToDatabase();


    }

    private void sendToDatabase() {
        progressBar.setVisibility(View.VISIBLE);
        UserModel user = new UserModel();
        user.setEmail(edtEmail.getText().toString());
        user.setNama(edtNama.getText().toString());
        user.setPassword(edtPassword1.getText().toString());
        String userId = mDatabase.push().getKey();
        mDatabase.child("users").child(userId).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(RegisterActivity.this, "register berhasil silahkan login", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(RegisterActivity.this, "terjadi kesalahan silahkan coba lagi", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}