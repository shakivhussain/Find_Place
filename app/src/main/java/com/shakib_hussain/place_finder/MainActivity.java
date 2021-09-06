package com.shakib_hussain.place_finder;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.shakib_hussain.place_finder.Activities.Add_Pg_Activity;
import com.shakib_hussain.place_finder.Activities.ListenerEdit;
import com.shakib_hussain.place_finder.Activities.LoginActivity;
import com.shakib_hussain.place_finder.Adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ListenerEdit {

    ArrayList pgImages = new ArrayList<>(Arrays.asList(R.drawable.img1, R.drawable.im2,
            R.drawable.im3, R.drawable.im4, R.drawable.img1, R.drawable.im3, R.drawable.im2, R.drawable.im4));
    RecyclerView pgRecyclerView;

    // Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ConstraintLayout contentView;
    static final float END_SCALE = 0.7f;
    ImageView menuIcon;

    // Fab
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        menuIcon = findViewById(R.id.home_menu);
        fab = findViewById(R.id.home_fab);
        pgRecyclerView = findViewById(R.id.pgRecycler);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Add_Pg_Activity.class));
            }
        });

        setAdapter();
        navigationDrawer();

    }

    private void setAdapter() {

        pgRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HomeAdapter imageGalleryAdapter = new HomeAdapter(MainActivity.this, pgImages, this);
        pgRecyclerView.setAdapter(imageGalleryAdapter);

    }


    private void navigationDrawer() {

        // Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);    //
        navigationView = findViewById(R.id.navigation_view); // navigation drawer
        contentView = findViewById(R.id.contentView);


        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        navigationView.setCheckedItem(R.id.nav_bug_report); // For Check Item

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        animnateNavigationdrawer();
    }

    private void animnateNavigationdrawer() {

        drawerLayout.setScrimColor(getResources().getColor(R.color.m_white));
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);


                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }

        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_bug_report:
            case R.id.nav_about:

            case R.id.nav_help:
            case R.id.nav_logout:
                Toast.makeText(getApplicationContext(), getString(R.string.this_feature_under), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void onItemClicked(int id) {
        startActivity(new Intent(getApplicationContext(), EditPGActivity.class));

    }

    public void tryFunc(View view) {


        startActivity(new Intent(getApplicationContext(), LoginActivity.class));

    }
}