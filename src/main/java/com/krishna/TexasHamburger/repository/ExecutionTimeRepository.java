package com.krishna.TexasHamburger.repository;

import com.krishna.TexasHamburger.model.ExecutionTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
public interface ExecutionTimeRepository extends JpaRepository<ExecutionTime,Long> {
}
