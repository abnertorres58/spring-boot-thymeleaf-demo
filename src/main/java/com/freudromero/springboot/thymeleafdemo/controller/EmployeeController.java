package com.freudromero.springboot.thymeleafdemo.controller;

import com.freudromero.springboot.thymeleafdemo.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    // Load employee data
    private List<Employee> theEmployees;

    @PostConstruct
    private void loadData() {
        // Create employees
        Employee employee1 = new Employee(1, "Leslie", "Andrews", "leslie@mail.com");
        Employee employee2 = new Employee(2, "Emma", "Baumgarten", "emma@mail.com");
        Employee employee3 = new Employee(3, "Avani", "Gupta", "avani@mail.com");

        // Create the list
        theEmployees = new ArrayList<>();

        // Add to the list
        theEmployees.add(employee1);
        theEmployees.add(employee2);
        theEmployees.add(employee3);
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model theModel){
        // Add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "list-employees";
    }
}
