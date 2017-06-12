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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import medicine.example.com.dailyalert.R;
import medicine.example.com.dailyalert.Splashscrren;
import medicine.example.com.dailyalert.adapter.UsuallytakenMedicineAdapter;
import medicine.example.com.dailyalert.apputils.AppUtils;
import medicine.example.com.dailyalert.sqlite.DatabaseHelper;


public class UsuallytakenMedicine extends Fragment implements View.OnClickListener {
    View view;
    ImageView iv_settings;
    LinearLayout ll_settings,ll_search;
    TextView tv_usuallmedicine;
    RecyclerView rv_usuallymedice;
    SharedPreferences sharedpreparence;
    SharedPreferences.Editor spe;
    DatabaseHelper databaseHelper;
    public UsuallytakenMedicine() {
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

            view = inflater.inflate(R.layout.fragment_usuallytaken_medicine, container, false);
        Intialzewidgets(view);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    private void Intialzewidgets(View view) {
        sharedpreparence=AppUtils.share(getActivity());

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        iv_settings=(ImageView)view.findViewById(R.id.iv_settings);
        ll_settings=(LinearLayout) view.findViewById(R.id.ll_setting);
        ll_search=(LinearLayout)view.findViewById(R.id.ll_search);
        tv_usuallmedicine=(TextView)view.findViewById(R.id.tv_usuallymedices);
        rv_usuallymedice=(RecyclerView)view.findViewById(R.id.rv_usuallymedices);
        ll_settings.setOnClickListener(this);
        iv_settings.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        int color = AppUtils.adjustAlpha(getResources().getColor(R.color.lightgrey), 0.8f);
        toolbar.setBackgroundColor(color);
        databaseHelper= new DatabaseHelper(getActivity());
        ArrayList<HashMap<String,String>> al_medicine=databaseHelper.getMedicineDetalils(sharedpreparence.getString("uid",null));
        if(al_medicine.size()>0)
        {
            rv_usuallymedice.setVisibility(View.VISIBLE);
            tv_usuallmedicine.setVisibility(View.GONE);
            UsuallytakenMedicineAdapter adapter=new UsuallytakenMedicineAdapter(getActivity(),al_medicine);
            rv_usuallymedice.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity());
            rv_usuallymedice.setLayoutManager(mLayoutManager2);
                  /*  rv_previous.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));*/
            rv_usuallymedice.setItemAnimator(new DefaultItemAnimator());
            // rv_previous.getLayoutParams().height = rv_previous.getHeight() /2;

            rv_usuallymedice.setAdapter(adapter);
        }
        else{
            rv_usuallymedice.setVisibility(View.GONE);
            tv_usuallmedicine.setVisibility(View.VISIBLE);
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

