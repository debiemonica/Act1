package com.test.finals;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CoffeeController {

    private final CoffeeRepository coffeeRepository;

    @Autowired // Optional in recent Spring Boot if only one constructor
    public CoffeeController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @GetMapping("/coffees")
    public String listCoffees(Model model) {
        model.addAttribute("coffees", coffeeRepository.findAll());
        return "coffee"; // Make sure your template is named coffee.html
    }

    @GetMapping("/coffees/new")
    public String showAddCoffeeForm(Model model) {
        model.addAttribute("coffee", new Coffee());
        return "addCoffee"; // Make sure your template is named addCoffee.html
    }

    @PostMapping("/coffees")
    public String addCoffee(@ModelAttribute Coffee coffee) {
        coffeeRepository.save(coffee);
        return "redirect:/coffees";
    }

    @GetMapping("/coffees/delete/{id}")
    public String deleteCoffee(@PathVariable Long id) {
        coffeeRepository.delete(id);
        return "redirect:/coffees";
    }
}
