package com.jesusguzman.clase1android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ActivityDatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        DatabaseHandler db = new DatabaseHandler(this);

        db.addContact(new Contacto("Jesus", "01800555555"));
        db.addContact(new Contacto("Mari", "01800555555"));

        List<Contacto> listaContactos = db.getAllContacts();

        for (Contacto contacto : listaContactos) {
            String dato = "ID: " + contacto.getId() + " | Nombre: " + contacto.getNombre() + " | Telefono: " + contacto.getTelefono();
            System.out.println(dato);
        }

    }
}