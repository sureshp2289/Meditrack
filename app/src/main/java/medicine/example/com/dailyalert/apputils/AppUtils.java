package medicine.example.com.dailyalert.apputils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.Random;

/**
 * Created by Start4me on 2/14/2017.
 */

public class AppUtils {


    static ProgressDialog progessDialog;
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;

    public static void showProgressDialog(Context context, String message) {

        if (context != null) {
            progessDialog = new ProgressDialog(context);
            progessDialog.setTitle("Medicine...");
            // progessDialog.setIcon(R.drawable.ic_launcher);
            progessDialog.setMessage("" + message);

            progessDialog.setCanceledOnTouchOutside(false);

            progessDialog.show();
        }
    }

    public static void statusbar(Context context, int color) {
        if (Build.VERSION.SDK_INT >= 21) {
            Activity activity = (Activity) context;
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
        }
    }


    public static int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    public static void AlertDialog(Context context, String message) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle("Medicine App");
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();


    }

    public static void hideProgressDialog() {


        if (progessDialog.isShowing()) {
            progessDialog.cancel();
            progessDialog.dismiss();

        } else {
            Log.d("UB Hair", "Progress Dialog is Null");
        }
    }
    public static String Randomnumber()
    {
        String str_uid="";
        Random diceRoller = new Random();
        for (int i = 0; i < 1; i++) {
            int roll = diceRoller.nextInt(1000000000) + 1;
            // System.out.println(roll);
          str_uid= String.valueOf(roll);
            // Toast.makeText(getActivity(),""+roll,Toast.LENGTH_SHORT).show();
        }
        return str_uid;
    }
    public static SharedPreferences share(Context context){
        SharedPreferences sharedPreferences;
        sharedPreferences=context.getSharedPreferences("loginstatus", context.MODE_PRIVATE);
        return sharedPreferences;
    }

}
