package com.imaginea.postgressql.dao;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import com.imaginea.postgressql.beans.Employee;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeDAOImplTest {

    private EmployeeDAOImpl employeeDAOImpl;

    @Mock
    private Employee employee;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        employeeDAOImpl = spy(new EmployeeDAOImpl());
        ReflectionTestUtils.setField(employeeDAOImpl, "jdbcTemplate", jdbcTemplate);
    }

    @Test
    public void shouldSaveEmployee() {
        employeeDAOImpl.save(employee);
        verify(jdbcTemplate).update(anyString(), anyObject(), anyObject(), anyObject(), anyLong());
    }

    @Test
    public void shouldSaveAndReturnKeyEmployee() {
        employeeDAOImpl.saveAndReturnKey(employee);
        verify(jdbcTemplate).update((PreparedStatementCreator) anyObject(), (KeyHolder) anyObject());
    }

    @Test
    public void shouldGetEmployee() {
        employeeDAOImpl.get(2L);
        verify(jdbcTemplate).queryForObject(anyString(), (Object[]) anyObject(), (RowMapper<Employee>) anyObject());
    }

    @Test
    public void shouldListEmployees() {
        employeeDAOImpl.list();
        verify(jdbcTemplate).query(anyString(), (RowMapper) anyObject());
    }

    @Test
    public void shouldBulkInsertEmployees() {
        List<Employee> empList = new ArrayList<Employee>(2);
        empList.add(employee);
        employeeDAOImpl.bulkInsert(empList);
        verify(jdbcTemplate).batchUpdate(anyString(), (List<Object[]>) anyObject());
    }
}
