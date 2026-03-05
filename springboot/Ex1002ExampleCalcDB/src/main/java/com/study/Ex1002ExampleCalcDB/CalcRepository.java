package com.study.Ex1002ExampleCalcDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalcRepository extends JpaRepository<CalcEntity, Long> {
}
