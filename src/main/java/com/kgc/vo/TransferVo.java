package com.kgc.vo;

import java.math.BigDecimal;

/**
 * Created by jora on 2019/5/20.
 * v:ViewModel
 */
public class TransferVo {
    String Outcardno;
    String Incardno;
    BigDecimal tranferAmount;
    String remark;

    public String getOutcardno() {
        return Outcardno;
    }

    public void setOutcardno(String outcardno) {
        Outcardno = outcardno;
    }

    public String getIncardno() {
        return Incardno;
    }

    public void setIncardno(String incardno) {
        Incardno = incardno;
    }

    public BigDecimal getTranferAmount() {
        return tranferAmount;
    }

    public void setTranferAmount(BigDecimal tranferAmount) {
        this.tranferAmount = tranferAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "TransferVo{" +
                "Outcardno='" + Outcardno + '\'' +
                ", Incardno='" + Incardno + '\'' +
                ", tranferAmount=" + tranferAmount +
                ", remark='" + remark + '\'' +
                '}';
    }
}
