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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DetailDishActivity extends AppCompatActivity {

    private Dish dish = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dish);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //create the back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        displayValues();
    }

    /**
     * Take from http://stackoverflow.com/questions/10108774/how-to-implement-the-android-actionbar-back-button
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, DishActivity.class);
                startActivity(intent);
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

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
     * Take from http://stackoverflow.com/questions/17674634/saving-and-reading-bitmaps-images-from-internal-memory-in-android
     * @param path
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
}
