package edu.ucsb.cs.cs184.jaredbjensen.jjensendemosuite;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.w3c.dom.Text;

public class MainActivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static Fragment activeFragment;

    PictureFragment pictureFragment;
    FireworkFragment fireworkFragment;
    MovieFragment movieFragment;
    TextToSpeechFragment textToSpeechFragment;
    SpeechToTextFragment speechToTextFragment;

    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();


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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_picture_viewer) {
            pictureFragment = new PictureFragment();
            addFragment(R.id.layout_picture, pictureFragment);
        } else if (id == R.id.nav_fireworks) {
            fireworkFragment = new FireworkFragment();
            addFragment(R.id.layout_firework, fireworkFragment);
        } else if (id == R.id.nav_movie) {
            movieFragment = new MovieFragment();
            addFragment(R.id.layout_movie, movieFragment);
        } else if (id == R.id.nav_text_to_speech) {
            textToSpeechFragment = new TextToSpeechFragment();
            addFragment(R.id.layout_text_to_speech, textToSpeechFragment);
        } else if (id == R.id.nav_speech_to_text) {
            speechToTextFragment = new SpeechToTextFragment();
            addFragment(R.id.layout_speech_to_text, speechToTextFragment);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addFragment(int layout, Fragment fragment) {

        if (activeFragment == null) {
            transaction = fragmentManager.beginTransaction();
            transaction.add(R.id., fragment);
            transaction.commit();
            activeFragment = fragment;
            return;
        }

        transaction = fragmentManager.beginTransaction();
        transaction.remove(activeFragment).commit();
        transaction.replace(layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        activeFragment = fragment;
    }
}
