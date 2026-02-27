package com.study.Ex1001ExampleCounterDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountRepository extends JpaRepository<CountEntity, Long>{
}
