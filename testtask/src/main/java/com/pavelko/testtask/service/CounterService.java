package com.pavelko.testtask.service;

import com.pavelko.testtask.model.NumberEntity;
import com.pavelko.testtask.repository.NumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterService implements ICounterService {
    private final NumberRepository numberRepository;

    @Autowired
    public CounterService(NumberRepository numberRepository) {
        this.numberRepository = numberRepository;
    }

    @Override
    public void deletePreviousCounter() {
        if (!numberRepository.findAll().isEmpty()) {
            numberRepository.deleteAll();
        }
    }

    @Override
    public boolean overWriteCounter(int value) {
        deletePreviousCounter();
        NumberEntity entityToSave = new NumberEntity();
        entityToSave.setValue(value);
        try {
            numberRepository.save(entityToSave);
            return true;
        } catch (IllegalArgumentException iae) {
            return false;
        }
    }

    @Override
    public Integer getCounter() {
        return numberRepository.findAll().get(0).getValue();
    }
}

