package com.example.mm.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.example.mm.common.entity.Animal;

import static java.lang.String.format;

/**
 * Created by rviner on 12.10.2019.
 */
public class RTAnimalClient implements AnimalClient {

    final RestTemplate restTemplate;
    final String url;

    public RTAnimalClient(final String baseUrl) {
        this.url = format("%s/animals", baseUrl);
        this.restTemplate = new RestTemplate();
    }

    public Animal getAnimal(String name) {
        final String url = format("%s/%s", this.url, name);

        return restTemplate.getForObject(url, Animal.class);
    }

    public List<Animal> getAllAnimals() {
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Animal>>(){})
            .getBody();
    }

    public void putAnimal(Animal animal) {
        restTemplate.put(url, animal);
    }

    public void updateAnimal(Animal animal) {
        restTemplate.postForObject(url, animal, Animal.class);
    }

    public void deleteAnimal(String name) {
        final String url = format("%s/%s", this.url, name);

        restTemplate.delete(url);
    }
}
