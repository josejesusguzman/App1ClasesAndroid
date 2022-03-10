package com.jesusguzman.clase1android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.RemoteInput;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private EditText edtTextoCapturado;
    private TextView txtMuestraTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTextoCapturado = (EditText) findViewById(R.id.edt_mi_texto);
        txtMuestraTexto = (TextView) findViewById(R.id.txt_muestra_texto);
        Button btnAccionTurbo = (Button) findViewById(R.id.btn_accion_turbo);
        Button btnIrActivity = (Button) findViewById(R.id.btn_ir_otra_activity);
        Button btnActivityDatos = (Button) findViewById(R.id.btn_ir_activity_datos);

        btnAccionTurbo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarTexto();
            }
        });

        btnIrActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ScrollTestActivity.class));
            }
        });

        btnActivityDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActivityDatos.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getMessageText(new Intent(this, MainActivity.class));
    }

    private void mostrarTexto() {
        String texto = edtTextoCapturado.getText().toString();
        if (texto.equals("")){
            Toast.makeText(getApplicationContext(),
                    "¿Me quieres ver la cara de estupida?   ",
                    Toast.LENGTH_SHORT)
                    .show();
        } else {
            txtMuestraTexto.setText(texto);
            Snackbar snackbar = Snackbar.make(findViewById(R.id.ctl_parent),
                    texto, Snackbar.LENGTH_LONG);
            snackbar.setAction("Wey Contexto", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CAMERA}, 7);
                    }
                }
            });
            snackbar.show();
        }
    }

    private void getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if(remoteInput != null) {
            Toast.makeText(getApplicationContext(),
                    remoteInput.getCharSequence(ImageActivity.KEY_TEXT),
                    Toast.LENGTH_LONG)
                    .show();
        } else {
            Toast.makeText(getApplicationContext(),
                    "Eto lo hago pa diveltilme",
                    Toast.LENGTH_SHORT)
                    .show();
        }

    }
}