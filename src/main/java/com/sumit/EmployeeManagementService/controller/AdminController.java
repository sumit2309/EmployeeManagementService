package com.sumit.EmployeeManagementService.controller;

import com.sumit.EmployeeManagementService.entity.Employee;
import com.sumit.EmployeeManagementService.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final EmployeeService employeeService;

    public AdminController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/stats")
    public Flux<Employee> getDailyLoginStats(@RequestParam("date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        return employeeService.getDailyLoginStats(localDate);
    }

}
