package com.jesusguzman.clase1android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ScrollTestActivity extends AppCompatActivity {

    private static final String CANAL_NAME = "Canal de las estrellas";
    private static final String ID_CANAL = "2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tollbar_scroll_video);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbox, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.toolbox_go_activity) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_AppCompat_Light)
                    .setTitle("¿Qué quieres hacer?")
                    .setMessage("HOLIIIIIIIIIIIIIIII");
            builder.setPositiveButton("Aceptar", (dialogInterface, i) -> startActivity(new Intent(getApplicationContext(), ImageActivity.class)));
            builder.setNegativeButton("Cancelar", (dialogInterface, i) -> {
                crearCanalNotificacion();
                NotificationCompat.Builder builder1 = new NotificationCompat.Builder(getApplicationContext(), ID_CANAL)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("¿Por qué me funaste? :(")
                        .setContentText("DA CLIC AQUÍ - NO ES UN VIRUS")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                notificationManager.notify(1, builder1.build());

            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void crearCanalNotificacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importancia = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(ID_CANAL, CANAL_NAME, importancia);
            channel.setDescription(CANAL_NAME);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}