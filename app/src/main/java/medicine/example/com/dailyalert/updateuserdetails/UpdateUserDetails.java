package medicine.example.com.dailyalert.updateuserdetails;

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

import medicine.example.com.dailyalert.R;
import medicine.example.com.dailyalert.Splashscrren;
import medicine.example.com.dailyalert.apputils.AppUtils;
import medicine.example.com.dailyalert.sossetting.SosSetting;
import medicine.example.com.dailyalert.sqlite.DatabaseHelper;
import medicine.example.com.dailyalert.tablayout.Tablayout;

public class UpdateUserDetails extends AppCompatActivity implements View.OnClickListener {
    EditText et_name,et_age;
    Button btn_update;
    DatabaseHelper databaseHelper;
    SharedPreferences sharedpreparence;
    SharedPreferences.Editor spe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        int color = AppUtils.adjustAlpha(getResources().getColor(R.color.lightgrey), 0.8f);
        toolbar.setBackgroundColor(color);
        AppUtils.statusbar(UpdateUserDetails.this,getResources().getColor(R.color.lightgrey));

        IntializeWidgets();


    }
    private void IntializeWidgets() {
        sharedpreparence=AppUtils.share(UpdateUserDetails.this);

        spe = sharedpreparence.edit();
        et_name=(EditText)findViewById(R.id.et_name);
        et_age=(EditText)findViewById(R.id.et_age);
        btn_update=(Button)findViewById(R.id.btn_update);
        btn_update.setOnClickListener(this);
        databaseHelper=new DatabaseHelper(this);
        et_name.setText(sharedpreparence.getString("name",null));
        et_age.setText(sharedpreparence.getString("age",null));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_update:
                if(et_name.getText().toString().trim().length()>0&&et_age.getText().toString().trim().length()>0)
                {
                    String name=et_name.getText().toString().trim();
                    String age=et_age.getText().toString().trim();
                    String uid=sharedpreparence.getString("uid",null);
                    long i=databaseHelper.EditUsers(name,age,uid);
                    if(i==-1)
                    {
                        Toast.makeText(UpdateUserDetails.this,"user details is not updated",Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(UpdateUserDetails.this,"user details is  updated",Toast.LENGTH_SHORT).show();

                        spe.putString("name",name);
                        spe.putString("age",age);
                        spe.commit();
                        finish();
                    }

                }
                else {
                    Toast.makeText(UpdateUserDetails.this,"all fields are madatatory",Toast.LENGTH_SHORT).show();
                }
                break;
            default:break;
        }
    }
}
