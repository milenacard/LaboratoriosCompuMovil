package co.edu.udea.compumovil.gr08_20171.Lab2.Activity.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import co.edu.udea.compumovil.gr08_20171.Lab2.Activity.adapters.EventViewHolder;
import co.edu.udea.compumovil.gr08_20171.Lab2.Activity.fragments.FragmentEventDetail;
import co.edu.udea.compumovil.gr08_20171.Lab2.Activity.fragments.FragmentEvents;
import co.edu.udea.compumovil.gr08_20171.Lab2.R;

public class MenuPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,EventViewHolder.OnEventSelectedListener {

    private Fragment fragmentEvents;
    private Fragment fragmentEventDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_completo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentEvents = new FragmentEvents();
        fragmentEventDetail = new FragmentEventDetail();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.button_add_event);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if(findViewById(R.id.content_menu_principal) != null){

            Fragment fragmentEvents = new FragmentEvents();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.content_menu_principal, fragmentEvents);
            transaction.commit();

        }
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
        getMenuInflater().inflate(R.menu.activity_principal_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_configuracion) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.item_perfil) {
            // Handle the camera action
        } else if (id == R.id.item_eventos) {
            showEventsFragment();
        } else if (id == R.id.item_configuracion) {

        } else if (id == R.id.item_cerrar_sesion) {

        } else if (id == R.id.item_acerca_de) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void showEventsFragment() {
        FragmentEventDetail fragmentEventDetail = (FragmentEventDetail) getSupportFragmentManager().findFragmentByTag("fragmentEventDetail");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (fragmentEvents.isAdded()) {
            fragmentTransaction.show(fragmentEvents);
        } else {
            fragmentTransaction.add(R.id.content_menu_principal, fragmentEvents,"fragmentEvents");
        }
        if (fragmentEventDetail.isAdded()) {
            fragmentTransaction.hide(fragmentEventDetail);
        }
        fragmentTransaction.commit();
    }

    protected void showEventsDetailFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (fragmentEventDetail.isAdded()) {
            fragmentTransaction.show(fragmentEventDetail);
            //TODO: llamar al metodo update para cambiar la informacion.
        } else {
            fragmentTransaction.add(R.id.content_menu_principal, fragmentEventDetail,"fragmentEventDetail");
        }
        if (fragmentEvents.isAdded()) {
            fragmentTransaction.hide(fragmentEvents);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onEventSelected(int position) {
        Log.d("TAG","presionado");
        FragmentEventDetail fragmentEventDetail = (FragmentEventDetail) getSupportFragmentManager().findFragmentByTag("fragmentEventDetail");
        FragmentEvents fragmentEvents = (FragmentEvents) getSupportFragmentManager().findFragmentByTag("fragmentEvents");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragmentEventDetail != null) {
            fragmentEventDetail.updateDetailEventInformation(position);
            transaction.show(fragmentEventDetail);
        } else {

            FragmentEventDetail newFragmentEventDetail = new FragmentEventDetail();
            Bundle bundleArguments = new Bundle();
            bundleArguments.putInt(FragmentEventDetail.ARG_POSITION, position);
            newFragmentEventDetail.setArguments(bundleArguments);

            transaction.replace(R.id.content_menu_principal, newFragmentEventDetail,"fragmentEventDetail");
            transaction.addToBackStack(null);

        }
        if (fragmentEvents != null && fragmentEvents.isAdded()) {
            transaction.hide(fragmentEvents);
        }
        transaction.commit();
    }
}
