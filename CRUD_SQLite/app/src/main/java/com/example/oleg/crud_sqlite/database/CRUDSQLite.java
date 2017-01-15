package com.example.oleg.crud_sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oleg.crud_sqlite.Config;
import com.example.oleg.crud_sqlite.model.Person;

import java.util.ArrayList;

/**
 * Created by Oleg on 06.01.2017.
 */

public class CRUDSQLite {

    SQLiteDBHelper sqLiteDBHelper;

    public CRUDSQLite(Context context) {

        this.sqLiteDBHelper = new SQLiteDBHelper(context);
    }

    public void AddPerson(Person person) {
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.KEY_ID, person.getId());
        contentValues.put(Config.KEY_NAME, person.getmName());
        contentValues.put(Config.KEY_SURNAME, person.getmSurname());
        contentValues.put(Config.KEY_PHONE, person.getmPhone());
        contentValues.put(Config.KEY_MAIL, person.getmMail());
        contentValues.put(Config.KEY_SKYPE, person.getmSkype());
        db.insert(Config.TABLE_PERSON, null, contentValues);
    }

    public ArrayList<Person> getAllPersons() {

        ArrayList<Person> persons = new ArrayList<>();
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(Config.COMMAND_SELECT, null);
        Person person = null;

        if (cursor.moveToFirst()) {
            do {
                person = new Person();
                person.setId(Integer.parseInt(cursor.getString(0)));
                person.setmName(cursor.getString(1));
                person.setmSurname(cursor.getString(2));
                person.setmPhone(cursor.getString(3));
                person.setmMail(cursor.getString(4));
                person.setmSkype(cursor.getString(5));
                persons.add(person);
            }
            while (cursor.moveToNext());
        }
        return persons;
    }
}
