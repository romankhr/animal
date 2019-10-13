package com.example.mm.service.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.mm.client.AnimalClient;
import com.example.mm.client.RTAnimalClient;
import com.example.mm.common.entity.Animal;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by rviner on 12.10.2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnimalControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getTest() throws Exception {
        AnimalClient animalClient = new RTAnimalClient("http://localhost:" + port + "/");

        Animal animal = animalClient.getAnimal("test");

        assertNotNull(animal);
        assertEquals("test", animal.getName());
    }
}
