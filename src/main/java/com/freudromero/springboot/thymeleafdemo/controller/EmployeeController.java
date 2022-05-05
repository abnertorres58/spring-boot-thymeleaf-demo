package com.freudromero.springboot.thymeleafdemo.controller;

import com.freudromero.springboot.thymeleafdemo.entity.Employee;
import com.freudromero.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService theEmployeeService) {
        this.employeeService = theEmployeeService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model theModel){

        // Get employees from the db
        List<Employee> theEmployees = employeeService.findAll();

        // Add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // Create the model attribute to bind form data
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {

        // Get the employee from the service
        Employee theEmployee = employeeService.findById(theId);

        // Set employee as a model attribute to pre-populate the form
        theModel.addAttribute("employee", theEmployee);

        // Send over our form
        return "employees/employee-form";

    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

        // Save the employee
        employeeService.save(theEmployee);

        // Use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId) {

        // Delete the Employee
        employeeService.deleteById(theId);

        // Redirect to /employees/list
        return "redirect:/employees/list";
    }
}
