package com.pavelko.testtask.controller;

import com.pavelko.testtask.service.CounterService;
import com.pavelko.testtask.service.ICounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CounterController {
    private final ICounterService counterService;

    @Autowired
    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @PostMapping("counter/{value}")
    public boolean overWriteCounter(@PathVariable int value) {
        return counterService.overWriteCounter(value);
    }

    @GetMapping("counter")
    public Integer getNumber() {
        return counterService.getCounter();
    }
}
