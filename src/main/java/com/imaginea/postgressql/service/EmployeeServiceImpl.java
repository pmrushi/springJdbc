package com.imaginea.postgressql.service;

import com.imaginea.postgressql.beans.Employee;
import com.imaginea.postgressql.dao.EmployeeDAO;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public void save(Employee employee) {
        employeeDAO.save(employee);
    }

    @Override
    public long saveAndReturnKey(Employee employee) {
        return employeeDAO.saveAndReturnKey(employee);
    }

    @Override
    public Employee get(long empId) {
        return employeeDAO.get(empId);
    }

    @Override
    public List<Employee> list() {
        return employeeDAO.list();
    }

    @Override
    public int[] bulkInsert(List<Employee> empList) {
        return employeeDAO.bulkInsert(empList);
    }
}
