package model;

import java.util.List;

public class Class {
    private int classId; // ID lớp học
    private String className; // Tên lớp học
    private String description; // Mô tả lớp học
    private List<Student> studentsList; // Danh sách sinh viên trong lớp

    // Constructor mặc định
    public Class() {}

    // Constructor đầy đủ tham số
    public Class(int classId, String className, String description) {
        this.classId = classId;
        this.className = className;
        this.description = description;
    }

    // Getters và Setters
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(List<Student> studentsList) {
        this.studentsList = studentsList;
    }

    @Override
    public String toString() {
        return "Class{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", description='" + description + '\'' +
                ", studentsList=" + studentsList +
                '}';
    }
}
