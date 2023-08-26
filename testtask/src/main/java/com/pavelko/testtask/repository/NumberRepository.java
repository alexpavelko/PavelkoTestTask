package com.pavelko.testtask.repository;


import com.pavelko.testtask.model.NumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumberRepository extends JpaRepository<NumberEntity, Integer> {
}
