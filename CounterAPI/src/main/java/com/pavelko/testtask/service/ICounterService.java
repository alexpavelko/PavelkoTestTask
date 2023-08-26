package com.pavelko.testtask.service;

public interface ICounterService {
    void deletePreviousCounter();
    boolean overWriteCounter(int value);
    Integer getCounter();
}
