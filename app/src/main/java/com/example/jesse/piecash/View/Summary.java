package com.example.jesse.piecash.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.jesse.piecash.R;
import com.example.jesse.piecash.firebase.DataManager;

import java.util.ArrayList;

public class Summary extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DataManager manager = new DataManager();

        ArrayList<Group> groups = new ArrayList<>();

        ImageView image = (ImageView) findViewById(R.id.image);
        Bitmap bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(500, 500, 500, paint);
        paint.setColor(Color.RED);
        canvas.drawArc(0, 0, 1000, 1000, 0, (float) (-0.25*360), true, paint);
        paint.setColor(Color.BLUE);
        canvas.drawArc(0, 0, 1000, 1000, (float) (-0.25*360), (float) (-0.75*360), true, paint);
        paint.setColor(Color.WHITE);
        canvas.drawRect(500, 495, 1000, 505, paint);
        canvas.drawArc(0, 0, 1000, 1000, 1, -1, true, paint);
        canvas.drawArc(0, 0, 1000, 1000, (float) (-0.25*360 + 1), -1, true, paint);
        canvas.drawCircle(500, 500, 100, paint);
        //canvas.drawText("Person A", 400, 200, paint);
        image.setImageBitmap(bitmap);

        groups.add(new Group("yes", new ArrayList<Person>()));
        groups.add(new Group("no", new ArrayList<Person>()));
        groups.add(new Group("no", new ArrayList<Person>()));


        LinearLayout lin = (LinearLayout) findViewById(R.id.key);
        //lin.setPadding(50, 750 - groups.size()*30, 0, 0);
        TextView text;
        for (int i = 0; i < groups.size(); i++) {
            text = new TextView(this);
            text.setText("yes");
            text.setGravity(Gravity.LEFT);
            text.setTextSize(30);
            lin.addView(text);
        }
        //key.setTextColor(Color.parseColor("Blue"));
        //key.setText("hello");

        ListView list = (ListView) findViewById(R.id.debts);
        ArrayList<String> arr = new ArrayList<>();
        ArrayAdapter<String> debts = new ArrayAdapter<>(this,   android.R.layout.simple_list_item_1, arr);
        list.setAdapter(debts);
        debts.add("A owes B $15");
        debts.notifyDataSetChanged();

        ListView week = (ListView) findViewById(R.id.weekly);
        arr = new ArrayList<>();
        ArrayAdapter<String> purchases = new ArrayAdapter<>(this,   android.R.layout.simple_list_item_1, arr);
        week.setAdapter(purchases);
        purchases.add("Weekly Purchases");
        purchases.notifyDataSetChanged();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
