package com.test.finals;

import java.util.*;
import org.springframework.stereotype.Repository;

@Repository
public class CoffeeRepository {

    private Map<Long, Coffee> coffees = new HashMap<>();
    private long nextId = 1;

    public List<Coffee> findAll() {
        return new ArrayList<>(coffees.values());
    }

    public Coffee save(Coffee coffee) {
        if (coffee.getId() == null) {
            coffee.setId(nextId++);
        }
        coffees.put(coffee.getId(), coffee);
        return coffee;
    }

    public void delete(Long id) {
        coffees.remove(id);
    }

    public Optional<Coffee> findById(Long id) {
        return Optional.ofNullable(coffees.get(id));
    }
}
