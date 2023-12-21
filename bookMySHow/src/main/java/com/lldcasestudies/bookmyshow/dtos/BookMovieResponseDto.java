package com.lldcasestudies.bookmyshow.dtos;

import com.lldcasestudies.bookmyshow.models.BookingResponseStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BookMovieResponseDto {
    private Long bookingId;
    private int amount;
    BookingResponseStatus bookingResponseStatus;
}
