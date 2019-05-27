package com.example.gpaplanner;

public class Course {

    //General
    private int courseId = 0;
    private String courseName = "Not Set";
    private int sks = 0;
    private String teacher = "Not Set";
    private String user = "Not Set";

    //Value
    private double uts = -99.00;
    private double uas = -99.00;
    private double fp = -99.00;
    private double praktikum = -99.00;
    private double quiz = -99.00;
    private double tugas = -99.00;

    //Persent
    private double utsPersent = -99.00;
    private double uasPersent = -99.00;
    private double fpPersent = -99.00;
    private double praktikumPersent = -99.00;
    private double quizPersent = -99.00;
    private double tugasPersent = -99.00;

    //total Score
    private String target = "Not Set";
    private int jumlah_tugas = 0;
    private int jumlah_quiz = 0;

    public Course(){

    }

    //Setter

    public void setFp(double fp) {
        this.fp = fp;
    }

    public void setTugas(double tugas) {
        this.tugas = tugas;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setUts(double uts) {
        this.uts = uts;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setFpPersent(double fpPersent) {
        this.fpPersent = fpPersent;
    }

    public void setUas(double uas) {
        this.uas = uas;
    }

    public void setPraktikum(double praktikum) {
        this.praktikum = praktikum;
    }

    public void setQuiz(double quiz) {
        this.quiz = quiz;
    }

    public void setQuizPersent(double quizPersent) {
        this.quizPersent = quizPersent;
    }

    public void setUasPersent(double uasPersent) {
        this.uasPersent = uasPersent;
    }

    public void setUtsPersent(double utsPersent) {
        this.utsPersent = utsPersent;
    }

    public void setTugasPersent(double tugasPersent) { this.tugasPersent = tugasPersent; }

    public void setPraktikumPersent(double praktikumPersent) {
        this.praktikumPersent = praktikumPersent;
    }

    public void setJumlah_quiz(int jumlah_quiz) {
        this.jumlah_quiz = jumlah_quiz;
    }

    public void setJumlah_tugas(int jumlah_tugas) {
        this.jumlah_tugas = jumlah_tugas;
    }

    //Getter
    public String getTarget() {
        return target;
    }

    public String getTeacher() {
        return teacher;
    }

    public double getFp() {
        return fp;
    }

    public double getPraktikum() {
        return praktikum;
    }

    public double getUas() {
        return uas;
    }

    public double getUts() {
        return uts;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getSks() {
        return sks;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getFpPersent() {
        return fpPersent;
    }

    public double getPraktikumPersent() {
        return praktikumPersent;
    }

    public double getUasPersent() { return uasPersent; }

    public double getUtsPersent() { return utsPersent; }

    public double getTugasPersent() { return tugasPersent; }

    public double getQuiz() {
        return quiz;
    }

    public double getTugas() { return tugas;}

    public double getQuizPersent() {
        return quizPersent;
    }

    public String getUser() {
        return user;
    }

    public int getJumlah_quiz() {
        return jumlah_quiz;
    }

    public int getJumlah_tugas() {
        return jumlah_tugas;
    }
}
