package com.example.abc.sqliteregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import static com.example.abc.sqliteregister.LoginActivity.USERID;
import static com.example.abc.sqliteregister.LoginActivity.USERIEMAIL;
import static com.example.abc.sqliteregister.LoginActivity.USERNAME;
import static com.example.abc.sqliteregister.LoginActivity.USERPASSWORD;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
 ImageView image;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String MYID = "id1";
    public static final String MYNAME = "name1";
    public static final String MYEMAIL = "email1";
    public static final String MYPASS = "pass1";
    String id=null,name=null,email=null,password=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(4000);
        rotate.setInterpolator(new LinearInterpolator());
        image= (ImageView) findViewById(R.id.imageView);
        image.startAnimation(rotate);
        sharedPreferences=getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        id=sharedPreferences.getString(MYID,null);
        name=sharedPreferences.getString(MYNAME,null);
        email=sharedPreferences.getString(MYEMAIL,null);
        password=sharedPreferences.getString(MYPASS,null);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(TextUtils.isEmpty(id)||TextUtils.isEmpty(name)||TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
                    Intent intent=new Intent(SplashActivity.this,RegisterActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent=new Intent(SplashActivity.this,ProfileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(USERID,id);
                    intent.putExtra(USERNAME,name);
                    intent.putExtra(USERIEMAIL,email);
                    intent.putExtra(USERPASSWORD,password);
                    startActivity(intent);
                    finish();
                }


            }
        },5000);
    }
}
