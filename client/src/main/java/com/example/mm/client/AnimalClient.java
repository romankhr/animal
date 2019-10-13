package com.example.mm.client;

import java.util.List;

import com.example.mm.common.entity.Animal;

/**
 * Created by rviner on 12.10.2019.
 */
public interface AnimalClient {

    Animal getAnimal(String name);

    List<Animal> getAllAnimals();

    void putAnimal(Animal animal);

    void updateAnimal(Animal animal);

    void deleteAnimal(String name);
}
