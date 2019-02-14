package com.example.abc.sqliteregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.abc.sqliteregister.LoginActivity.USERID;
import static com.example.abc.sqliteregister.LoginActivity.USERIEMAIL;
import static com.example.abc.sqliteregister.LoginActivity.USERNAME;
import static com.example.abc.sqliteregister.LoginActivity.USERPASSWORD;

public class ProfileActivity extends AppCompatActivity {
TextView emailholder;
Button logout;
String id,name,email,password;
    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String MYID = "id1";
    public static final String MYNAME = "name1";
    public static final String MYEMAIL = "email1";
    public static final String MYPASS = "pass1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        emailholder=(TextView)findViewById(R.id.emailholder);
        logout=(Button)findViewById(R.id.logout);
        Intent intent=getIntent();
        id=intent.getStringExtra(USERID);
        name=intent.getStringExtra(USERNAME);
        email=intent.getStringExtra(USERIEMAIL);
        password=intent.getStringExtra(USERPASSWORD);

        emailholder.setText(email);
        sharedPreferences=getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.commit();
                Intent intent=new Intent(ProfileActivity.this,RegisterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
                finish();
            }
        });
    }
}
