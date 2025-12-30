package com.example.bookmyshow.models;

import java.util.Date;

public class Payment extends BaseModel {
    private double amount;
    private Date paymentDate;
    private PaymentGateway paymentGateway;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;
    private String refNum;
}
