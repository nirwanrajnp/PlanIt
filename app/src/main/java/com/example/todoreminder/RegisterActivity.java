package com.example.todoreminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
// uses firebase to make an account for logging in the app
public class RegisterActivity extends AppCompatActivity {

    EditText email, password;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

    }

    public void launchCancel (View view){
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }
    public void launchSignIn (View view){
        String emailid = email.getText().toString();
        String pwd = password.getText().toString();
        if(emailid.isEmpty()){
            email.setError("Please Enter Email");
            email.requestFocus();
        }
        else if(pwd.isEmpty()){
            password.setError("Please Enter Password");
            password.requestFocus();
        }
        else if(emailid.isEmpty() && pwd.isEmpty()){
            Toast.makeText(RegisterActivity.this, "Fields are Empty!", Toast.LENGTH_SHORT).show();
        }
        else if(!(emailid.isEmpty() && pwd.isEmpty())){
            mFirebaseAuth.createUserWithEmailAndPassword(emailid, pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!(task.isSuccessful())){
                        Toast.makeText(RegisterActivity.this, "Sign Up Unsuccessful! Please Try Again", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent (RegisterActivity.this, MainActivity.class);
                        startActivity(intent1);
                    }
                }
            });
        }
        else{
            Toast.makeText(RegisterActivity.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
        }
    }

}
