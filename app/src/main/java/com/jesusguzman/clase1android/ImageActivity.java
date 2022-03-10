package com.jesusguzman.clase1android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

public class ImageActivity extends AppCompatActivity {

    private static final String TITLE_NOTIFICATIONS = "Mensaje de Cheems";
    public static final String KEY_TEXT = "key_text";

    // Para la tarea
    public ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tollbar_scroll_image);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button notificacionStandar = findViewById(R.id.btn_notificacion1);

        // Para la tarea
        mainLayout = findViewById(R.id.main_layout);

        notificacionStandar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                notificationManager.notify(1,
                        constructorNotificaciones(
                                TITLE_NOTIFICATIONS,
                                "NO SOY UN VIMRUS - TEMNGO AMNSIEDAD")
                                .build()
                );
            }
        });

        Button notificacionAccionable = findViewById(R.id.btn_notificacion2);
        notificacionAccionable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ScrollTestActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(
                        getApplicationContext(),
                        0,
                        intent,
                        0);

                NotificationCompat.Builder builder = constructorNotificaciones(
                        TITLE_NOTIFICATIONS,
                        "DALE CLIC - NO SOY UN VIRUZZZ x"
                ).setContentIntent(pendingIntent)
                        .setAutoCancel(true);

                NotificationManagerCompat notificationManager =
                        NotificationManagerCompat.from(getApplicationContext());
                notificationManager.notify(1, builder.build());
            }
        });

        Button notificacionConBoton = findViewById(R.id.btn_notificacion3);
        notificacionConBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ScrollTestActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(
                        getApplicationContext(),
                        0,
                        intent,
                        0);

                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent2 = PendingIntent.getActivity(
                        getApplicationContext(),
                        0,
                        intent2,
                        0);

                NotificationCompat.Builder builder = constructorNotificaciones(
                        TITLE_NOTIFICATIONS,
                        "Picale al botón para obtener los packs XD"
                ).setContentIntent(pendingIntent)
                        .addAction(R.drawable.ic_baseline_image_24, "Obtener packs", pendingIntent2)
                        .setAutoCancel(true);



                NotificationManagerCompat notificationManager =
                        NotificationManagerCompat.from(getApplicationContext());
                notificationManager.notify(1, builder.build());
            }
        });

        Button notificacionConRespuesta = findViewById(R.id.btn_notificacion4);
        notificacionConRespuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Para la tarea
                mainLayout.setBackgroundResource(R.color.black);

                String replyLabel = getResources().getString(R.string.reply_label);
                RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT)
                        .setLabel(replyLabel)
                        .build();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(
                        getApplicationContext(),
                        0,
                        intent,
                        0);

                NotificationCompat.Action action = new NotificationCompat.Action.Builder(
                        R.drawable.ic_baseline_send_24,
                        getResources().getString(R.string.reply_label), pendingIntent
                ).addRemoteInput(remoteInput).build();


                NotificationCompat.Builder builder = constructorNotificaciones(
                        TITLE_NOTIFICATIONS,
                        "Picale al botón para obtener los packs XD"
                ).addAction(action).setAutoCancel(true);


                NotificationManagerCompat notificationManager =
                        NotificationManagerCompat.from(getApplicationContext());
                notificationManager.notify(1, builder.build());

            }
        });

        Button notificacionConProgress = findViewById(R.id.btn_notificacion5);
        notificacionConProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManagerCompat notificationManager =
                        NotificationManagerCompat.from(getApplicationContext());
                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        getApplicationContext(),
                        ScrollTestActivity.ID_CANAL
                ).setContentTitle("Descargando malware")
                        .setContentText("Ya te cayo Hanonimux")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);

                int PROGRESS_MAX = 100;
                int PROGRESS_CURRENT = 50;

                builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
                notificationManager.notify(1, builder.build());

            }
        });

    }

    public NotificationCompat.Builder constructorNotificaciones(String Title, String mensaje) {
        return new NotificationCompat.Builder(getApplicationContext(), ScrollTestActivity.ID_CANAL)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(Title)
                .setContentText(mensaje)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }
}