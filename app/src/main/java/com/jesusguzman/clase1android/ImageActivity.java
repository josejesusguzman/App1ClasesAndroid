package com.jesusguzman.clase1android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

public class ImageActivity extends AppCompatActivity {

    private static final String TITLE_NOTIFICATIONS = "Mensaje de Cheems";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tollbar_scroll_image);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button notificacionStandar = findViewById(R.id.btn_notificacion1);

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
    }

    public NotificationCompat.Builder constructorNotificaciones(String Title, String mensaje) {
        return new NotificationCompat.Builder(getApplicationContext(), ScrollTestActivity.ID_CANAL)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(Title)
                .setContentText(mensaje)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }
}