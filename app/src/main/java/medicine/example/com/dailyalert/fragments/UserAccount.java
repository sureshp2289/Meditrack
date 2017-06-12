package medicine.example.com.dailyalert.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import medicine.example.com.dailyalert.R;
import medicine.example.com.dailyalert.Splashscrren;
import medicine.example.com.dailyalert.addnewmedicine.AddNewMedicine;
import medicine.example.com.dailyalert.apputils.AppUtils;
import medicine.example.com.dailyalert.sossetting.SosSetting;
import medicine.example.com.dailyalert.updateuserdetails.UpdateUserDetails;


public class UserAccount extends Fragment implements View.OnClickListener{
View view;
    ImageView iv_settings;
    LinearLayout ll_settings;
    Button btn_updateuserdeatils,btn_sossettingpage,btn_addnewmedicine;
    SharedPreferences sharedpreparence;
    SharedPreferences.Editor spe;
    public UserAccount() {
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

            view = inflater.inflate(R.layout.fragment_user_account, container, false);


        Intialzewidgets(view);

        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event

    private void Intialzewidgets(View view) {
        sharedpreparence=AppUtils.share(getActivity());
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        iv_settings=(ImageView)view.findViewById(R.id.iv_settings);
        ll_settings=(LinearLayout) view.findViewById(R.id.ll_setting);
        btn_addnewmedicine=(Button) view.findViewById(R.id.btn_addnewmedicine);
        btn_sossettingpage=(Button) view.findViewById(R.id.btn_sossettingpage);
        btn_updateuserdeatils=(Button) view.findViewById(R.id.btn_updateuserprofile);

        iv_settings.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        ll_settings.setOnClickListener(this);
        btn_sossettingpage.setOnClickListener(this);
        btn_updateuserdeatils.setOnClickListener(this);
        btn_addnewmedicine.setOnClickListener(this);

        int color = AppUtils.adjustAlpha(getResources().getColor(R.color.lightgrey), 0.8f);
        toolbar.setBackgroundColor(color);
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
            case R.id.btn_addnewmedicine:
                new AddNewMedicine("","","","","","","","");
                Intent c=new Intent(getActivity(), AddNewMedicine.class);
                getActivity().startActivity(c);
                break;
            case R.id.btn_sossettingpage:
                Intent a=new Intent(getActivity(), SosSetting.class);
                getActivity().startActivity(a);
                break;
            case R.id.btn_updateuserprofile:
                Intent b=new Intent(getActivity(), UpdateUserDetails.class);
                getActivity().startActivity(b);

                break;
            default:break;
        }
    }

}
