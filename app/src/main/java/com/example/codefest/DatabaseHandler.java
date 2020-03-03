package com.example.codefest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.example.codefest.Util.DATABASE_NAME;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
        CREATE TABLE tableName(id INTEGER PRIMARY KEY, name TEXT, phone_number TEXT);
         */
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME
                + "(" + Util.KEY_ID + " INTEGER PRIMARY KEY, "
                + Util.KEY_NAME + " TEXT,"
                + Util.KEY_PHONE_NUMBER + " TEXT" + ")";

        String CREATE_DATETIME_TABLE = "CREATE TABLE " + Util.TABLE_DATE_TIME
                + "(" + Util.KEY_ID_DATE_TIME + " INTEGER PRIMARY KEY, "
                + Util.KEY_BIRTH_DATE + " TEXT,"
                + Util.KEY_TIME + " TEXT" + ")";

        db.execSQL(CREATE_CONTACT_TABLE);
        db.execSQL(CREATE_DATETIME_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf(R.string.db_drop);
        db.execSQL(DROP_TABLE, new String[]{DATABASE_NAME});

        onCreate(db);
    }

    public void addDateTime(TimeDate timeDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Util.KEY_BIRTH_DATE, timeDate.getDate_time());
        values.put(Util.KEY_TIME, timeDate.getTime());

        //Insert to row
        db.insert(Util.TABLE_DATE_TIME, null, values);

        Log.d("DBHandler", "addDateTime: " + "item added");
        db.close();
    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        //Insert to row
        db.insert(Util.TABLE_NAME, null, values);

        Log.d("DBHandler", "addContact: " + "item added");
        db.close();
    }

    //get 1 contact
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME,
                new String[]{ Util.KEY_ID, Util.KEY_NAME, Util.KEY_PHONE_NUMBER},
                Util.KEY_ID +"=?",new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact("James", "213986");
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhoneNumber(cursor.getString(2));

        return contact;
    }

    //get all contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        //Select all contacts
        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through our data
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact("James", "213986");
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                //add contact objects to our list
                contactList.add(contact);
            }while (cursor.moveToNext());
        }

        return contactList;
    }

    //update
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        //update the row
        //update(tablename, values, where id = 43)
        return db.update(Util.TABLE_NAME, values, Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});
    }

    //delete
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});

        db.close();
    }
    public void deleteContact1(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});

        db.close();
    }

    //Get contacts count
    public int getCount() {
        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();

    }
}
