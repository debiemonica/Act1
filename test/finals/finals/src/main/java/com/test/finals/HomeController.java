package com.test.finals;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final List<Coffee> coffeeList = new ArrayList<>();

    public HomeController() {
        coffeeList.add(new Coffee(1, "Espresso", "Arabica", "Small", 3.50, "Dark", "Ethiopia", false, 10, Arrays.asList("Chocolate", "Nutty"), "Espresso"));
        coffeeList.add(new Coffee(2, "Latte", "Arabica", "Medium", 4.50, "Medium", "Brazil", false, 8, Arrays.asList("Creamy", "Sweet"), "Drip"));
        coffeeList.add(new Coffee(3, "Cappuccino", "Robusta", "Large", 5.00, "Medium", "Colombia", false, 12, Arrays.asList("Fruity", "Bold"), "French Press"));
        coffeeList.add(new Coffee(4, "Mocha", "Arabica", "Medium", 4.75, "Dark", "Guatemala", false, 6, Arrays.asList("Chocolate", "Smooth"), "Espresso"));
        coffeeList.add(new Coffee(5, "Americano", "Robusta", "Large", 3.25, "Light", "Kenya", false, 15, Arrays.asList("Citrus", "Balanced"), "Drip"));
    }

    // Display all coffees or search by name
    @GetMapping("/")
    public String getCoffees(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Coffee> filteredCoffees = coffeeList;

        if (search != null && !search.isEmpty()) {
            filteredCoffees = coffeeList.stream()
                    .filter(coffee -> coffee.getName().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }

        model.addAttribute("coffees", filteredCoffees);
        model.addAttribute("search", search);
        return "index"; // Thymeleaf template for displaying the list
    }

    // Display form for adding a new coffee
    @GetMapping("/add")
    public String addCoffee(Model model) {
        model.addAttribute("coffee", new Coffee(0, "", "", "", 0.0, "", "", false, 0, Arrays.asList(), "")); // Empty object for the form
        return "new"; // Thymeleaf template for adding a new coffee
    }

    // Save a new coffee
    @PostMapping("/save")
    public String saveCoffee(@ModelAttribute Coffee coffee) {Long newId = coffeeList.size() > 0
            ? coffeeList.get(coffeeList.size() - 1).getId() + 1
            : 1L; // Note the `L` to indicate a long literal

        coffee.setId(newId);

        return "redirect:/"; // Redirect to the list after saving
    }

    // Display form for editing an existing coffee
    @GetMapping("/edit")
    public String editCoffee(@RequestParam int id, Model model) {
        // implementation for editing coffee
        return "edit";
    }
}