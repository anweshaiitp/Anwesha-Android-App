package info.anwesha2k18.iitp;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import info.anwesha2k18.iitp.activities.MainActivity;

public class FcmService extends FirebaseMessagingService {

    private static final String LOG_TAG = FcmService.class.getName();
    private int notificationId = 1;
    private String flashLightData;
    CameraManager cameraManager;
    String cameraId;

    @Override
    public void onCreate() {
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {

        }
        super.onCreate();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d("hellomessage", "FROM: ");

        if (remoteMessage.getData().size() > 0){
            /*message data will be used to create intents to particular activity on notification click*/
            Log.d("hellomessage", "Message Data: " + remoteMessage.getData());
            flashLightData = remoteMessage.getData().get("flashlight");
        }
        final boolean existFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);


        if (remoteMessage.getNotification() != null){
            Log.d(LOG_TAG, "Message Body: " + remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getBody());
        }

        if (flashLightData.equals("on") && existFlash){

            implementFlashlight();

        } if (flashLightData.equals("off") && existFlash){
            flashlightOff();
        }
    }

    private void sendNotification(String body){

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, getString(R.string.default_notification_channel_id))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Anwesha 2k19")
                .setContentText(body)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setVisibility(Notification.VISIBILITY_PUBLIC);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notificationBuilder.build());

    }

    private void flashlightOn() {

        try {
            cameraManager.setTorchMode(cameraId, true);

        } catch (CameraAccessException e) {

        }
    }

    private void flashlightOff() {
        try {
            cameraManager.setTorchMode(cameraId, false);
        } catch (CameraAccessException e) {

        }
    }

    private void implementFlashlight(){
        String myString = "0101010101";
        long blinkDelay = 100; //Delay in ms
        for (int i = 0; i < myString.length(); i++) {
            if (i == myString.length() - 1)
                i = 0;
            if (myString.charAt(i) == '0') {
                flashlightOn();
            } else {
                flashlightOff();
            }
            try {
                Thread.sleep(blinkDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
