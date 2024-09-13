package com.sumit.EmployeeManagementService.controller;


import com.sumit.EmployeeManagementService.dto.RSAKeyPairResponse;
import com.sumit.EmployeeManagementService.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/login")
    public Mono<String> login(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam Long id,
                              @RequestParam String publicKeyBase64) {
        return employeeService.login(username, password, id, publicKeyBase64);
    }

    @PostMapping("/logout")
    public Mono<String> logout(@RequestParam String username) {
        return employeeService.logout(username);
    }

    @PostMapping("/create")
    public Mono<RSAKeyPairResponse> createEmployee(@RequestParam String username,
                                                   @RequestParam String password) {
        return employeeService.createEmployee(username, password);
    }

    @PostMapping("/delete")
    public Mono<String> deleteEmployee(@RequestParam String username) {
        return employeeService.deleteEmployee(username);
    }
}

