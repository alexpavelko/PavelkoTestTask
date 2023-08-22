package com.pavelko.testtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;
import com.pavelko.testtask.model.NumberEntity;
import com.pavelko.testtask.repository.NumberRepository;

import java.util.Optional;



@RestController
@RequestMapping("/numbers")
public class NumberController {

    private final NumberRepository numberRepository;

    @Autowired
    public NumberController(NumberRepository numberRepository) {
        this.numberRepository = numberRepository;
    }

    @PostMapping
    public NumberEntity saveNumber(@RequestBody int value) {
        NumberEntity number = new NumberEntity();
        number.setValue(value);
        return numberRepository.save(number);
    }

    @GetMapping("/{id}")
    public Optional<NumberEntity> getNumber(@PathVariable Long id) {
        return numberRepository.findById(id);
    }
}
