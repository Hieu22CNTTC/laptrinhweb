package model;

// Lớp biểu diễn thông tin khoản thanh toán
public class Payment {
    private int paymentId;
    private int studentId;
    private String studentName; // Tên sinh viên
    private int feeId;
    private double amount; // Số tiền từ bảng fee
    private String status;
    private int classId; // Mã lớp
    private String className; // Tên lớp
    private int semesterId; // Mã học kỳ
    private String semesterName; // Tên học kỳ

    // Constructor mặc định
    public Payment() {
    }

    // Constructor đầy đủ
    public Payment(int paymentId, int studentId, String studentName, int feeId,
                   double amount, String status, int classId, String className,
                   int semesterId, String semesterName) {
        this.paymentId = paymentId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.feeId = feeId;
        this.amount = amount;
        this.status = status;
        this.classId = classId;
        this.className = className;
        this.semesterId = semesterId;
        this.semesterName = semesterName;
    }

    // Getters và Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getFeeId() {
        return feeId;
    }

    public void setFeeId(int feeId) {
        this.feeId = feeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    // Override toString()
    @Override
    public String toString() {
        return "Payment {" +
                "Payment ID=" + paymentId +
                ", Student ID=" + studentId +
                ", Student Name='" + studentName + '\'' +
                ", Fee ID=" + feeId +
                ", Amount=" + amount +
                ", Status='" + status + '\'' +
                ", Class ID=" + classId +
                ", Class Name='" + className + '\'' +
                ", Semester ID=" + semesterId +
                ", Semester Name='" + semesterName + '\'' +
                '}';
    }
}
