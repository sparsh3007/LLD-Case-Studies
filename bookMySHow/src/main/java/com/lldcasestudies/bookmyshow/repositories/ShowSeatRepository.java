package com.lldcasestudies.bookmyshow.repositories;

import com.lldcasestudies.bookmyshow.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {
    @Override
    List<ShowSeat> findAllById(Iterable<Long> showSeatIds);

    @Override
    ShowSeat save(ShowSeat showSeatId);
    // save -> Update+Insert
    // If the ShowSeat object that we are passing is not present in the DB
    // then insert it in the DB
    // If ShowSeat object is present in the DB then update the object in the DB
}
