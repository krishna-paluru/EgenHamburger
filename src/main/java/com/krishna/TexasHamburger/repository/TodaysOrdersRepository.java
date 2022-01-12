package com.krishna.TexasHamburger.repository;

import com.krishna.TexasHamburger.model.TodaysOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodaysOrdersRepository extends JpaRepository<TodaysOrder,Long> {

}
