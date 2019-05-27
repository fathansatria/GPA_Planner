package com.example.gpaplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CourseDetail extends AppCompatActivity {

    private TextView tv_courseName;
    private String account,courseName = "Not Set", dosen, totalScore = "Not Set",target = "Not Set";
    private DatabaseHelper db;
    private Course currentCourse;

    //courseData
    private Double uts, uas, fp, prak, tugas, quis;
    private Double uts_persen, uas_persen, fp_persen, prak_persen, tugas_persen, quis_persen;
    private Double progress;
    private int jumlah_tgs, jumlah_kuis,sks;

    //Design
    private EditText targetText;
    private TextView tugasBox,kuisBox,utsBox,uasBox,prakBox,fpBox, finalScore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        db = new DatabaseHelper(this);

        tv_courseName = findViewById(R.id.tv_course);
        tugasBox = findViewById(R.id.tv_assign);
        kuisBox = findViewById(R.id.tv_quiz);
        utsBox = findViewById(R.id.tv_uts);
        uasBox = findViewById(R.id.tv_uas);
        prakBox = findViewById(R.id.tv_praktikum);
        fpBox = findViewById(R.id.tv_final);
        finalScore = findViewById(R.id.tv_current_score);

        Bundle extras = getIntent().getExtras();
        if(extras != null){

            account = extras.getString("user");
            courseName = extras.getString("coursename");

        }



        currentCourse = db.getUserCourse(account, courseName);

        //keterangan umum
        courseName =  currentCourse.getCourseName();
        tv_courseName.setText(courseName);

        dosen = currentCourse.getTeacher();
        target = currentCourse.getTarget();
        sks = currentCourse.getSks();
        jumlah_kuis = currentCourse.getJumlah_quiz();
        jumlah_tgs = currentCourse.getJumlah_tugas();

        //Nilai
        fp = currentCourse.getFp();
        tugas = currentCourse.getTugas();
        prak = currentCourse.getPraktikum();
        uts = currentCourse.getUts();
        uas = currentCourse.getUas();
        quis = currentCourse.getQuiz();

        //Persentase
        fp_persen = currentCourse.getFpPersent();
        prak_persen = currentCourse.getPraktikumPersent();
        quis_persen = currentCourse.getQuizPersent();
        uts_persen = currentCourse.getUtsPersent();
        uas_persen = currentCourse.getUasPersent();
        tugas_persen = currentCourse.getTugasPersent();

        //Inisiasi Box

        try{
            inisiasi(fpBox, fp);
            inisiasi(utsBox, uts);
            inisiasi(uasBox, uas);
            inisiasi(prakBox, prak);
            usingRate(tugasBox, tugas, jumlah_tgs);
            usingRate(kuisBox, quis, jumlah_kuis);
        }
        catch(NullPointerException n){
            Toast.makeText(getApplicationContext(), "Many Data Not Set", Toast.LENGTH_LONG).show();
        }


        Double numberScore = setCourseScore(tugas, quis,fp, uts, uas, prak, uts_persen,
                uas_persen, tugas_persen, quis_persen,prak_persen,fp_persen,jumlah_tgs, jumlah_kuis );

        totalScore = scoreConverter(numberScore);

        try {
            finalScore.setText(totalScore);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Many Data Not Set", Toast.LENGTH_LONG).show();
        }

        progress = numberScore/targetConverter(target);


    }

    public void inisiasi (TextView tv, Double val){

        if (val.equals(-99.00)){
            tv.setText("Not Set");
        }
        else{
            tv.setText(val.toString());
        }
    }

    public void usingRate (TextView tv, Double val, int n){

        if (val.equals(-99.00)){
            tv.setText("Not Set");
        }
        else{
            Double rate = val/n;
            tv.setText(rate.toString());
        }
    }

    public Double setCourseScore(Double assign1, Double qz,
                               Double fp, Double midEx, Double finalEx, Double prac,Double komp_uts,
                               Double komp_uas, Double komp_tugas, Double komp_kuis, Double komp_prak,
                               Double komp_fp, int n_tugas, int n_quiz){

        Double mid_val = notSetChecker(midEx)*notSetChecker(komp_uts);
        Double uas_val = notSetChecker(finalEx)*notSetChecker(komp_uas);
        Double prak_val = notSetChecker(prac)*notSetChecker(komp_prak);
        Double fp_val = notSetChecker(fp)*notSetChecker(komp_fp);
        Double tugas_val = notSetSpecial(assign1, n_tugas)*notSetChecker(komp_tugas);
        Double kuis_val = notSetSpecial(qz, n_quiz)*notSetChecker(komp_kuis);

        return mid_val + uas_val + prak_val + fp_val + tugas_val + kuis_val;
    }

    public Double notSetChecker(Double var){
        Double temp;

        if (var.equals(-99)){
            temp = 0.00;
        }
        else{
            temp = var;
        }
        return temp;
    }

    public Double notSetSpecial (Double val, int n){

        Double temp;

        if (val.equals(-99.00)){
            temp = 0.00;
        }
        else{
            temp = val/n;
        }

        return temp;

    }

    public String scoreConverter(Double score){

        String val;

        if (score >= 80){
            val = "A";
        }
        else if (score >= 75 && score < 80){
            val = "A-";
        }
        else if (score >= 70 && score < 75){
            val = "B+";
        }
        else if (score >= 65 && score < 70){
            val = "B";
        }
        else if (score >= 60 && score < 65){
            val = "B-";
        }
        else if (score >= 55 && score < 60){
            val = "C+";
        }
        else if (score >= 45 && score < 55){
            val = "C";
        }
        else if (score >= 35 && score < 45){
            val = "D";
        }
        else if (score < 80 && score> 0){
            val = "E";
        }
        else {
            val = "Error Score";
        }

        return val;
    }

    public Double targetConverter(String t){
        Double tmp;
        String tg = t.toUpperCase();

        if(tg.equals("A")){
            tmp = 80.00;
        }
        else if(tg.equals("A-")){
            tmp = 75.00;
        }
        else if(tg.equals("B+")){
            tmp = 70.00;
        }
        else if(tg.equals("B")){
            tmp = 65.00;
        }
        else if(tg.equals("B-")){
            tmp = 60.00;
        }
        else if(tg.equals("C+")){
            tmp = 55.00;
        }
        else if(tg.equals("C")){
            tmp = 45.00;
        }
        else if(tg.equals("D")){
            tmp = 35.00;
        }
        else if(tg.equals("E")){
            tmp = 0.00;
        }
        else{
            tmp = 0.00;
        }

        return tmp;
    }

}
