package com.example.bookmyshow.models;

import java.util.Date;
import java.util.List;

public class Booking extends BaseModel{
    private User user;
    private Date bookingDate;
    private List<Payment> payments;
    private Show show;
    private BookingStatus bookingStatus;
}
