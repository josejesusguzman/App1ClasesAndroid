package com.jesusguzman.clase1android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ActivityDatos extends AppCompatActivity {

    private EditText edtTelefono, edtNombre;
    private ListView listViewData;
    private ArrayAdapter arrayAdapter;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tollbar_db);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtNombre = findViewById(R.id.edt_nombre);
        edtTelefono = findViewById(R.id.edt_telefono);
        listViewData = findViewById(R.id.listView);

        db = new DatabaseHandler(this);

        List<Contacto> listaContactos = db.getAllContacts();
        arrayAdapter = new ArrayAdapter(ActivityDatos.this, android.R.layout.simple_list_item_1, listaContactos);
        listViewData.setAdapter(arrayAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_form_datos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.toolbox_send_data) {
            if (edtNombre.getText().toString().isEmpty()) {
                edtNombre.setError("Este campo es obligatorio");
                return super.onOptionsItemSelected(item);
            }
            if (edtTelefono.getText().toString().isEmpty()) {
                edtTelefono.setError("Este campo es obligatorio");
                return super.onOptionsItemSelected(item);
            }

            Contacto contacto = new Contacto(
                    edtNombre.getText().toString(),
                    edtTelefono.getText().toString()
            );

            db.addContact(contacto);

            arrayAdapter.notifyDataSetChanged();
            listViewData.invalidateViews();
            listViewData.refreshDrawableState();

            edtNombre.setText("");
            edtTelefono.setText("");

            Toast.makeText(getApplicationContext(),
                    "Datos guardados con éxito",
                    Toast.LENGTH_LONG)
                    .show();

        }

        return super.onOptionsItemSelected(item);
    }
}