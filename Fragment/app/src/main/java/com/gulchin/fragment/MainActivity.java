package com.gulchin.fragment;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView fragmentNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentNavigation = findViewById(R.id.fragment_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  new FirstFragment()).commit();

        fragmentNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch(item.getItemId()) {
                    case R.id.contacts:
                        fragment = new FirstFragment();
                        break;
                    case R.id.calling:
                         fragment = new SecondFragment();
                        break;
                    case R.id.add_contact:
                        fragment = new ThirdFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  fragment).commit();
                return false;
            }
        });
    }
}