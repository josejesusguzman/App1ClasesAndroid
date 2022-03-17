package com.jesusguzman.clase1android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// TAREA DEL FORNAIS
public class TareaPseudocodigo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea_pseudocodigo);

        // 0. COSAS DE ANDROID
        EditText edad = findViewById(R.id.editTextNumberDecimal);
        EditText peso = findViewById(R.id.editTextNumberDecimal2);
        TextView txtEdad = findViewById(R.id.textView);
        TextView txtPeso = findViewById(R.id.textView2);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(view -> {
            // 1. Recibir del usuario el dato de edad y guardarlo
            String edadTexto = edad.getText().toString();

            // 2. Recibir del usuario el dato de peso y guardarlo
            String pesoTexto = peso.getText().toString();

            // 3. Imprimir el dato de edad guardado
            txtEdad.setText(edadTexto);

            // 4. Imprimir el dato de peso guardado
            txtPeso.setText(pesoTexto);
        });

    }
}