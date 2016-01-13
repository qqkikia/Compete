package com.pillowtechnologies.mohamedaliaddi.compete;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    ArrayList<String> perms = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        perms.add("public_profile");
        if(AccessToken.getCurrentAccessToken() != null && Profile.getCurrentProfile() != null){

        }
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                toGeneral();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        CirclePageIndicator cp = (CirclePageIndicator)findViewById(R.id.indicator);
        cp.setViewPager(pager);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

                case 0: return LoginActivityFragment.newInstance("LoginActivityFragment, Instance 1");
                case 1: return LoginActivityFragment.newInstance("LoginActivityFragment, Instance 2");
                case 2: return LoginActivityFragment.newInstance("LoginActivityFragment, Instance 3");
                case 3: return LoginActivityFragment.newInstance("LoginActivityFragment, Instance 4");
                case 4: return LoginActivityFragment.newInstance("LoginActivityFragment, Instance 5");
                default: return LoginActivityFragment.newInstance("LoginActivityFragment, default");
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    }


    public void toSignUp(View view){
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    public void toGeneral(){
        Intent intent = new Intent (this,GeneralActivity.class);
        startActivity(intent);
    }
    public void Login(View view){

        toGeneral();

    }

    public void toChatLogin(View view){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }




}


