package com.kgc.pojo;

import com.kgc.utils.Page;

import java.math.BigDecimal;

public class Transactionrecord {
  private Integer id;
  private String cardno;
  private String transactiondate;
  private BigDecimal transactionamount;
  private BigDecimal balance;
  private String transactiontype;
  private String remark;

  private Page page;

  public Page getPage() {
    return page;
  }

  public void setPage(Page page) {
    this.page = page;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCardno() {
    return cardno;
  }

  public void setCardno(String cardno) {
    this.cardno = cardno;
  }

  public String getTransactiondate() {
    return transactiondate;
  }

  public void setTransactiondate(String transactiondate) {
    this.transactiondate = transactiondate;
  }

  public BigDecimal getTransactionamount() {
    return transactionamount;
  }

  public void setTransactionamount(BigDecimal transactionamount) {
    this.transactionamount = transactionamount;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public String getTransactiontype() {
    return transactiontype;
  }

  public void setTransactiontype(String transactiontype) {
    this.transactiontype = transactiontype;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Override
  public String toString() {
    return "Transactionrecord{" +
            "id=" + id +
            ", cardno='" + cardno + '\'' +
            ", transactiondate='" + transactiondate + '\'' +
            ", transactionamount=" + transactionamount +
            ", balance=" + balance +
            ", transactiontype='" + transactiontype + '\'' +
            ", remark='" + remark + '\'' +
            '}';
  }
}
