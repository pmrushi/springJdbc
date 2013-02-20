package com.imaginea.postgressql.service;

import static org.mockito.Mockito.verify;

import com.imaginea.postgressql.beans.Employee;
import com.imaginea.postgressql.dao.EmployeeDAO;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

    private EmployeeService employeeService;

    @Mock
    private EmployeeDAO employeeDAO;

    @Mock
    private Employee employee;

    @Mock
    private List<Employee> empList;

    @Before
    public void setUp() {
        employeeService = new EmployeeServiceImpl(employeeDAO);
    }

    @Test
    public void saveShouldCallEmployeeDAOSave() {
        employeeService.save(employee);
        verify(employeeDAO).save(employee);
    }

    @Test
    public void saveAndReturnKeyShouldCallEmployeeDAOSaveAndReturnKey() {
        employeeService.saveAndReturnKey(employee);
        verify(employeeDAO).saveAndReturnKey(employee);
    }

    @Test
    public void getShouldCallEmployeeDAOGet() {
        Long empId = 1L;
        employeeService.get(empId);
        verify(employeeDAO).get(empId);
    }

    @Test
    public void listShouldCallEmployeeDAOList() {
        employeeService.list();
        verify(employeeDAO).list();
    }

    @Test
    public void bulkInsertShouldCallEmployeeDAOBulkInsert() {
        employeeService.bulkInsert(empList);
        verify(employeeDAO).bulkInsert(empList);
    }
}