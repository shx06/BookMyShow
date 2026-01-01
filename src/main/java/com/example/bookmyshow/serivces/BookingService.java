package com.example.bookmyshow.serivces;

import com.example.bookmyshow.models.*;
import com.example.bookmyshow.repos.BookingRepo;
import com.example.bookmyshow.repos.ShowRepo;
import com.example.bookmyshow.repos.ShowSeatRepo;
import com.example.bookmyshow.repos.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private UserRepo userRepo;
    private ShowRepo showRepo;
    private ShowSeatRepo showSeatRepo;
    private BookingRepo bookingRepo;
    private BookingAmountCalculationService bookingAmountCalculationService;

    public BookingService(
            UserRepo userRepo,
            ShowRepo showRepo,
            ShowSeatRepo showSeatRepo,
            BookingRepo bookingRepo,
            BookingAmountCalculationService bookingAmountCalculationService) {
        this.userRepo = userRepo;
        this.showRepo = showRepo;
        this.showSeatRepo = showSeatRepo;
        this.bookingRepo = bookingRepo;
        this.bookingAmountCalculationService = bookingAmountCalculationService;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie (long userId, long showId, List<Long> showSeatIds) {

        //Get user by userId
        Optional<User> optionalUser = userRepo.findById((int) userId);

        if (optionalUser.isEmpty()){
            throw new RuntimeException("Cannot find user with the user ID.");
        }

        User user = optionalUser.get();

        //Get show by showId
        Optional<Show> optionalShow = showRepo.findById(showId);
        if (optionalShow.isEmpty()){
            throw new RuntimeException("Cannot find show with the show ID.");
        }

        Show show = optionalShow.get();

        //Get all showSeats by showSeatIds
        List<ShowSeat> showSeats = showSeatRepo.findAllById(showSeatIds);

        for(ShowSeat showSeat : showSeats){
            if (!showSeat.getStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new RuntimeException("Show seat with this Show seat id: " + showSeat
                        .getId() + " is not available");
            }
        }

        //mark all showSeats blocked
        for (ShowSeat showSeat : showSeats) {
            showSeat.setStatus(ShowSeatStatus.BLOCKED);
            showSeatRepo.save(showSeat);
        }

        //create the booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setShowSeats(showSeats);
        booking.setBookingDate(new Date());
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setAmount(bookingAmountCalculationService.calculateBookingAmount(showSeats));

        bookingRepo.save(booking);
        return booking;
    }
}
