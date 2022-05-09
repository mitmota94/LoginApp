package com.example.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Objects;
import java.util.regex.Pattern;


public class SecondActivity extends AppCompatActivity {

    ImageView imageBack;
    EditText emailVal;
    EditText passwordVal;
    EditText repPassVal;
    String TAG = "*****SecondActivity";

   // TextView wrongEmail;
    TextView wrongPass_1;
    TextView wrongPass_2;
    Button btnNext_2;
    final private  boolean [] vErrors = {true,true,true};

    Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_second);
        imageBack = findViewById(R.id.iv_1);
        emailVal = findViewById(R.id.et_1);
        passwordVal = findViewById(R.id.et_2);
        repPassVal = findViewById(R.id.et_3);
       // wrongEmail = findViewById(R.id.tv_wrongEmail);
        wrongPass_1 = findViewById(R.id.tv_wrongPass_1);
        wrongPass_2 = findViewById(R.id.tv_wrongPass_2);
        btnNext_2 = findViewById(R.id.btnNext_1);
        imageBack.setOnClickListener(view -> {

            Intent intent = new Intent();
            intent.setClass(SecondActivity.this,MainActivity.class);
            startActivity(intent);


        });



        emailVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable v) {

               String email = emailVal.getText().toString();

                if(email.isEmpty()){
                    vErrors[0]= false;
                    //Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
                }else if(emailVal.getText().toString().trim().matches(emailPattern)){
                    vErrors[0]= true;
                     //  Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                    emailVal.setCompoundDrawablesWithIntrinsicBounds(null,null,getDrawable(R.drawable.tick),null);
                } else{
                    vErrors[0]= false;
                   // Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                    emailVal.setCompoundDrawablesWithIntrinsicBounds(null,null,getDrawable(R.drawable.cross),null);
                }

                if(valErrors())
                {
                    btnNext_2.setEnabled(true);
                }else {
                    btnNext_2.setEnabled(false);

                }
            }


        });


        passwordVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String passwordInput = passwordVal.getText().toString().trim();

                if (passwordInput.isEmpty()) {
                    vErrors[1]= false;
                  //  passwordVal.setError("Enter Password");
                }
                else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()){
                    vErrors[1]= false;
                    wrongPass_2.setVisibility(View.VISIBLE);
                }

                 else {
                     vErrors[1]=true;
                    wrongPass_2.setVisibility(View.INVISIBLE);
                    passwordVal.setCompoundDrawablesWithIntrinsicBounds(null,null,getDrawable(R.drawable.tick),null);
                }
                if(valErrors())
                {
                    btnNext_2.setEnabled(true);
                }else {
                    btnNext_2.setEnabled(false);

                }

            }

        });
        repPassVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (passwordVal.getText().toString().equals(repPassVal.getText().toString())) {
                    vErrors[2] = true;
                    repPassVal.setCompoundDrawablesWithIntrinsicBounds(null, null, getDrawable(R.drawable.tick), null);


                } else {
                    vErrors[2] = false;
                }

                Log.d(TAG, String.valueOf(valErrors()));
                if(valErrors())
                {
                    btnNext_2.setEnabled(true);

                }else {
                    btnNext_2.setEnabled(false);

                }
            }




        });
        btnNext_2.setOnClickListener(view -> {
           // Toast.makeText(getApplicationContext(), "Finish", Toast.LENGTH_SHORT).show();
            finish();
        });


}
    private boolean valErrors() {
        return(vErrors[0] && vErrors[1] && vErrors[2]);
    }
}
