package com.pavelko.testtask.repository;


import com.pavelko.testtask.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterRepository extends JpaRepository<Counter, Integer> {
}
