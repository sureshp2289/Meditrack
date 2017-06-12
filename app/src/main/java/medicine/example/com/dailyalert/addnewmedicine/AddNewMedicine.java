package medicine.example.com.dailyalert.addnewmedicine;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import medicine.example.com.dailyalert.R;
import medicine.example.com.dailyalert.Splashscrren;
import medicine.example.com.dailyalert.apputils.AppUtils;
import medicine.example.com.dailyalert.broadcastreciever.MyReceiver;
import medicine.example.com.dailyalert.sqlite.DatabaseHelper;
import medicine.example.com.dailyalert.tablayout.Tablayout;

public class AddNewMedicine extends AppCompatActivity implements View.OnClickListener{
TextView tv_title,tv_title2;
    Button btn_add;
    EditText et_mname,et_dose,et_quantity,et_nodoseperday,et_remainder1,et_remainder2,et_dosepurchased;
    ListPopupWindow dosefrequecny,doseperda;
    String[] dose={"Daily","Weekly"};
    String []doseperday={"1","2"};
    private int  mHour, mMinute;
    Calendar c;
    String str_mid;
    static String str_mname,str_dose,str_quantity,str_perday,str_remainder1,str_remainder2,str_purchaesd,str_mid1;
    SharedPreferences sharedpreparence;
    SharedPreferences.Editor spe;
    TimePickerDialog timePickerDialog;
    TextInputLayout til_remainder2;
    PendingIntent mEverydayPendingIntent;
    DatabaseHelper databaseHelper;
    Calendar calendarr1,calenderr2;
    public AddNewMedicine()
    {

    }
    public AddNewMedicine(String mname,String dose,String quantity,String doseperday,String remainder1,String remaindaer2,String dosepurchsed,String mid)
    {
       str_mname=mname;
        str_dose=dose;
        str_quantity=quantity;
        str_perday=doseperday;
        str_remainder1=remainder1;
        str_remainder2=remaindaer2;
        str_purchaesd=dosepurchsed;
        str_mid1=mid;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_medicine);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        int color = AppUtils.adjustAlpha(getResources().getColor(R.color.lightgrey), 0.8f);
        toolbar.setBackgroundColor(color);
        AppUtils.statusbar(AddNewMedicine.this,getResources().getColor(R.color.lightgrey));

        InitialzeWidegets();
    }

    private void InitialzeWidegets() {

        sharedpreparence=AppUtils.share(AddNewMedicine.this);

        tv_title=(TextView)findViewById(R.id.tv_title);
        tv_title2=(TextView)findViewById(R.id.tv_title2);
        btn_add=(Button)findViewById(R.id.btn_add);
        et_mname=(EditText) findViewById(R.id.et_mname);
        et_dose=(EditText) findViewById(R.id.et_dose);
        et_quantity=(EditText) findViewById(R.id.et_quantity);
        et_nodoseperday=(EditText) findViewById(R.id.et_noofdoseperday);
        et_remainder1=(EditText) findViewById(R.id.et_remainder1);
        et_remainder2=(EditText) findViewById(R.id.et_remainder2);
        et_dosepurchased=(EditText) findViewById(R.id.et_noofdosepurchased);
        til_remainder2=(TextInputLayout)findViewById(R.id.til_remainder2);
if(str_perday.equalsIgnoreCase("2")&&str_perday.length()>0)
{
    til_remainder2.setVisibility(View.VISIBLE);
    btn_add.setText("Update");
    tv_title.setText("Update the medicine");
    tv_title2.setText("Update the medicine");
}
 if(str_mname.length()>0&&str_perday.equalsIgnoreCase("1")){

    til_remainder2.setVisibility(View.GONE);
    btn_add.setText("Update");
    tv_title.setText("Update the medicine");
    tv_title2.setText("Update the medicine");

}
 if(str_remainder1.length()>0)
{
    DateFormat sdf = new SimpleDateFormat("HH:mm"); // or "hh:mm" for 12 hour format
    Date date = null;
    try {
        date = sdf.parse(str_remainder1);
    } catch (ParseException e) {
        e.printStackTrace();
    }


    calendarr1=Calendar.getInstance();
    if(str_dose.equalsIgnoreCase("Weekly"))
    {
        calendarr1.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
    }
    calendarr1.set(Calendar.HOUR_OF_DAY, date.getHours());
    calendarr1.set(Calendar.MINUTE, date.getMinutes());
}
 if(str_remainder2.length()>0)
{
    DateFormat sdf = new SimpleDateFormat("HH:mm"); // or "hh:mm" for 12 hour format
    Date date = null;
    try {
        date = sdf.parse(str_remainder2);
    } catch (ParseException e) {
        e.printStackTrace();
    }


    calenderr2=Calendar.getInstance();
    if(str_dose.equalsIgnoreCase("Weekly"))
    {
        calenderr2.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
    }
    calenderr2.set(Calendar.HOUR_OF_DAY, date.getHours());
    calenderr2.set(Calendar.MINUTE, date.getMinutes());
}

         et_mname.setText(str_mname);
        et_dose.setText(str_dose);
        et_quantity.setText(str_quantity);
        et_nodoseperday.setText(str_perday);
        et_remainder1.setText(str_remainder1);
        et_remainder2.setText(str_remainder2);
        et_dosepurchased.setText(str_purchaesd);
databaseHelper=new DatabaseHelper(this);

        btn_add.setOnClickListener(this);
        et_dose.setOnClickListener(this);
        et_nodoseperday.setOnClickListener(this);
        et_remainder1.setOnClickListener(this);
        et_remainder2.setOnClickListener(this);


        dosefrequecny = new ListPopupWindow(this);
        dosefrequecny.setAdapter(new ArrayAdapter<String>(AddNewMedicine.this, android.R.layout.simple_list_item_1, dose));
        dosefrequecny.setWidth(ListPopupWindow.WRAP_CONTENT);
        dosefrequecny.setHeight(ListPopupWindow.WRAP_CONTENT);
        dosefrequecny.setDropDownGravity(Gravity.CENTER);
        dosefrequecny.setVerticalOffset(2);

        doseperda = new ListPopupWindow(this);
        doseperda.setAdapter(new ArrayAdapter<String>(AddNewMedicine.this, android.R.layout.simple_list_item_1, doseperday));
        doseperda.setWidth(ListPopupWindow.WRAP_CONTENT);
        doseperda.setHeight(ListPopupWindow.WRAP_CONTENT);
        doseperda.setDropDownGravity(Gravity.CENTER);
        doseperda.setVerticalOffset(2);

        c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        doseperda.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                                  @Override
                                                  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                      doseperda.dismiss();
                                                      et_nodoseperday.setText(doseperday[position]);
                                                      if(doseperday[position].equalsIgnoreCase("2"))
                                                      {
                                                          til_remainder2.setVisibility(View.VISIBLE);

                                                      }
                                                      else {
                                                          til_remainder2.setVisibility(View.GONE);
                                                          et_remainder2.setText("");

                                                      }
                                                  }
                                              }
        );
        dosefrequecny.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                             @Override
                                             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                 dosefrequecny.dismiss();
                                                 et_dose.setText(dose[position]);

                                             }
                                         }
        );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_add:
                if(et_mname.getText().toString().trim().length()>0&&et_dose.getText().toString().trim().length()>0&&et_nodoseperday.getText().toString().trim().length()>0
                &&et_quantity.getText().toString().trim().length()>0&&et_remainder1.getText().toString().trim().length()>0&&et_dosepurchased.getText().toString().trim().length()>0)
                {
                    if(btn_add.getText().toString().equalsIgnoreCase("Add"))
                    {
                        int i = databaseHelper.CheckMedicineisExist(et_mname.getText().toString().trim());
                        if (i > 0) {
                            Toast.makeText(AddNewMedicine.this, "Medicine is already exist", Toast.LENGTH_SHORT).show();
                        } else {

                            str_mid = "med" + AppUtils.Randomnumber();
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("mname", et_mname.getText().toString().trim()); // Contact Name
                            map.put("dose", et_dose.getText().toString().trim());
                            map.put("quantity", et_quantity.getText().toString().trim());
                            map.put("noofdoseperday", et_nodoseperday.getText().toString().trim());
                            map.put("reminder1", et_remainder1.getText().toString().trim());
                            map.put("reminder2", et_remainder2.getText().toString().trim());
                            map.put("noofmedicine", et_dosepurchased.getText().toString().trim());
                            map.put("mid", str_mid);
                            map.put("userid", sharedpreparence.getString("uid", null));

                            long l = databaseHelper.InsertMedicine(map);
                            if (l == -1) {
                                Toast.makeText(AddNewMedicine.this, "Medicine is not added", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(AddNewMedicine.this, "Medicine is added", Toast.LENGTH_SHORT).show();
                                setRepeatedNotification(calendarr1);
                                if(et_remainder2.getText().toString().length()>0)
                                {
                                    setRepeatedNotification1(calenderr2);
                                }
                                Intent a= new Intent(AddNewMedicine.this,Tablayout.class);
                                a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(a);                            }
                        }
                    }
                    else {
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("mname", et_mname.getText().toString().trim()); // Contact Name
                        map.put("dose", et_dose.getText().toString().trim());
                        map.put("quantity", et_quantity.getText().toString().trim());
                        map.put("noofdoseperday", et_nodoseperday.getText().toString().trim());
                        map.put("reminder1", et_remainder1.getText().toString().trim());
                        map.put("reminder2", et_remainder2.getText().toString().trim());
                        map.put("noofmedicine", et_dosepurchased.getText().toString().trim());
                        map.put("mid", str_mid1);
                        long l = databaseHelper.UpdateMedicineDetails(map);
                        if (l == -1) {
                            Toast.makeText(AddNewMedicine.this, "Medicine is not updated", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(AddNewMedicine.this, "Medicine is updated", Toast.LENGTH_SHORT).show();
                            setRepeatedNotification(calendarr1);
                            if(et_remainder2.getText().toString().length()>0)
                            {
                                setRepeatedNotification1(calenderr2);
                            }
                            Intent a= new Intent(AddNewMedicine.this,Tablayout.class);
                            a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(a);
                        }
                    }

                }
                else {
                    Toast.makeText(AddNewMedicine.this,"all the fields are madatory",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.et_dose:
                dosefrequecny.setAnchorView(view);
                dosefrequecny.show();
                break;
            case R.id.et_noofdoseperday:
                doseperda.setAnchorView(view);
                doseperda.show();
                break;
            case R.id.et_remainder1:


                // Launch Time Picker Dialog
                timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                calendarr1=Calendar.getInstance();
                                if(et_dose.getText().toString().trim().equalsIgnoreCase("Weekly"))
                                {
                                 calendarr1.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
                                }
                                calendarr1.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendarr1.set(Calendar.MINUTE, minute);
                                SimpleDateFormat hrmn = new SimpleDateFormat("h:mm a");
                                et_remainder1.setText(hrmn.format(calendarr1.getTime()));
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

                break;
            case R.id.et_remainder2:
                // Launch Time Picker Dialog
                timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                calenderr2=Calendar.getInstance();
                                if(et_dose.getText().toString().trim().equalsIgnoreCase("Weekly"))
                                {
                                    calendarr1.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
                                }
                                calenderr2.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calenderr2.set(Calendar.MINUTE, minute);
                                SimpleDateFormat hrmn = new SimpleDateFormat("h:mm a");
                                et_remainder2.setText(hrmn.format(calenderr2.getTime()));
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
                break;
            default:break;
        }
    }
    private void setRepeatedNotification(Calendar calendar) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent alarmIntent = new Intent(AddNewMedicine.this, MyReceiver.class);
       alarmIntent.putExtra("medicine",et_mname.getText().toString().trim());
        alarmIntent.putExtra("dose",et_dose.getText().toString().trim());
        alarmIntent.putExtra("time",et_remainder1.getText().toString().trim());
        //Log.d("setRepeatedNotification", "ID:" + ID);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(AddNewMedicine.this, 0, alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);


        Calendar now = Calendar.getInstance();
      //  calendar.set(Calendar.SECOND, ss);

        //check whether the time is earlier than current time. If so, set it to tomorrow. Otherwise, all alarms for earlier time will fire

        if(calendar.before(now)){
            calendar.add(Calendar.DATE, 1);
        }

       // mEverydayPendingIntent = pendingIntent;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24*60*60*1000, pendingIntent);



    }
    private void setRepeatedNotification1(Calendar calendar) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent alarmIntent = new Intent(AddNewMedicine.this, MyReceiver.class);
        alarmIntent.putExtra("medicine",et_mname.getText().toString().trim());
        alarmIntent.putExtra("dose",et_dose.getText().toString().trim());
        alarmIntent.putExtra("time",et_remainder2.getText().toString().trim());
        //Log.d("setRepeatedNotification", "ID:" + ID);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(AddNewMedicine.this, 1, alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);


        Calendar now = Calendar.getInstance();
        //  calendar.set(Calendar.SECOND, ss);

        //check whether the time is earlier than current time. If so, set it to tomorrow. Otherwise, all alarms for earlier time will fire

        if(calendar.before(now)){
            calendar.add(Calendar.DATE, 1);
        }

       // mEverydayPendingIntent = pendingIntent;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24*60*60*1000, pendingIntent);



    }
}
