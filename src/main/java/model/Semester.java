package model;

public class Semester {
    private int semesterId;        // ID của học kỳ
    private String semesterName;   // Tên học kỳ (VD: "Học kỳ 1")
    private String year;           // Năm học (VD: "2024-2025")

    public Semester() {
    }

    public Semester(int semesterId, String semesterName, String year) {
        this.semesterId = semesterId;
        this.semesterName = semesterName;
        this.year = year;
    }

    // Getters và Setters
    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
