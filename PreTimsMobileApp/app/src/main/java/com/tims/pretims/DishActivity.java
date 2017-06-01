package com.tims.pretims;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DishActivity extends AppCompatActivity {

    private List<Dish> dishList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createDishList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.login_menuItem)
        {
            return true;
        }
        else if (id == R.id.history_menuItem)
        {
            return true;
        }
        else if (id == R.id.settings_menuItem)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createDishList()
    {
        dishList = new ArrayList();

        for(int i = 0; i<20; i++)
        {
            dishList.add(new Dish(Integer.toString(i), "Plat n° "+i, "kjhsdbdfsbsdfjhbdfsjhbjsdhfbjhbdfs", "fejhuzedbuvuvsdusbdvdsvbudvsjkbfdvjkfdjkfdjkfdbsbdfnfdbsbfsdbbhfdbshfdsvbknjkasdjknsdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaajknsdackjnsdacnkknjdasnsdacknjdscaknkdnsajknsdankjdnkjdsankjdsnkjjkndasnjkdasc", null));
        }


        MyItemRecyclerViewAdapter adapter = new MyItemRecyclerViewAdapter(dishList, null);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listDish_recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
