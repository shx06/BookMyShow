package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dtos.BookMovieRequestDTO;
import com.example.bookmyshow.dtos.BookMovieResponseDTO;
import com.example.bookmyshow.dtos.ResponseStatus;
import com.example.bookmyshow.models.Booking;
import com.example.bookmyshow.serivces.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookMovieResponseDTO findBookingById(BookMovieRequestDTO req) {

        BookMovieResponseDTO res = new BookMovieResponseDTO();

        try {
            Booking booking = bookingService.bookMovie(req.getUserId(), req.getShowId(), req.getShowSeatsIds());

            if (booking != null) {
                res.setStatus(ResponseStatus.SUCCESS);
                res.setMessage("Booking is done successfully. Congratulations!!!");
                res.setBooking(booking);
            }

        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(ResponseStatus.FAILURE);
            res.setMessage("Error: " + e.getMessage());
        }

        return res;

    }
}
