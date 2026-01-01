package com.example.bookmyshow.serivces;

import com.example.bookmyshow.models.Booking;
import com.example.bookmyshow.models.Show;
import com.example.bookmyshow.models.ShowSeat;
import com.example.bookmyshow.models.ShowSeatType;
import com.example.bookmyshow.repos.ShowSeatTypeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingAmountCalculationService {
    private ShowSeatTypeRepo showSeatTypeRepo;

    public BookingAmountCalculationService(ShowSeatTypeRepo showSeatTypeRepo) {
        this.showSeatTypeRepo = showSeatTypeRepo;
    }

    public double calculateBookingAmount(List<ShowSeat> showSeats) {

        Show show = showSeats.get(0).getShow();
        List<ShowSeatType> showSeatTypes = showSeatTypeRepo.findByShow(show);

        double bookingAmount = 0;

        for (ShowSeat showSeat : showSeats) {
            for (ShowSeatType showSeatType: showSeatTypes) {
                if (showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                    bookingAmount += showSeatType.getPrice();
                    break;
                }
            }
        }

        return bookingAmount;

    }
}
