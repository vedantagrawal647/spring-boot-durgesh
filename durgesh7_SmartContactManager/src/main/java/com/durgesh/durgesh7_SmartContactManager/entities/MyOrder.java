package com.durgesh.durgesh7_SmartContactManager.entities;

import javax.persistence.*;


@Entity
@Table(name="orders")
public class MyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long myOrderId;

    private String orderId;

    private String amount;

    public  String receipt;

    private  String status;

    @ManyToOne
    private  UserData userData;

    private  String paymentId;

    public MyOrder() {
        super();
    }

    public MyOrder(Long myOrderId, String orderId, String amount, String receipt, String status, UserData userData, String paymentId) {
        this.myOrderId = myOrderId;
        this.orderId = orderId;
        this.amount = amount;
        this.receipt = receipt;
        this.status = status;
        this.userData = userData;
        this.paymentId = paymentId;
    }

    public Long getMyOrderId() {
        return myOrderId;
    }

    public void setMyOrderId(Long myOrderId) {
        this.myOrderId = myOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

}
