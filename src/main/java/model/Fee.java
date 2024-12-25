package model;

import java.math.BigDecimal;

public class Fee {
    private int feeId; // ID khoản phí
    private int semesterId; // Học kỳ
    private BigDecimal amount; // Số tiền

    public Fee() {
    }

    public Fee(int feeId, int semesterId, BigDecimal amount) {
        this.feeId = feeId;
        this.semesterId = semesterId;
        this.amount = amount;
    }

    // Getters và Setters
    public int getFeeId() {
        return feeId;
    }

    public void setFeeId(int feeId) {
        this.feeId = feeId;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Fee [ID = " + feeId + ", Học kỳ = " + semesterId + ", Số tiền = " + amount + "]";
    }
}
