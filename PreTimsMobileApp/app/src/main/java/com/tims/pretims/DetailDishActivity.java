package com.tims.pretims;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DetailDishActivity extends AppCompatActivity {

    private Dish dish = null;
    private UserDataSource userDatabase;
    private Button reservationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dish);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //create the back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //display the values of the dish
        displayValues();

        //if the user is logged then it display the reservation button
        userDatabase = new UserDataSource(this);
        userDatabase.open();

        reservationButton = (Button) findViewById(R.id.reservation_button);
        if(userDatabase.getLastToken() != null)
        {
            reservationButton.setVisibility(View.VISIBLE);
        }
        else
        {
            reservationButton.setVisibility(View.GONE);
        }

        userDatabase.close();
    }

    /**
     * Take from http://stackoverflow.com/questions/10108774/how-to-implement-the-android-actionbar-back-button
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (MenuNavBar.checkItemSelected(this, id))
        {
            finish();
        }

        return true;
        //return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuNavBar.setConnectionVisibility(menu, this);
        return true;
    }

    public void sendReservation(View v)
    {
        Gson gson = new Gson();
        userDatabase.open();
        String login = userDatabase.getLastUser().getLogin();
        userDatabase.close();

        //create the associative table
        final Map<String, String> values = new HashMap<String, String>();
        values.put("idDish", dish.getId());
        values.put("useLogin", login);

        //convert the associative table to a json
        String body = gson.toJson(values);

        Request request = new Request.Builder()
                .url(AllConstants.POST_RESERVATION)
                .post(RequestBody.create(AllConstants.MEDIA_TYPE_MARKDOWN, body))
                .build();

        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                Log.e("Request reservation", "Unable to get the response from the server");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                if(response.code() == 200)
                {
                    Log.i("Request reservation", "No problem");


                    returnToMainActivity();
                }
                else
                {
                    Log.e("Request reservation", "A problem occured during the reservation");
                }
            }
        });
    }

    /**
     * Display the values of the dish
     */
    private void displayValues()
    {
        //get dish object from the previous activity "DishActivity"
        Intent intent = getIntent();
        dish = new Dish(
                intent.getStringExtra("disId"),
                intent.getStringExtra("disName"),
                intent.getStringExtra("disComposition"),
                intent.getStringExtra("disDescription"),
                loadImageFromStorage(intent.getStringExtra(("disImage")))
        );

        //get the visuals componants
        TextView dishName_textView = (TextView) findViewById(R.id.nameDish_textView);
        TextView dishDescription_textView = (TextView) findViewById(R.id.descriptionDish_textView);
        TextView dishComposition_textView = (TextView) findViewById(R.id.compositionDish_textView);
        ImageView dishImage_textView = (ImageView) findViewById(R.id.imageDish_imageView);

        //set the visuals componants
        dishName_textView.setText(dish.getName());
        dishDescription_textView.setText(dish.getDescription());
        dishComposition_textView.setText(dish.getComposition());
        dishImage_textView.setImageBitmap(dish.getImage());
    }


    /**
     * It load an image from a local file
     * Take from http://stackoverflow.com/questions/17674634/saving-and-reading-bitmaps-images-from-internal-memory-in-android
     * @param path => Path to file
     */
    private Bitmap loadImageFromStorage(String path)
    {

        try {
            File f = new File(path, AllConstants.NAME_FILE_DISH_IMAGE);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            return b;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }

    }

    private void returnToMainActivity()
    {
        Intent intent = new Intent(this, DishActivity.class);
        startActivity(intent);
        finish();
    }
}
