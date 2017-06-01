package com.tims.pretims;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileOutputStream;
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
        MenuNavBar.setConnectionVisibility(menu, this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (MenuNavBar.checkItemSelected(this, id))
        {
            finish();
        }

        return true;
        //return super.onOptionsItemSelected(item);

    }

    /**
     * Create the dish list and display it
     */
    private void createDishList()
    {
        dishList = new ArrayList();

        //create the request
        Request request = new Request.Builder()
                                .url(AllConstants.GET_DISH_LIST)
                                .build();
        //send the request
        sendRequest(request);
    }

    /**
     * Display the list
     */
    private void displayList()
    {
        final Context thisContext = this;
        DishItemFragment.OnListFragmentInteractionListener listener = new DishItemFragment.OnListFragmentInteractionListener() {
            @Override
            public void onListFragmentInteraction(Dish item) {
                startDetailsActivity(item);
            }
        };

        //create the list to display
        final DishItemRecyclerViewAdapter adapter = new DishItemRecyclerViewAdapter(dishList, listener);

        //display the list
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listDish_recyclerView);

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(thisContext));
            }
        });

    }

    /**
     * Send the request for getting the dish's list
     * @param request => Request to send
     */
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
                Log.i("Request list dish", jsonString);
                JsonParser jsonParser = new JsonParser();
                //JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonString);
                JsonArray jsonArray = (JsonArray) jsonParser.parse(jsonString);

                for(int i = 0; i < Integer.parseInt(response.header("NumberDishies")); i++)
                {
                    JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();

                    //get image
                    byte[] decodedImage = Base64.decode(jsonObject.get("disImage").toString(), Base64.DEFAULT);
                    Bitmap image = BitmapFactory.decodeByteArray(decodedImage, 0, decodedImage.length);

                    //set class dish
                    Dish dish = new Dish(
                            jsonObject.get("idDish").getAsString(),
                            jsonObject.get("disName").getAsString(),
                            jsonObject.get("disComposition").getAsString(),
                            jsonObject.get("disDescription").getAsString(),
                            image
                    );
                    dishList.add(dish);

                    displayList();
                }

            }
        });
    }

    /**
     * Start the activity for displaying the detail of a dish
     * @param dish => Datas of the dish
     */
    private void startDetailsActivity(Dish dish)
    {
        Intent intent = new Intent(DishActivity.this, DetailDishActivity.class);

        intent.putExtra("disId", dish.getId());
        intent.putExtra("disName", dish.getName());
        intent.putExtra("disComposition", dish.getComposition());
        intent.putExtra("disDescription", dish.getDescription());
        intent.putExtra("disImage", saveToInternalStorage( dish.getImage()));


        startActivity(intent);

        this.finish();
    }

    /**
     * Take from http://stackoverflow.com/questions/17674634/saving-and-reading-bitmaps-images-from-internal-memory-in-android
     * @param bitmapImage => Image to save
     * @return => path to the saved image
     */
    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir(AllConstants.NAME_DIRECTORY_DISH_IMAGE, Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory, AllConstants.NAME_FILE_DISH_IMAGE);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
}
