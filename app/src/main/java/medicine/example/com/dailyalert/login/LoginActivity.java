package medicine.example.com.dailyalert.login;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import medicine.example.com.dailyalert.R;
import medicine.example.com.dailyalert.Splashscrren;
import medicine.example.com.dailyalert.apputils.AppUtils;
import medicine.example.com.dailyalert.sossetting.SosSetting;
import medicine.example.com.dailyalert.sqlite.DatabaseHelper;
import medicine.example.com.dailyalert.tablayout.Tablayout;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
EditText et_name,et_age;
    Button btn_signin;
    String  str_uid;
    DatabaseHelper databaseHelper;
    HashMap<String,String> map_header;
    public static final int MY_PERMISSIONS_REQUEST_CALL_PHONE=1;
    ArrayList<HashMap<String,String>> userlist;
    SharedPreferences sharedpreparence;
    SharedPreferences.Editor spe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
 IntializeWidgets();
        if (Build.VERSION.SDK_INT >= 23) {
            int read = ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.CALL_PHONE);
            if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                // Check Permissions Now
                // Callback onRequestPermissionsResult interceptado na Activity Stylist_Gallery_Display
                ActivityCompat.requestPermissions(LoginActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE,Manifest.permission.RECEIVE_BOOT_COMPLETED,Manifest.permission.WAKE_LOCK},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
            } else {

            }

        } else {


        }
    }

    private void IntializeWidgets() {
        sharedpreparence=AppUtils.share(LoginActivity.this);
        spe = sharedpreparence.edit();
        et_name=(EditText)findViewById(R.id.et_name);
        et_age=(EditText)findViewById(R.id.et_age);
        btn_signin=(Button)findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(this);
        databaseHelper=new DatabaseHelper(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_signin:
                if(et_name.getText().toString().trim().length()>0&&et_age.getText().toString().trim().length()>0)
                {
                    str_uid="user"+AppUtils.Randomnumber();
                    HashMap<String,String>map= new HashMap<String, String>();
                    map.put("name",et_name.getText().toString().trim());
                    map.put("age",et_age.getText().toString().trim());
                    map.put("sosname","");
                    map.put("sosnumber","");
                    map.put("uid",str_uid);
                    userlist=databaseHelper.getUserdetails(et_name.getText().toString().trim());
                    if(userlist.size()>0)
                    {
                        Toast.makeText(LoginActivity.this,"Login sucessful",Toast.LENGTH_SHORT).show();
                         map_header=userlist.get(0);
                        spe.putBoolean("status",true);
                        spe.putString("uid", map_header.get("uid"));
                        spe.putString("name",map_header.get("name"));
                        spe.putString("age",map_header.get("age"));
                        spe.putString("sosname",map_header.get("sosname"));
                        spe.putString("sosnumber",map_header.get("sosnumber"));
                        spe.commit();
                        if(map_header.get("sosname").length()>0&&map_header.get("sosnumber").length()>0) {

                            Intent a = new Intent(LoginActivity.this, Tablayout.class);
                            startActivity(a);
                        }
                        else {
                            Intent a = new Intent(LoginActivity.this, SosSetting.class);
                            startActivity(a);
                        }
                    }
                    else {
                        long l= databaseHelper.InsertData(map);
                        if(l==-1)
                        {
                            Toast.makeText(LoginActivity.this,"Data is not saved",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            userlist=databaseHelper.getUserdetails(et_name.getText().toString().trim());
                            if(userlist.size()>0)
                            {
                                Toast.makeText(LoginActivity.this, "Register sucessful", Toast.LENGTH_SHORT).show();
                                map_header=userlist.get(0);
                                spe.putBoolean("status",true);
                                spe.putString("uid", map_header.get("uid"));
                                spe.putString("name",map_header.get("name"));
                                spe.putString("age",map_header.get("age"));
                                spe.putString("sosname",map_header.get("sosname"));
                                spe.putString("sosnumber",map_header.get("sosnumber"));
                                spe.commit();
                                if(map_header.get("sosname").length()>0&&map_header.get("sosnumber").length()>0) {

                                    Intent a = new Intent(LoginActivity.this, Tablayout.class);
                                    startActivity(a);
                                }
                                else {
                                    Intent a = new Intent(LoginActivity.this, SosSetting.class);
                                    startActivity(a);
                                }
                            }
                        }
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this,"all fields are madatory",Toast.LENGTH_SHORT).show();
                }
                break;
            default:break;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {




                }
                else {

                }
                // other 'case' lines to check for other
                // permissions this app might request
            }
            break;
        }
    }
}
