package com.krishna.TexasHamburger.repository;

import com.krishna.TexasHamburger.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends JpaRepository<Time,Long> {

}
