package com.example.readapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.readapplication.Functions.CheckInputValue;
import com.example.readapplication.Object.Status;
import com.example.readapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp_Activity extends AppCompatActivity {
    private TextInputLayout sign_Up_LBL_Full_Name;
    private TextInputLayout sign_Up_LBL_Emil;
    private TextInputLayout sign_Up_LBL_Password;
    private Button sign_Up_BTN_Submit;

    private FirebaseAuth mAuth;
    private DatabaseReference reference;
    private CheckInputValue checkInputValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        checkInputValue = new CheckInputValue();

        findView();
        initButton();
    }

    private void initButton() {
        sign_Up_BTN_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValueAndStore();

            }
        });
    }

    // Checks if the data entered by user is logically correct
    private void checkValueAndStore() {
        if (!checkInputValue.validateName(sign_Up_LBL_Full_Name) |
                !checkInputValue.validateEmail(sign_Up_LBL_Emil) |
                !checkInputValue.validatePassword(sign_Up_LBL_Password)) {
            return;
        }

        String email = sign_Up_LBL_Emil.getEditText().getText().toString().trim();
        String password = sign_Up_LBL_Password.getEditText().getText().toString().trim();

        saveUserInDB(email, password);
    }

    // Save new user in DB and open the log in screen
    private void saveUserInDB(final String email, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            Status status = new Status();
                            status.setOn(false);

                            FirebaseDatabase.getInstance().getReference("STATUS/").child(FirebaseAuth.getInstance().getCurrentUser().getUid() + "/").setValue(status);

                            newActivity();
                        } else {
                            if (task.getException().getMessage().contains("email")) {
                                sign_Up_LBL_Emil.setError(task.getException().getMessage());
                            }
                        }
                    }
                });
    }

    private void newActivity() {
        finish();
    }


    private void findView() {
        sign_Up_LBL_Full_Name = findViewById(R.id.Sign_Up_LBL_Full_Name);
        sign_Up_LBL_Emil = findViewById(R.id.Sign_Up_LBL_Emil);
        sign_Up_LBL_Password = findViewById(R.id.Sign_Up_LBL_Password);
        sign_Up_BTN_Submit = findViewById(R.id.sign_Up_BTN_Submit);
    }
}