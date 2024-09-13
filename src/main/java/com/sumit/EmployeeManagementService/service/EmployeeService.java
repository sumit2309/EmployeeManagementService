package com.sumit.EmployeeManagementService.service;


import com.sumit.EmployeeManagementService.dto.RSAKeyPairResponse;
import com.sumit.EmployeeManagementService.entity.Employee;
import com.sumit.EmployeeManagementService.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final WebClient webClient;

    @Value("${auth.service.url}")
    private String authServiceUrl;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.webClient = WebClient.builder().baseUrl("http://localhost:9000").build();
    }

    public Mono<RSAKeyPairResponse> generateRSAKeyPair() {
        return webClient.post()
                .uri("/rsa/generate")
                .retrieve()
                .bodyToMono(RSAKeyPairResponse.class);
    }

    public Mono<String> login(String username, String password, Long id, String publicKeyBase64) {
        return validatePublicKey(id, publicKeyBase64)
                .flatMap(isValid -> {
                    if (!isValid) {
                        return Mono.just("Invalid public key");
                    }
                    Employee employee = employeeRepository.findByUsername(username);
                    if (employee != null && employee.getPassword().equals(password)) {
                        employee.setLoginTime(LocalDateTime.now());
                        employeeRepository.save(employee);
                        return Mono.just("Login successful");
                    }
                    return Mono.just("Invalid username or password");
                });
    }

    public Mono<String> logout(String username) {
        Employee employee = employeeRepository.findByUsername(username);
        if (employee != null) {
            employee.setLogoutTime(LocalDateTime.now());
            employeeRepository.save(employee);
            return Mono.just("Logout successful");
        }
        return Mono.just("User not found");
    }

    public Mono<RSAKeyPairResponse> createEmployee(String username, String password) {
        return generateRSAKeyPair()
                .flatMap(rsaKeyPairResponse -> {
                    Long rsaKeyId = rsaKeyPairResponse.getId();
                    Employee employee = new Employee(username, password, rsaKeyId);
                    employeeRepository.save(employee);
                    return Mono.just(rsaKeyPairResponse);
                });
    }

    public Mono<String> deleteEmployee(String username) {
        Employee employee = employeeRepository.findByUsername(username);
        if (employee != null) {
            employeeRepository.delete(employee);
            return Mono.just("Employee deleted");
        }
        return Mono.just("Employee not found");
    }

    private Mono<Boolean> validatePublicKey(Long id, String publicKeyBase64) {
        return webClient.post()
                .uri("/rsa/validate/{id}", id)
                .bodyValue(publicKeyBase64)
                .retrieve()
                .bodyToMono(Boolean.class);
    }

    public Flux<Employee> getDailyLoginStats(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);
        List<Employee> employees = employeeRepository.findByLoginTimeBetween(startOfDay, endOfDay);
        return Flux.fromIterable(employees);
    }
}

