package com.lldcasestudies.bookmyshow.services;

import com.lldcasestudies.bookmyshow.exceptions.InvalidShowSeatException;
import com.lldcasestudies.bookmyshow.exceptions.InvalidUserException;
import com.lldcasestudies.bookmyshow.exceptions.InvalidShowException;
import com.lldcasestudies.bookmyshow.models.*;
import com.lldcasestudies.bookmyshow.repositories.ShowRepository;
import com.lldcasestudies.bookmyshow.repositories.ShowSeatRepository;
import com.lldcasestudies.bookmyshow.repositories.UserRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculatorService priceCalculatorService;
    BookingService(UserRepository userRepository,ShowRepository showRepository, ShowSeatRepository showSeatRepository, PriceCalculatorService priceCalculatorService){
        this.showRepository=showRepository;
        this.userRepository=userRepository;
        this.showSeatRepository=showSeatRepository;
        this.priceCalculatorService=priceCalculatorService;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookmovie(Long userId, Long showId, List<Long> showSeatIds){
        /*
        Steps to book movie tickets:
        -----Acquire the lock ------
        // bad approach to acquire the lock for the entire method, but easier to implement
        1. Get the User from the UserId from DB
        2. Get the Show from the showId
        3. Get the list of Show seats from showSeatIds from the DB
        4. Check if all the seats are available or not
        // -------- Acquire the lock -------at this step in real project but it's difficult to implement
        5. If not then throw an exception -> Code
        6. If yes then mark the status as Blocked
        7. Save the status of seats in the DB as well
        // --------Release the lock---------at this stage in real project
        8. Creating the booking object
        9. Save the booking to DB
        10. Return the booking.
        ------Release the lock-------
         */
//        1. Get the User from the UserId from DB
        Optional<User> optionalUser=userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new InvalidUserException("Invalid user Id");
        }
//        2. Get the Show from the showId
        User bookedBy=optionalUser.get();
        Optional<Show> optionalShow=showRepository.findById(showId);
        if(optionalShow.isEmpty()){
            throw new InvalidShowException("Invalid show Id");
        }
        Show bookedShow= optionalShow.get();
//        3. Get the list of Show seats from showSeatIds from the DB
//        4. Check if all the seats are available or not

        List<ShowSeat> showSeats=showSeatRepository.findAllById(showSeatIds);
        for(ShowSeat showSeat: showSeats) {
            if (!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new InvalidShowSeatException("ShowSeat with id" + showSeat.getId() + " not available");
            }
        }
//        6. If yes then mark the status as Blocked
        for(ShowSeat showSeat: showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
//            7. Save the status of seats in the DB as well
            showSeatRepository.save(showSeat);
        }
//        8. Creating the booking object
        Booking booking=new Booking();
        booking.setUser(bookedBy);
        booking.setBookingStatus(BookingStatus.IN_PROGRESS);
        booking.setPayments(new ArrayList<>());
        booking.setBookedDate(new Date());
        booking.setShowSeats(showSeats);
        booking.setCreatedAt(new Date());
        booking.setAmount(priceCalculatorService.calculatePrice(showSeats,bookedShow));
        return booking;


    }
}
