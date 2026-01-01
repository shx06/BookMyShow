package com.example.bookmyshow.repos;

import com.example.bookmyshow.models.Show;
import com.example.bookmyshow.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatTypeRepo extends JpaRepository<ShowSeatType, Long> {

    List<ShowSeatType> findByShow(Show show);
}
