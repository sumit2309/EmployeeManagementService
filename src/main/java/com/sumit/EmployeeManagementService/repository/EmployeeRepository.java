package com.sumit.EmployeeManagementService.repository;


import com.sumit.EmployeeManagementService.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUsername(String username);
    List<Employee> findByLoginTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

}
