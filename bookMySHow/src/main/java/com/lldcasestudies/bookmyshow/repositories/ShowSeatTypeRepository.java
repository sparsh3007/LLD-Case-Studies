package com.lldcasestudies.bookmyshow.repositories;

import com.lldcasestudies.bookmyshow.models.Show;
import com.lldcasestudies.bookmyshow.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType,Long> {
    List<ShowSeatType> findAllByShow(Show show);
}
