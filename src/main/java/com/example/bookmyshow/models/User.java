package com.example.bookmyshow.models;

import java.util.List;

public class User extends BaseModel {
    private String name;
    private String email;
    private String password;
    private List<Booking> bookings;
}
