package com.lldcasestudies.bookmyshow.controllers;

import com.lldcasestudies.bookmyshow.dtos.BookMovieRequestDto;
import com.lldcasestudies.bookmyshow.dtos.BookMovieResponseDto;
import com.lldcasestudies.bookmyshow.models.Booking;
import com.lldcasestudies.bookmyshow.models.BookingResponseStatus;
import com.lldcasestudies.bookmyshow.services.BookingService;

public class BookingController {
    private BookingService bookingService;
    BookingController(BookingService bookingService){
        this.bookingService=bookingService;
    }
    public BookMovieResponseDto bookmovie(BookMovieRequestDto bookMovieRequestDto){
//        return null;

        return null;
    }
}
