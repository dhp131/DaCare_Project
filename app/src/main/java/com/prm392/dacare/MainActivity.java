package com.prm392.dacare;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.prm392.dacare.databinding.ActivityMainBinding;
import com.prm392.dacare.ui.cart.CartFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_routine, R.id.navigation_notifications, R.id.navigation_user)
//                .build();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_routine, R.id.navigation_cart, R.id.navigation_user)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        // Custom navigation listener to handle Cart refresh
        navView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_cart) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment navHostFragment = fragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main);
                if (navHostFragment != null) {
                    Fragment currentFragment = navHostFragment.getChildFragmentManager().getFragments().get(0);
                    if (currentFragment instanceof CartFragment) {
                        // Refresh the existing CartFragment
                        ((CartFragment) currentFragment).refreshCart();
                        return true;
                    }
                }
                // Navigate to CartFragment if not already visible
                navController.navigate(R.id.navigation_cart);
                return true;
            }
            // Default navigation for other items
            navController.navigate(itemId);
            return true;
        });
    }

}