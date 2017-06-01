package com.tims.pretims;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;

/**
 * Created by mayocartad on 23.05.2017.
 */

public class MenuNavBar
{
    public static boolean checkItemSelected(Context context, int id)
    {
        if (id == R.id.login_menuItem)
        {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            return true;
        }
        else if (id == R.id.logout_menuItem)
        {
            //delete last token
            UserDataSource userDatabase = new UserDataSource(context);
            userDatabase.open();
            userDatabase.deleteLastToken();
            userDatabase.close();

            Intent intent = new Intent(context, context.getClass());
            context.startActivity(intent);
            return true;
        }
        else if (id == R.id.history_menuItem)
        {
            //
            return false;
        }
        else if (id == R.id.settings_menuItem)
        {
            //
            return false;
        }
        else if (id == android.R.id.home)
        {
            Intent intent = new Intent(context, DishActivity.class);
            context.startActivity(intent);
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void setConnectionVisibility(Menu menu, Context context)
    {
        UserDataSource dataSource = new UserDataSource(context);
        dataSource.open();
        String token = dataSource.getLastToken();
        dataSource.close();

        if(token != null)
        {
            menu.getItem(0).setVisible(false);
            menu.getItem(1).setVisible(true);
        }
        else
        {
            menu.getItem(0).setVisible(true);
            menu.getItem(1).setVisible(false);
        }
    }
}
