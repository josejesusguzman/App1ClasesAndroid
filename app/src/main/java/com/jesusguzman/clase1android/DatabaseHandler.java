package com.jesusguzman.clase1android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ContactosManager";
    private static final String TABLE_CONTACTOS_NAME = "Contactos";

    private static final String KEY_ID = "id";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_TELEFONO = "telefono";


    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String crearTabla = "CREATE TABLE " + TABLE_CONTACTOS_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NOMBRE + " TEXT, "
                + KEY_TELEFONO + " TEXT)";
        sqLiteDatabase.execSQL(crearTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTOS_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addContact(Contacto contacto) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(KEY_NOMBRE, contacto.getNombre());
        valores.put(KEY_TELEFONO, contacto.getTelefono());

        sqLiteDatabase.insert(TABLE_CONTACTOS_NAME, null, valores);
        sqLiteDatabase.close();
    }

    public List<Contacto> getAllContacts() {
        List<Contacto> contactoList = new ArrayList<Contacto>();

        String query =  "SELECT * FROM " + TABLE_CONTACTOS_NAME;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Contacto contacto = new Contacto();
                contacto.setId(Integer.parseInt(cursor.getString(0)));
                contacto.setNombre(cursor.getString(1));
                contacto.setTelefono(cursor.getString(2));

                contactoList.add(contacto);

            } while (cursor.moveToNext());
        }
        return  contactoList;
    }

}
