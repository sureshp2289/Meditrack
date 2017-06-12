package medicine.example.com.dailyalert.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import medicine.example.com.dailyalert.R;
import medicine.example.com.dailyalert.Splashscrren;
import medicine.example.com.dailyalert.adapter.DailytakenMedicineAdapter;
import medicine.example.com.dailyalert.adapter.UsuallytakenMedicineAdapter;
import medicine.example.com.dailyalert.apputils.AppUtils;
import medicine.example.com.dailyalert.sqlite.DatabaseHelper;


public class DailytakenMedicine extends Fragment implements View.OnClickListener{
    View view;
    ImageView iv_settings;
    LinearLayout ll_settings;
    SharedPreferences sharedpreparence;
    SharedPreferences.Editor spe;
    RecyclerView rv_dailymedicineusuage;
    TextView tv_dailymedicineusuages;
    String str_day;
    DatabaseHelper databaseHelper;

    public DailytakenMedicine() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

            view = inflater.inflate(R.layout.fragment_dailytaken_medicine, container, false);

            Intialzewidgets(view);

        return view;
    }

    private void Intialzewidgets(View view) {
        sharedpreparence=AppUtils.share(getActivity());

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        iv_settings=(ImageView)view.findViewById(R.id.iv_settings);
        ll_settings=(LinearLayout) view.findViewById(R.id.ll_setting);
        tv_dailymedicineusuages=(TextView)view.findViewById(R.id.tv_dailymedices);
        rv_dailymedicineusuage=(RecyclerView)view.findViewById(R.id.rv_dailymedices);
        iv_settings.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        ll_settings.setOnClickListener(this);
        databaseHelper= new DatabaseHelper(getActivity());
        int color = AppUtils.adjustAlpha(getResources().getColor(R.color.lightgrey), 0.8f);
        toolbar.setBackgroundColor(color);
        String weekdays[] = new DateFormatSymbols(Locale.US).getWeekdays();
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        int dayofmonth= c.get(Calendar.MONTH);
        int year=c.get(Calendar.YEAR);
        int day=c.get(Calendar.DAY_OF_MONTH);
       String selectedday=weekdays[dayOfWeek];
        if(selectedday.equalsIgnoreCase("Sunday"))
        {
           str_day="Weekly";
        }else {
            str_day="Daily";
        }
        ArrayList<HashMap<String,String>> alist=databaseHelper.getDayMedicineDetalils(sharedpreparence.getString("uid",null),str_day);
        if(alist.size()>0)
        {
            rv_dailymedicineusuage.setVisibility(View.VISIBLE);
            tv_dailymedicineusuages.setVisibility(View.GONE);
            DailytakenMedicineAdapter adapter=new DailytakenMedicineAdapter(getActivity(),alist);
            rv_dailymedicineusuage.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity());
            rv_dailymedicineusuage.setLayoutManager(mLayoutManager2);
                  /*  rv_previous.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));*/
            rv_dailymedicineusuage.setItemAnimator(new DefaultItemAnimator());
            // rv_previous.getLayoutParams().height = rv_previous.getHeight() /2;

            rv_dailymedicineusuage.setAdapter(adapter);
        }
        else{
            rv_dailymedicineusuage.setVisibility(View.GONE);
            tv_dailymedicineusuages.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_setting:
                if(sharedpreparence.getString("sosnumber",null).length()>0)
                {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:0"+sharedpreparence.getString("sosnumber",null)));
                    startActivity(callIntent);
                }
                break;
            default:break;
        }
    }

}
