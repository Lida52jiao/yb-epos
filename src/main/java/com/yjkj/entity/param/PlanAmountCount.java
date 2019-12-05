package com.yjkj.entity.param;

/**
 * Created by bin on 2018/6/6.
 */
public class PlanAmountCount implements java.io.Serializable{
    private Long number;
    private Long totalAmount;
    private Long d0Fee;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getD0Fee() {
        return d0Fee;
    }

    public void setD0Fee(Long d0Fee) {
        this.d0Fee = d0Fee;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }
}
