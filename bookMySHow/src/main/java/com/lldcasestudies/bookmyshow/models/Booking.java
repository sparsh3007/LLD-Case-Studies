package com.lldcasestudies.bookmyshow.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    @ManyToOne
    private User user;
    @ManyToMany // If someone cancels a ticket then the same showseats will be available to multiple bookings
    private List<ShowSeat> showSeats;
    private int amount;
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
    private Date bookedDate;
    @OneToMany
    private List<Payment> payments;
}
