package model;

public class Student {
    private int id; // ID của sinh viên
    private String name; // Tên sinh viên
    private String email; // Email sinh viên
    private int classId; // ID lớp học
    private String className; // Tên lớp học

    // Constructor mặc định
    public Student() {}

    // Constructor đầy đủ tham số
    public Student(int id, String name, String email, int classId, String className) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.classId = classId;
        this.className = className;
    }

    // Getter và Setter cho các thuộc tính
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    // Phương thức để hiển thị thông tin sinh viên
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", classId=" + classId +
                ", className='" + className + '\'' +
                '}';
    }
}
