package medicine.example.com.dailyalert.tablayout;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.HashMap;

import medicine.example.com.dailyalert.R;
import medicine.example.com.dailyalert.apputils.AppUtils;
import medicine.example.com.dailyalert.fragments.DailytakenMedicine;
import medicine.example.com.dailyalert.fragments.UserAccount;
import medicine.example.com.dailyalert.fragments.UsuallytakenMedicine;

public class Tablayout  extends AppCompatActivity {
    public static FragmentTabHost tabHost;
    public TabWidget tabWidget;
    static ImageView tabimage;
    static TextView tabtext;
    HashMap<String,String> map_header;
    boolean error;
    String regid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        AppUtils.statusbar(Tablayout.this,getResources().getColor(R.color.lightgrey));

        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(),android.R.id.tabhost);




        tabHost.addTab(tabHost.newTabSpec("Daily taken").setIndicator(createTabView(Tablayout.this, getResources().getDrawable(R.drawable.medicine), "Daily taken", 0)),
                DailytakenMedicine.class,null);
        tabHost.addTab(tabHost.newTabSpec("Usally taken").setIndicator(createTabView(Tablayout.this, getResources().getDrawable(R.drawable.medicine), "Usally taken", 0)),
                UsuallytakenMedicine.class,null);
        tabHost.addTab(tabHost.newTabSpec("User settig").setIndicator(createTabView(Tablayout.this, getResources().getDrawable(R.drawable.users), "User account", 0)),
                UserAccount.class,null);
        tabHost.setCurrentTab(0);




        setTabColor(tabHost);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTabChanged(String arg0) {

                setTabColor(tabHost);
              /*  FragmentManager manager = getSupportFragmentManager();

                if(manager.getBackStackEntryCount() > 0)
                {
                    manager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }*/
            }
        });

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("message", "This is my message to be reloaded");
        super.onSaveInstanceState(outState);
    }
    private static View createTabView(final Context context, final Drawable image, final String text, int pos) {

        View tab_view = LayoutInflater.from(context).inflate(R.layout.tablayout, null);
        tabimage = (ImageView) tab_view.findViewById(R.id.tabs_image);
        tabimage.setImageDrawable(image);
        // drawableimage=image;

        tabtext = (TextView) tab_view.findViewById(R.id.tabs_tv);


        tabtext.setText(text);
        tabtext.setTextSize(9.25f);

        //txt_text.setGravity(Gravity.CENTER);
        return tab_view;
    }

    public void setTabColor(TabHost tabhost) {
        //  Toast.makeText(Tablayout.this,""+tabhost.getCurrentTab(),Toast.LENGTH_SHORT).show();
        for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {
            int color= AppUtils.adjustAlpha(getResources().getColor(R.color.lightgrey),0.8f);
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(color);
            tabtext = (TextView) tabhost.getTabWidget().getChildAt(i).findViewById(R.id.tabs_tv);//Unselected Tabs
            tabtext.setTextColor(getResources().getColor(R.color.white));
            tabtext.setTextSize(TypedValue.COMPLEX_UNIT_DIP,10f);

            tabimage = (ImageView) tabhost.getTabWidget().getChildAt(i).findViewById(R.id.tabs_image);
            tabimage.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);//Unselected Tabs
            // img.setImageDrawable(drawableimage);
            //unselected
            // }
        }
        // tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#B37D4E")); // selected
        if (tabhost.getCurrentTab() == 0) {
            tabtext = (TextView) tabhost.getCurrentTabView().findViewById(R.id.tabs_tv);
            //Unselected Tabs

            tabtext.setTextColor(getResources().getColor(R.color.black));
            tabimage = (ImageView) tabhost.getCurrentTabView().findViewById(R.id.tabs_image);
            tabimage.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);

        } else {
            tabtext = (TextView) tabhost.getCurrentTabView().findViewById(R.id.tabs_tv);
            //Unselected Tabs
            tabtext.setTextColor(getResources().getColor(R.color.black));
            tabimage = (ImageView) tabhost.getCurrentTabView().findViewById(R.id.tabs_image);
            tabimage.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        }


    }






}
