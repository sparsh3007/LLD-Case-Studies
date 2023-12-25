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
        BookMovieResponseDto bookMovieResponseDto=new BookMovieResponseDto();
        try {
            Booking booking=bookingService.bookmovie(bookMovieRequestDto.getUserId(),
                    bookMovieRequestDto.getShowId(),bookMovieRequestDto.getShowSeatId());
            bookMovieResponseDto.setBookingResponseStatus(BookingResponseStatus.CONFIRMED);
            bookMovieResponseDto.setAmount(booking.getAmount());
            bookMovieResponseDto.setBookingId(booking.getId());
        }
        catch (Exception exception){
            bookMovieResponseDto.setBookingResponseStatus(BookingResponseStatus.FAILURE);
            System.out.println(exception.getMessage());
        }
        return bookMovieResponseDto;
    }
    /* HomeWork
    public void cancelBooking(Long bookingId){

    }
     */
}
