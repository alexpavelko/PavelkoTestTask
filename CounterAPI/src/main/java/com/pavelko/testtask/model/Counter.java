package com.pavelko.testtask.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="number_data")
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name="num_value", nullable = false)
    private int value;
}
