package com.imaginea.postgressql.service;

import com.imaginea.postgressql.beans.Employee;
import com.imaginea.postgressql.dao.EmployeeDAO;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void save(Employee employee) {
        employeeDAO.save(employee);
    }

    public long saveAndReturnKey(Employee employee) {
        return employeeDAO.saveAndReturnKey(employee);
    }

    public Employee get(long empId) {
        return employeeDAO.get(empId);
    }

    public List<Employee> list() {
        return employeeDAO.list();
    }

    public int[] bulkInsert(List<Employee> empList) {
        return employeeDAO.bulkInsert(empList);
    }
}
