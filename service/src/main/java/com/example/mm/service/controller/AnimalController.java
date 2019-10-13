package com.example.mm.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mm.client.AnimalClient;
import com.example.mm.common.entity.Animal;

/**
 * Created by rviner on 12.10.2019.
 */
@RestController
@RequestMapping("/animals")
public class AnimalController implements AnimalClient {

    final List<Animal> animals = new ArrayList<>();

    public AnimalController() {
        Animal animal = new Animal();
        animal.setName("test");
        this.animals.add(animal);
    }

    @GetMapping("{name}")
    @Override
    public Animal getAnimal(@PathVariable("name") String name) {
        return animals.stream()
            .filter(a -> a.getName().equalsIgnoreCase(name))
            .findAny()
            .orElse(null);
    }

    @GetMapping
    @Override
    public List<Animal> getAllAnimals() {
        return animals;
    }

    @PutMapping
    @Override
    public void putAnimal(@RequestBody Animal animal) {
        animals.add(animal);
    }

    @PostMapping
    @Override
    public void updateAnimal(@RequestBody Animal animal) {
        animals.stream()
            .filter(a -> a.getName().equalsIgnoreCase(animal.getName()))
            .findAny()
            .ifPresent(a -> {
                animals.remove(a);
                animals.add(animal);
            });
    }

    @DeleteMapping("{name}")
    @Override
    public void deleteAnimal(@PathVariable String name) {
        animals.stream()
            .filter(a -> a.getName().equalsIgnoreCase(name))
            .findAny()
            .ifPresent(a -> {
                animals.remove(a);
            });
    }
}
