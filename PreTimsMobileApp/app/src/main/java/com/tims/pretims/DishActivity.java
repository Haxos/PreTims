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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

//        for(int i = 0; i < 20; i++)
//        {
//            dishList.add(new Dish(Integer.toString(i), "Plat n° "+i, "kjhsdbdfsbsdfjhbdfsjhbjsdhfbjhbdfs", "fejhuzedbuvuvsdusbdvdsvbudvsjkbfdvjkfdjkfdjkfdbsbdfnfdbsbfsdbbhfdbshfdsvbknjkasdjknsdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaajknsdackjnsdacnkknjdasnsdacknjdscaknkdnsajknsdankjdnkjdsankjdsnkjjkndasnjkdasc", null));
//        }

        //create the request
        Request request = new Request.Builder()
                                .url(AllConstants.GET_DISH_LIST)
                                .build();
        //send the request
        sendRequest(request);
    }

    private void displayList()
    {
        //create the list to display
        MyItemRecyclerViewAdapter adapter = new MyItemRecyclerViewAdapter(dishList, null);

        //display the list
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listDish_recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void sendRequest(Request request)
    {
        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                Log.e("Request list dish", "Unable to get the response from the server");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                Gson gson = new Gson();

                //get JSON and convert to Json Object from Gson
                String jsonString = response.body().string();
                //jsonString = jsonString.replace("[", "{");
                //jsonString = jsonString.replace("]", "}");
                Log.i("Request list dish", jsonString);
                JsonParser jsonParser = new JsonParser();
                //JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonString);
                JsonArray jsonArray = (JsonArray) jsonParser.parse(jsonString);

                for(int i = 0; i < Integer.parseInt(response.header("NumberDishies")); i++)
                {
                    JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                    Dish dish = new Dish(
                            jsonObject.get("idDish").getAsString(),
                            jsonObject.get("disName").getAsString(),
                            jsonObject.get("disComposition").getAsString(),
                            jsonObject.get("disDescription").getAsString(),
                            null
                    );
                    dishList.add(dish);

                    displayList();
                }


            }
        });
    }
}
