package com.example.bookmyshow.models;

import java.util.Date;
import java.util.List;

public class Show extends BaseModel {
    private Theatre theatre;
    private Screen screen;
    private Movie movie;
    private String startTime;
    private String endTime;
    private Date showDate;
    private List<ShowSeat> seats;
    private List<ShowSeatType> seatTypes;
}
