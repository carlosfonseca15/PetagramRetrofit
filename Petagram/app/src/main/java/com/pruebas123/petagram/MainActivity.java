package com.pruebas123.petagram;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.pruebas123.petagram.adapter.PageAdapter;
import com.pruebas123.petagram.db.BaseDatos;
import com.pruebas123.petagram.fragmnet.PerfilFragment;
import com.pruebas123.petagram.fragmnet.RecyclerviewFragment;
import com.pruebas123.petagram.pojo.Mascota;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<Mascota> mascotas_sort;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.top5:
                Toast.makeText(this, "Diste a top5", Toast.LENGTH_SHORT).show();
                //sortLista();
                irDetalleTop5();
                break;
            case  R.id.mContact:
                Intent intentC = new Intent(this, ActivityContact.class);
                startActivity(intentC);
                break;
            case R.id.mAbout:
                Intent intentA = new Intent(this, ActivityAbout.class);
                startActivity(intentA);
                break;
            case R.id.mCuenta:
                Intent intentCC = new Intent(this, ActivityCuenta.class);
                startActivity(intentCC);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.miactionbar);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        toolbar.setTitleTextColor(getResources().getColor(R.color.textoOscuro));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.dog_footprint);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setUpViewPager();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    private ArrayList<Fragment> agregarFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerviewFragment());
        fragments.add(new PerfilFragment());
        return  fragments;
    }
    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_dog_oscuro);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dog_claro);
        tabLayout.setTabIconTint(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
    }

    public void irDetalleTop5(){
        BaseDatos db = new BaseDatos(this);
        mascotas_sort = db.obtenerTodasLasMascotas("SI");
        Intent intent = new Intent(this, DetalleTop5.class);
        //intent.putExtra("FILES_TO_SEND", mascotas_sort);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)mascotas_sort);
        intent.putExtra("BUNDLE",args);
        startActivity(intent);
    }

}