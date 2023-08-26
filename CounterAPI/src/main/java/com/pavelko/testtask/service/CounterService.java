package com.pavelko.testtask.service;

import com.pavelko.testtask.model.Counter;
import com.pavelko.testtask.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterService implements ICounterService {
    private final CounterRepository counterRepository;

    @Autowired
    public CounterService(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Override
    public void deletePreviousCounter() {
        if (!counterRepository.findAll().isEmpty()) {
            counterRepository.deleteAll();
        }
    }

    @Override
    public boolean overWriteCounter(int value) {
        deletePreviousCounter();
        Counter entityToSave = new Counter();
        entityToSave.setValue(value);
        try {
            counterRepository.save(entityToSave);
            return true;
        } catch (IllegalArgumentException iae) {
            return false;
        }
    }

    @Override
    public Integer getCounter() {
        return counterRepository.findAll().get(0).getValue();
    }
}

