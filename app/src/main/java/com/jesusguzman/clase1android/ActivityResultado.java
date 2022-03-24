package com.jesusguzman.clase1android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ActivityResultado extends AppCompatActivity {

    private String sabor = "";
    public static final String TOPPING = "TOPPING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        TextView textView = findViewById(R.id.textView3);
        Button button = findViewById(R.id.button2);

        //OBTENER EL DATO DEL ACTIVITY ANTERIOR
        Bundle bundle = getIntent().getExtras();
        if (!bundle.isEmpty() && bundle != null) {
            sabor = bundle.getString(ActivityFormulario.SABOR);
            textView.setText(sabor);
        }

        Intent intent = new Intent(getApplicationContext(), ActivityResultadoFinal.class);
        if (sabor.isEmpty())
            intent.putExtra(ActivityFormulario.SABOR, "Sin sabor");
        else
            intent.putExtra(ActivityFormulario.SABOR, sabor);

        button.setOnClickListener(view -> {
            intent.putExtra(TOPPING, "Chispas de chocolate");
            startActivity(intent);
        });

    }
}