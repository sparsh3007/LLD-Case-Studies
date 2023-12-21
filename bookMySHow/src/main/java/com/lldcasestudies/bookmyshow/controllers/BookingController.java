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
        BookMovieResponseDto bookMovieResponseDto = new BookMovieResponseDto();
        try {
            Booking booking = bookingService.bookmovie(bookMovieRequestDto.getUserId(), bookMovieRequestDto.getShowId(), bookMovieRequestDto.getShowSeatId());
            bookMovieResponseDto.setAmount(booking.getAmount());
            bookMovieResponseDto.setBookingId(booking.getId());
            bookMovieResponseDto.setBookingResponseStatus(BookingResponseStatus.CONFIRMED);
        } catch (Exception e) {
            bookMovieResponseDto.setBookingResponseStatus(BookingResponseStatus.FAILURE);
        }
        return bookMovieResponseDto;
    }
}
