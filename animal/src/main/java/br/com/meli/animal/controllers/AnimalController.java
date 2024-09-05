package br.com.meli.animal.controllers;

import br.com.meli.animal.entities.Animal;
import br.com.meli.animal.services.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService service;

    @PostMapping(value = "/animals/create")
    public ResponseEntity<Animal> create(@RequestBody Animal animal) {
        Animal createdAnimal = service.create(animal.getName(), animal.getAge(), animal.getColor());

        return ResponseEntity.ok(createdAnimal);
    }

    @GetMapping("/animals/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Integer id) {
        try {
            Animal animal = service.findById(id);
            return ResponseEntity.ok(animal);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/animals/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable Integer id, @RequestBody Animal updatedAnimal) {
        try {
            Animal animal = service.update(id, updatedAnimal);
            return ResponseEntity.ok(animal);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/animals/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/animals")
    public ResponseEntity<List<Animal>> getAllAnimals() {
        List<Animal> animals = service.findAll();
        return ResponseEntity.ok(animals);
    }

}
