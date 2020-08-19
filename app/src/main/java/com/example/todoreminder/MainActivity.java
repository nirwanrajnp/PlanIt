package com.example.todoreminder;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
// uses firebase to get into app
// also has option for guest mode
public class MainActivity extends AppCompatActivity {
    //public static final ArrayList<Item> ITEMS = new ArrayList<>();
    EditText email, password;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    RelativeLayout x, y;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
        x.setVisibility(View.VISIBLE);
        y.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x = (RelativeLayout) findViewById(R.id.login);
        y = (RelativeLayout) findViewById(R.id.frgt_regter);

        handler.postDelayed(runnable,1500);

        // Generate some default items to be used in guest mode

        email = (EditText) findViewById(R.id.email_login);
        password = (EditText) findViewById(R.id.pwd_login);
        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser!=null){
                    Toast.makeText(MainActivity.this, "You are Logged in!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, HomeActivities.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this, "Wrong Email or Password, Please Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
    public void launchHome (View view) {
        String emailid = email.getText().toString();
        String pwd = password.getText().toString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            if (emailid.isEmpty()) {
                email.setError("Please Enter Email");
                email.requestFocus();
            } else if (pwd.isEmpty()) {
                password.setError("Please Enter Password");
                password.requestFocus();
            } else if (emailid.isEmpty() && pwd.isEmpty()) {
                Toast.makeText(MainActivity.this, "Fields are Empty!", Toast.LENGTH_SHORT).show();
            } else if (!(emailid.isEmpty() && pwd.isEmpty())) {
                mFirebaseAuth.signInWithEmailAndPassword(emailid, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login Error, Try Again!", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intToHome = new Intent(MainActivity.this, HomeActivities.class);
                            startActivity(intToHome);
                        }
                    }
                });
            } else {
                Toast.makeText(MainActivity.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Connected to the guest mode button on the landing screen
    public void launchGuestMode(View view) {
        Intent intent = new Intent(this, HomeActivities.class);
        TextView textView = (TextView) findViewById(R.id.app_login);
        String message = textView.getText().toString();
        //intent.putExtra(CURRENT_USER, message);
        //Intent intent = new Intent(this, HomeActivities.class);
        startActivity(intent);
    }

    public void launchRegister (View view1){
        Intent intent1 = new Intent (this, RegisterActivity.class);
        startActivity(intent1);
    }

    public void launchForgot (View view1){
        Intent intent2 = new Intent (this, ForgotActivity.class);
        startActivity(intent2);
    }
}
