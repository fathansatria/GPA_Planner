package com.example.gpaplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final static String DATABASENAME = "DbGPA";
    private final static String DATABASENAME1 = "DbGPA.sqlite";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_MHS = "mahasiswa_table";
    private static final String NAMA = "nama";
    private static final String PRODI = "prodi";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private String TBL_CREATE_MHS = "create table " + TABLE_MHS + " (" +
            USERNAME + " text primary key," +
            NAMA + " text," +
            PRODI + " text," +
            PASSWORD + " text)";

    private static final String TABLE_MATKUL = "matkul_table";
    private static final String KODEKULIAH = "kodekuliah";
    private static final String NAMAMATKUL = "namamatkul";
    private static final String SKS = "sks";
    private static final String KUIS = "kuis";
    private static final String TUGAS = "tugas";
    private static final String UTS = "uts";
    private static final String UAS = "uas";
    private static final String FINALPROJECT = "finalproject";
    private static final String PRAKTIKUM = "praktikum";
    private static final String DOSEN = "dosen";
    private static final String USER = "user";
    private static final String KUIS_PERSENT = "kuis_persent";
    private static final String TUGAS_PERSENT = "tugas_persent";
    private static final String UTS_PERSENT = "uts_persent";
    private static final String UAS_PERSENT = "uas_persent";
    private static final String FINALPROJECT_PERSENT = "finalproject_persent";
    private static final String PRAKTIKUM_PERSENT = "praktikum_persent";
    private static final String TARGET = "target";
    private static final String JUMLAH_KUIS = "jumlah_kuis";
    private static final String JUMLAH_TUGAS = "jumlah_tugas";


    private String TBL_CREATE_MATKUL = "create table " + TABLE_MATKUL + " (" +
            KODEKULIAH + " integer PRIMARY KEY ," +
            USER + " text," +
            NAMAMATKUL + " text," +
            SKS + " integer," +
            KUIS + " float," +
            TUGAS + " float, " +
            UTS + " float, " +
            UAS + " float, " +
            FINALPROJECT + " float," +
            KUIS_PERSENT + " float," +
            TUGAS_PERSENT + " float, " +
            UTS_PERSENT + " float, " +
            UAS_PERSENT + " float, " +
            JUMLAH_KUIS + " integer, " +
            JUMLAH_TUGAS + " integer, " +
            TARGET + " text, " +
            FINALPROJECT_PERSENT + " float," +
            PRAKTIKUM_PERSENT + " float," +
            DOSEN + " text, " +
            PRAKTIKUM + " float)";



    public DatabaseHelper(Context context)
    {
        super(context,DATABASENAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TBL_CREATE_MHS);
        sqLiteDatabase.execSQL(TBL_CREATE_MATKUL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MATKUL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MHS);
        onCreate(sqLiteDatabase);
    }

    public String registerStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        String username = student.getUsername();


            try{
                ContentValues values = new ContentValues();
                values.put(NAMA, student.getName());
                values.put(USERNAME, student.getUsername());
                values.put(PASSWORD, student.getPassword());
                values.put(PRODI, student.getMajor());
                db.insert(TABLE_MHS, null, values);
            }
            catch(SQLException i){
                System.out.println("Error");
            }

        // assigning tags to tbl create mengambil
        db.close();
        return username ;
    }

    /*
     * Creating tag
     */
    public long createCourse(Course matkul, String user) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAMAMATKUL, matkul.getCourseName());
        values.put(USER,user);
        values.put(SKS, matkul.getSks());
        values.put(UTS, matkul.getUts());
        values.put(UAS, matkul.getUas());
        values.put(KUIS, matkul.getQuiz());
        values.put(JUMLAH_KUIS, matkul.getJumlah_quiz());
        values.put(JUMLAH_TUGAS, matkul.getJumlah_tugas());
        values.put(TUGAS, matkul.getTugas());
        values.put(FINALPROJECT, matkul.getFp());
        values.put(PRAKTIKUM, matkul.getPraktikum());
        values.put(UTS_PERSENT, matkul.getUtsPersent());
        values.put(UAS_PERSENT, matkul.getUasPersent());
        values.put(KUIS_PERSENT, matkul.getQuizPersent());
        values.put(TUGAS_PERSENT, matkul.getTugasPersent());
        values.put(FINALPROJECT_PERSENT, matkul.getFpPersent());
        values.put(DOSEN, matkul.getTeacher());
        values.put(TARGET, matkul.getTarget());
        values.put(PRAKTIKUM_PERSENT, matkul.getPraktikumPersent());

        // insert row
        long kodekuliah = db.insert(TABLE_MATKUL, null, values);


        return kodekuliah;
    }


    public Student getStudent(String user) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_MHS + " WHERE "
                + USERNAME + " = " + user;

        Log.e(DATABASENAME, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Student std = new Student();
        std.setUsername((c.getString(c.getColumnIndex(USERNAME))));
        std.setMajor(c.getString(c.getColumnIndex(PASSWORD)));
        std.setName(c.getString(c.getColumnIndex(NAMA)));
        std.setMajor(c.getString(c.getColumnIndex(PRODI)));

        return std;
    }

    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<Student>();
        String selectQuery = "SELECT  * FROM " + TABLE_MHS;

        Log.e(DATABASENAME, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Student std = new Student();
                std.setUsername((c.getString(c.getColumnIndex(USERNAME))));
                std.setPassword(c.getString(c.getColumnIndex(PASSWORD)));
                std.setName(c.getString(c.getColumnIndex(NAMA)));
                std.setMajor(c.getString(c.getColumnIndex(PRODI)));

                // adding to todo list
                students.add(std);
            } while (c.moveToNext());
        }

        return students;
    }

    public Student getPass(long username) {

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = " SELECT " + PASSWORD + " FROM " + TABLE_MHS + " WHERE "
                + USERNAME + " = " + username;

        Log.e(DATABASENAME, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Student std = new Student();
        std.setUsername((c.getString(c.getColumnIndex(USERNAME))));
        std.setMajor(c.getString(c.getColumnIndex(PASSWORD)));
        std.setName(c.getString(c.getColumnIndex(NAMA)));
        std.setMajor(c.getString(c.getColumnIndex(PRODI)));

        return std;
    }

    public void deleteDatabase(Context c){
        c.deleteDatabase(DATABASENAME1);
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    public List<Course> getAllCoursesByUsername(String username) {
        List<Course> courses = new ArrayList<Course>();

        String selectQuery = "SELECT  * FROM " + TABLE_MATKUL + " tm WHERE tm."
                + USER + " = '" + username + "'";

        Log.e(DATABASENAME, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                Course c1 = new Course();
                c1.setCourseName(c.getString((c.getColumnIndex(NAMAMATKUL))));
                c1.setTeacher(c.getString((c.getColumnIndex(DOSEN))));
                c1.setSks(c.getInt((c.getColumnIndex(SKS))));
                c1.setJumlah_quiz(c.getInt((c.getColumnIndex(JUMLAH_KUIS))));
                c1.setJumlah_tugas(c.getInt((c.getColumnIndex(JUMLAH_TUGAS))));
                c1.setUser(c.getString((c.getColumnIndex(USER))));
                c1.setUts(c.getDouble((c.getColumnIndex(UTS))));
                c1.setUas(c.getDouble((c.getColumnIndex(UAS))));
                c1.setTugas(c.getDouble((c.getColumnIndex(TUGAS))));
                c1.setQuiz(c.getDouble((c.getColumnIndex(KUIS))));
                c1.setFp(c.getDouble((c.getColumnIndex(FINALPROJECT))));
                c1.setPraktikum(c.getDouble((c.getColumnIndex(PRAKTIKUM))));
                c1.setUtsPersent(c.getDouble((c.getColumnIndex(UTS_PERSENT))));
                c1.setUasPersent(c.getDouble((c.getColumnIndex(UAS_PERSENT))));
                c1.setTugasPersent(c.getDouble((c.getColumnIndex(TUGAS_PERSENT))));
                c1.setQuizPersent(c.getDouble((c.getColumnIndex(KUIS_PERSENT))));
                c1.setFpPersent(c.getDouble((c.getColumnIndex(FINALPROJECT_PERSENT))));
                c1.setTarget(c.getString((c.getColumnIndex(TARGET))));
                c1.setPraktikumPersent(c.getDouble((c.getColumnIndex(PRAKTIKUM_PERSENT))));

                // adding to courses
                courses.add(c1);

            } while (c.moveToNext());
        }

        return courses;
    }

    public Course getUserCourse(String username, String coursename) {

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = " SELECT  *  FROM " + TABLE_MATKUL + " tm WHERE tm."
                + USER + " = '" + username + "'" + " AND tm." + NAMAMATKUL + " = '" + coursename + "'";

        Log.e(DATABASENAME, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Course c1 = new Course();
        c1.setCourseName(c.getString((c.getColumnIndex(NAMAMATKUL))));
        c1.setTeacher(c.getString((c.getColumnIndex(DOSEN))));
        c1.setSks(c.getInt((c.getColumnIndex(SKS))));
        c1.setJumlah_quiz(c.getInt((c.getColumnIndex(JUMLAH_KUIS))));
        c1.setJumlah_tugas(c.getInt((c.getColumnIndex(JUMLAH_TUGAS))));
        c1.setUser(c.getString((c.getColumnIndex(USER))));
        c1.setUts(c.getDouble((c.getColumnIndex(UTS))));
        c1.setUas(c.getDouble((c.getColumnIndex(UAS))));
        c1.setTugas(c.getDouble((c.getColumnIndex(TUGAS))));
        c1.setQuiz(c.getDouble((c.getColumnIndex(KUIS))));
        c1.setFp(c.getDouble((c.getColumnIndex(FINALPROJECT))));
        c1.setPraktikum(c.getDouble((c.getColumnIndex(PRAKTIKUM))));
        c1.setUtsPersent(c.getDouble((c.getColumnIndex(UTS_PERSENT))));
        c1.setUasPersent(c.getDouble((c.getColumnIndex(UAS_PERSENT))));
        c1.setTugasPersent(c.getDouble((c.getColumnIndex(TUGAS_PERSENT))));
        c1.setQuizPersent(c.getDouble((c.getColumnIndex(KUIS_PERSENT))));
        c1.setFpPersent(c.getDouble((c.getColumnIndex(FINALPROJECT_PERSENT))));
        c1.setPraktikumPersent(c.getDouble((c.getColumnIndex(PRAKTIKUM_PERSENT))));
        c1.setTarget(c.getString((c.getColumnIndex(TARGET))));

        return c1;
    }
}
