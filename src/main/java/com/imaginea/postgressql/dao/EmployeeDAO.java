package com.imaginea.postgressql.dao;

import com.imaginea.postgressql.beans.Employee;

import java.util.List;

public interface EmployeeDAO {

    void save(Employee employee);

    long saveAndReturnKey(Employee employee);

    Employee get(long empId);

    List<Employee> list();

    int[] bulkInsert(final List<Employee> empList);
}
