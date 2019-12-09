package com.devzamse.appoficial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.devzamse.appoficial.InicioNavigation.InicioNavigation;
import com.devzamse.appoficial.ListaPersonajesFragment.ListaPersonajesFragment;
import com.devzamse.appoficial.MapsNavigation.MapsNavigation;
import com.devzamse.appoficial.PerfilNavigation.PerfilNavigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defaultFragment (new MapsNavigation());
        BottomNavigationView bottomNavigationView = findViewById(R.id.NavigationBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment frg = null;
        switch (menuItem.getItemId()){
            case R.id.inicio:
                frg = new ListaPersonajesFragment();
                break;

            case R.id.perfil:
                frg = new MapsNavigation();
                break;

            case R.id.directorio:
                frg = new InicioNavigation();
                break;

            case R.id.opciones:
                frg = new PerfilNavigation();
                break;
        }
        return defaultFragment(frg);
    }

    private boolean defaultFragment(Fragment frg) {
        if (frg!= null){
            getSupportFragmentManager().beginTransaction().replace(R.id.NoContribuyente,frg).commit();
            return true;
        }
        return false;
    }
}