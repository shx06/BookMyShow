package com.example.bookmyshow.dtos;


import com.example.bookmyshow.models.Booking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDTO extends CommonResponse{
    private Booking booking;
}
