package com.example.adopciondeanimales.Channel;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import com.example.adopciondeanimales.R;


public class NotificationHelp extends ContextWrapper {
    private static  final String CHANNEL_ID ="com.example.adoptame";
    private static  final String CHANNEL_NAME ="";

    private NotificationManager _notificationManager;

    public NotificationHelp(Context base) {
        super(base);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // Se hace uso del metodo! createChannel
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel(){
        NotificationChannel _notificationChannel = new NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
        );

        _notificationChannel.enableLights(true);
        _notificationChannel.enableVibration(true);
        _notificationChannel.setLightColor(Color.GREEN);
        _notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getNotificationManager().createNotificationChannel(_notificationChannel);
    }

    public NotificationManager getNotificationManager(){
        if(_notificationManager == null){
            _notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return _notificationManager;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getNotification(String title, String body, PendingIntent intent, Uri soundUri){
        return new Notification.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(intent)
                .setSound(soundUri)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true);
    }

    public NotificationCompat.Builder getNotificationOldAPI(String title, String body, PendingIntent intent, Uri soundUri){
        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(intent)
                .setSound(soundUri)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true);
    }

}
