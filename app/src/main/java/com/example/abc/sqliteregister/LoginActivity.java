package com.example.abc.sqliteregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    boolean validEmail = false;
    boolean validPass = false;
    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String MYID = "id1";
    public static final String MYNAME = "name1";
    public static final String MYEMAIL = "email1";
    public static final String MYPASS = "pass1";
    //Declaration EditTexts
    EditText editTextEmail;
    EditText editTextPassword;

    //Declaration TextInputLayout
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    //Declaration Button
    Button buttonLogin;

    //Declaration SqliteHelper
    SqliteHelper sqliteHelper;
    TextView textViewCreateAccount;
    public  static final String USERID="id";
    public  static final String USERNAME="name";
    public  static final String USERIEMAIL="email";
    public  static final String USERPASSWORD="password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sqliteHelper = new SqliteHelper(this);

        initViews();
        sharedPreferences=getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        //set click event of login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check user input is correct or not
                if (isValidEmail()&&isValidPass()) {

                    //Get values from EditText fields
                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();

                    //Authenticate user
                    User currentUser = sqliteHelper.Authenticate(new User(null, null, Email, Password));

                    //Check Authentication is successful or not
                    if (currentUser != null) {
                        Snackbar.make(buttonLogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();

                        //User Logged in Successfully Launch You home screen activity
                        Intent intent=new Intent(LoginActivity.this,ProfileActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(USERID,currentUser.getId());
                        intent.putExtra(USERNAME,currentUser.getUserName());
                        intent.putExtra(USERIEMAIL,currentUser.getEmail());
                        intent.putExtra(USERPASSWORD,currentUser.getPassword());
                        editor.putString(MYID,currentUser.getId());
                        editor.putString(MYEMAIL,currentUser.getEmail());
                        editor.putString(MYNAME,currentUser.getUserName());
                        editor.putString(MYPASS,currentUser.getPassword());
                        editor.commit();
                        startActivity(intent);
                        finish();
                    } else {

                        //User Logged in Failed
                        Snackbar.make(buttonLogin, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();

                    }
                }
            }
        });

        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });


    }



    //this method is used to connect XML views to its Objects
    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        textViewCreateAccount=(TextView) findViewById(R.id.textViewCreateAccount);

    }





    //Handling validation for Email field
    public boolean isValidEmail(){
        String Email = editTextEmail.getText().toString();
        //Handling validation for Email field
        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            validEmail = false;
            textInputLayoutEmail.setError("Please enter valid email!");
        } else {
            validEmail = true;
            textInputLayoutEmail.setError(null);
        }
        return validEmail;

    }

    //Handling validation for Password field
    public boolean isValidPass(){
        String Password = editTextPassword.getText().toString();
        //Handling validation for Password field
        //Handling validation for Password field
        if (Password.isEmpty()) {
            validPass = false;
            textInputLayoutPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                validPass = true;
                textInputLayoutPassword.setError(null);
            } else {
                validPass = false;
                textInputLayoutPassword.setError("Password is to short!");
            }
        }
        return validPass;

    }


}
