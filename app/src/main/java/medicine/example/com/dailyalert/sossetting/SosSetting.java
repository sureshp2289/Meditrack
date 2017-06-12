package medicine.example.com.dailyalert.sossetting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import medicine.example.com.dailyalert.R;
import medicine.example.com.dailyalert.Splashscrren;
import medicine.example.com.dailyalert.addnewmedicine.AddNewMedicine;
import medicine.example.com.dailyalert.apputils.AppUtils;
import medicine.example.com.dailyalert.login.LoginActivity;
import medicine.example.com.dailyalert.sqlite.DatabaseHelper;
import medicine.example.com.dailyalert.tablayout.Tablayout;

public class SosSetting extends AppCompatActivity implements View.OnClickListener{
EditText et_sosname,et_sosnumber;
    Button btn_sossubmit;
    SharedPreferences sharedpreparence;
    SharedPreferences.Editor spe;
    ArrayList<HashMap<String,String>>userlist;
    DatabaseHelper databaseHelper;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos_setting);
        AppUtils.statusbar(SosSetting.this,getResources().getColor(R.color.lightgrey));

        Intilazewidgets();

    }

    private void Intilazewidgets() {
        sharedpreparence=AppUtils.share(SosSetting.this);
        spe = sharedpreparence.edit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        et_sosname=(EditText)findViewById(R.id.et_sosname);
        et_sosnumber=(EditText)findViewById(R.id.et_sosnumber);
        btn_sossubmit=(Button)findViewById(R.id.btn_sossubmit);
        int color = AppUtils.adjustAlpha(getResources().getColor(R.color.lightgrey), 0.8f);
        toolbar.setBackgroundColor(color);
        databaseHelper= new DatabaseHelper(this);
        btn_sossubmit.setOnClickListener(this);

            et_sosnumber.setText(sharedpreparence.getString("sosnumber",null));
            et_sosname.setText(sharedpreparence.getString("sosname",null));


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_sossubmit:
                if(et_sosname.getText().toString().trim().length()>0&&et_sosnumber.getText().toString().trim().length()>0)
                {
                    if(et_sosnumber.getText().toString().trim().length()==10)
                    {
                        String name=et_sosname.getText().toString().trim();
                        String number=et_sosnumber.getText().toString().trim();
                        String uid=sharedpreparence.getString("uid",null);
                      long i=databaseHelper.UpdateSosData(name,number,uid);
                        if(i==-1)
                        {
                            Toast.makeText(SosSetting.this,"Sos details is not updated",Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(SosSetting.this,"Sos details is  updated",Toast.LENGTH_SHORT).show();

                            spe.putString("sosname",name);
                            spe.putString("sosnumber",number);
                            spe.commit();
                            Intent a= new Intent(SosSetting.this,Tablayout.class);
                            a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(a);                        }

                    }
                    else{
                        Toast.makeText(SosSetting.this,"enter the valid phone number",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(SosSetting.this,"all the fields are madatory",Toast.LENGTH_SHORT).show();
                }
                break;
            default:break;
        }
    }
}
