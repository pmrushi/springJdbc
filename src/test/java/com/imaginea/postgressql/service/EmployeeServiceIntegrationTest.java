package com.imaginea.postgressql.service;

import static junit.framework.Assert.assertEquals;

import com.imaginea.postgressql.beans.Employee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class EmployeeServiceIntegrationTest {

    private static final long DEPT_ID = 1L;
    private static final String BIRTH_DATE = "1981-08-19";
 
    @Autowired
    private EmployeeService employeeService;

    @Test
    public void shouldPersistBulkEmployees() {
        List<Employee> empList = new ArrayList<Employee>(3);
        empList.add(createEmployee("fname1", "lname1", BIRTH_DATE, DEPT_ID));
        empList.add(createEmployee("fname2", "lname2", BIRTH_DATE, DEPT_ID));
        empList.add(createEmployee("fname3", "lname3", BIRTH_DATE, DEPT_ID));
        employeeService.bulkInsert(empList);
        List<Employee> employees = employeeService.list();
        int counter = 1;
        assertEquals(3, employees.size());
        for (Employee employee : employees) {
            assertEquals("fname" + counter, employee.getFirstname());
            assertEquals("lname" + counter, employee.getLastname());
            assertEquals(BIRTH_DATE, employee.getBirthDate().toString());
            assertEquals(Long.valueOf(DEPT_ID), employee.getDeptId());
            counter++;
        }
    }

    @Test
    public void shouldPersistEmployee() {
        long key = employeeService.saveAndReturnKey(createEmployee("Scott", "Tiger", BIRTH_DATE, DEPT_ID));
        assertEmployee(employeeService.get(key));
    }

    private void assertEmployee(Employee employee) {
        assertEquals("Scott", employee.getFirstname());
        assertEquals("Tiger", employee.getLastname());
        assertEquals(BIRTH_DATE, employee.getBirthDate().toString());
        assertEquals(Long.valueOf(DEPT_ID), employee.getDeptId());
    }

    private Employee createEmployee(String firstName, String lastName, String birthDate, long deptId) {
        Employee employee = new Employee();
        employee.setFirstname(firstName);
        employee.setLastname(lastName);
        try {
            employee.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(birthDate));
        } catch (ParseException e) {
        }
        employee.setDeptId(deptId);
        return employee;
    }
}