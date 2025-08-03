package com.example.botondapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.botondapp.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ViewBinding beállítása
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Toolbar használata
        setSupportActionBar(binding.toolbar);

        // A SecondFragment betöltése az indításkor
        if (savedInstanceState == null) {
            SecondFragment fragment = new SecondFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        }

        // Opcionális FAB viselkedés
        binding.fab.setOnClickListener(v ->
                Snackbar.make(v, "Itt lehetne egy gyors akció.", Snackbar.LENGTH_LONG)
                        .setAnchorView(binding.fab)
                        .setAction("OK", null)
                        .show()
        );
    }
}
