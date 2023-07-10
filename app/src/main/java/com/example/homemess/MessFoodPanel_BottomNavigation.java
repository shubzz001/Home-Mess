package com.example.homemess;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.homemess.messFoodPanel.MessHomeFragment;
import com.example.homemess.messFoodPanel.MessOrderFragment;
import com.example.homemess.messFoodPanel.MessPendingOrderFragment;
import com.example.homemess.messFoodPanel.MessProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MessFoodPanel_BottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_food_panel_bottom_navigation);
        BottomNavigationView navigationView = findViewById(R.id.mess_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        String name = getIntent().getStringExtra("PAGE");

        if (name != null) {
            if (name.equalsIgnoreCase("Orderpage")) {
                loadmessfragment(new MessPendingOrderFragment());
            } else if (name.equalsIgnoreCase("Confrimpage")) {
                loadmessfragment(new MessOrderFragment());
            } else if (name.equalsIgnoreCase("AcceptOrderpage")) {
                loadmessfragment(new MessOrderFragment());
            } else if (name.equalsIgnoreCase("Deliveredpage")) {
                loadmessfragment(new MessOrderFragment());
            }
        } else {
            loadmessfragment(new MessHomeFragment());
        }
    }
    /**
     * Called when an item in the navigation menu is selected.
     *
     * @param item The selected item
     * @return true to display the item as the selected item and false if the item should not be
     * selected. Consider setting non-selectable items as disabled preemptively to make them
     * appear non-interactive.
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.messHome:
                fragment=new MessHomeFragment();
                break;
            case R.id.PendingOrders:
                fragment=new MessPendingOrderFragment();
                break;
            case R.id.Orders:
                fragment=new MessOrderFragment();
                break;
            case R.id.messProfile:
                fragment=new MessProfileFragment();
                break;
        }
        return loadmessfragment(fragment);
    }

    private boolean loadmessfragment(Fragment fragment) {

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
            return true;
        }
        return false;
    }

}