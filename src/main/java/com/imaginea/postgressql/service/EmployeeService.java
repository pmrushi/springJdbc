package com.imaginea.postgressql.service;

import com.imaginea.postgressql.beans.Employee;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeService {

    @Transactional
    void save(Employee employee);

    @Transactional
    long saveAndReturnKey(Employee employee);
    
    @Transactional(readOnly = true)
    Employee get(long empId);

    @Transactional(readOnly = true)
    List<Employee> list();

    @Transactional
    int[] bulkInsert(final List<Employee> empList);

}
