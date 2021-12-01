package com.example.lab1.SqliteDB_part;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.lab1.Student_model;

public class operations_student extends SQLiteOpenHelper {
    private static final int DB_VERSION=1;
    private static final String RegNo="RegNo";
    private static final String Lastname="Lastname";
    private static final String Firstname="Firstname";
    private static final String Department="Department";
    private static final String DB_NAME="student_database";
    private static final String TABLE_NAME="students_table";
    Student_model student_model;
    Context context;
    public operations_student(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
    }

    @Override

    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + Firstname + " TEXT, "
                + Lastname + " TEXT,"
                + RegNo+ "  INTEGER PRIMARY KEY,"
                + Department+ " TEXT)";


        db.execSQL(query);
    }
    public void addStudent(Student_model student_model) {

        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();


        values.put(Firstname,student_model.getFname());
        values.put(Lastname,student_model.getLname());
        values.put(RegNo,student_model.getRegno());
        values.put(Department,student_model.getDepartement());

try {
    db.insert(TABLE_NAME, null, values);
    Toast.makeText(context, "student is added", Toast.LENGTH_SHORT).show();
}
catch (Exception e){
    Log.d("Error:",e.getMessage());
    Toast.makeText(context, "Unable to add a record: "+e.getMessage(), Toast.LENGTH_SHORT).show();
}

        db.close();
    }
    public void deleteStudent(String regno_toDelete){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,"RegNo="+regno_toDelete ,null);
        Toast.makeText(context, "Student with RegNo: "+regno_toDelete+" is deleted", Toast.LENGTH_SHORT).show();
        db.close();
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
