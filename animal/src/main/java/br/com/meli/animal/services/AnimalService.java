package br.com.meli.animal.services;

import br.com.meli.animal.entities.Animal;
import br.com.meli.animal.repositories.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository repository;

    public Animal create(final String name, final Integer age, final String color) {

        Animal animal = new Animal();

        animal.setName(name);
        animal.setAge(age);
        animal.setColor(color);

        return repository.save(animal);

    }

    public Animal findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Animal not found!"));
    }

    public Animal update(Integer id, Animal updatedAnimal) {
        Animal existingAnimal = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal not found"));

        existingAnimal.setName(updatedAnimal.getName());
        existingAnimal.setAge(updatedAnimal.getAge());
        existingAnimal.setColor(updatedAnimal.getColor());

        return repository.save(existingAnimal);
    }

    public void delete(Integer id) {
        Animal animal = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal not found!"));

        repository.delete(animal);
    }

    public List<Animal> findAll() {
        return repository.findAll();
    }
}
