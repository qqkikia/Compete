package com.pillowtechnologies.mohamedaliaddi.compete;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GeneralActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    String[] drawerlist;
    DrawerLayout drawerLayout;
    ListView listView;
    Drawable d;
    Profile profile;
    ListView list;
    CustomAdapter adapter;
    GeneralActivity CustomListView = null;
    ArrayList<Event> currevents = new ArrayList<Event>();
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AccessToken.getCurrentAccessToken() != null && Profile.getCurrentProfile() != null) {
            Toast.makeText(this, "logged in", Toast.LENGTH_SHORT).show();
        }
        setContentView(R.layout.activity_general);
        list = (ListView) findViewById(R.id.list);
        CustomListView = this;
        setListData();
        Resources res = getResources();
        adapter = new CustomAdapter(CustomListView, currevents, res);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_general, menu);
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

    public Bitmap getPhotoFacebook(final String id) {

        Bitmap bitmap = null;
        final String nomimg = "https://graph.facebook.com/" + id + "/picture?type=large";
        URL imageURL = null;

        try {
            imageURL = new URL(nomimg);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection connection = (HttpURLConnection) imageURL.openConnection();
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();

            bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (IOException e) {

            e.printStackTrace();
        }
        return bitmap;

    }

    @Override
    public void onConnected(Bundle bundle) {

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);

        }
    }

    private void selectItem(int pos) {
        if (pos == 0) {
            Intent intent = new Intent(getApplicationContext(), NuSportenActivity.class);
            startActivity(intent);
        }
        if (pos == 1) {
            Intent intent = new Intent(getApplicationContext(), LaterSportenActivity.class);
            startActivity(intent);
        }
        if (pos == 2) {
            LoginManager.getInstance().logOut();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        }
    }


    public void toProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void toPlan(View view) {
        Intent intent = new Intent(this, PlanActivity.class);
        startActivity(intent);
    }

    public void toLadder(View view) {
        Intent intent = new Intent(this, LadderActivity.class);
        startActivity(intent);
    }

    public void toCurrent(View view) {
        Intent intent = new Intent(this, CurrentActivity.class);
        startActivity(intent);
    }

    public void hamburgerslide(View view) {

        drawerLayout.openDrawer(Gravity.LEFT);

    }


    public void setListData()
    {
        Event e1 = new Event();
        e1.setTitle("Event 1");
        Event e2 = new Event();
        e2.setTitle("Event 2");
        Event e3 = new Event();
        e3.setTitle("Event 3");
        Event e4 = new Event();
        e4.setTitle("Event 4");
        Event e5 = new Event();
        e5.setTitle("Event 5");
        currevents.add(e1);
        currevents.add(e2);
        currevents.add(e3);
        currevents.add(e4);
        currevents.add(e5);



    }



    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }
}