package com.lldcasestudies.bookmyshow.services;

import com.lldcasestudies.bookmyshow.models.Show;
import com.lldcasestudies.bookmyshow.models.ShowSeat;
import com.lldcasestudies.bookmyshow.models.ShowSeatType;
import com.lldcasestudies.bookmyshow.repositories.ShowSeatTypeRepository;

import java.util.List;

public class PriceCalculatorService {
    private ShowSeatTypeRepository showSeatTypeRepository;
    PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository){
        this.showSeatTypeRepository=showSeatTypeRepository;
    }
    public int calculatePrice(List<ShowSeat> showSeats, Show show){
//        First get the type of the seats from showSeats -> seat
//        get the price of each type from shoeSeatType object
        int amount=0;
        List<ShowSeatType> showSeatTypes=showSeatTypeRepository.findAllByShow(show);
        for(ShowSeat showSeat:showSeats) {
            for (ShowSeatType showSeatType : showSeatTypes) {
                if (showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())){
                    amount+=showSeatType.getPrice();
                    break;
                }
            }
        }
        return  amount;
    }
}
