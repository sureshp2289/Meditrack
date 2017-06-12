package medicine.example.com.dailyalert.broadcastreciever;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

import medicine.example.com.dailyalert.R;

public class MyReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
       Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();
        /*PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"Wakelock");
        wl.acquire();

        // Put here YOUR code.
        Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show(); // For example

        wl.release();*/


        String medicine=intent.getStringExtra("medicine");
        String dose=intent.getStringExtra("dose");
        String time=intent.getStringExtra("time");
        Uri sound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Please take the medicine")
                .setContentText(medicine+"  "+dose+" "+time)
                .setAutoCancel(true)
                .setSound(sound);

        Ringtone r = RingtoneManager.getRingtone(context, sound);
        r.play();

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);



        notificationManager.notify(0,notificationBuilder.build());
    }


}
