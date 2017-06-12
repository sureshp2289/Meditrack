package medicine.example.com.dailyalert;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import medicine.example.com.dailyalert.apputils.AppUtils;
import medicine.example.com.dailyalert.login.LoginActivity;

public class Splashscrren extends AppCompatActivity {
    public  SharedPreferences sharedpreparence;
    SharedPreferences.Editor spe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscrren);
        sharedpreparence=AppUtils.share(Splashscrren.this);
        spe=sharedpreparence.edit();
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent a= new Intent(Splashscrren.this, LoginActivity.class);
                startActivity(a);


                // close this activity
            }
        }, 3000);
        AppUtils.statusbar(Splashscrren.this, getResources().getColor(R.color.lightgrey));
    }


}
