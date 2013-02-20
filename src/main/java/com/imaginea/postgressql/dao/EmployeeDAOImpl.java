package com.imaginea.postgressql.dao;

import com.imaginea.postgressql.beans.Employee;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl extends JdbcDaoSupport implements EmployeeDAO {

    private static final String INSERT_QUERY = "INSERT INTO employee(firstname, lastname, birth_date, dept_id) VALUES (?,?,?,?)";
    private static final String EMP_LIST_QUERY = "SELECT emp_id,firstname, lastname, birth_date, dept_id FROM employee";

    public void save(Employee employee) {
        getJdbcTemplate().update(INSERT_QUERY,
                new Object[]{employee.getFirstname(), employee.getLastname(), employee.getBirthDate(), employee.getDeptId()});
    }

    public long saveAndReturnKey(final Employee employee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(INSERT_QUERY, new String[]{"emp_id"});
                preparedStatement.setString(1, employee.getFirstname());
                preparedStatement.setString(2, employee.getLastname());
                preparedStatement.setDate(3, new Date(employee.getBirthDate().getTime()));
                preparedStatement.setLong(4, employee.getDeptId());
                return preparedStatement;
            }
        }, keyHolder);
        Number key = keyHolder.getKey();
        return (key != null) ? key.longValue() : 0;
    }

    public Employee get(long empId) {
        String query = EMP_LIST_QUERY + " WHERE emp_id=?";
        return (Employee) getJdbcTemplate().queryForObject(query, new Object[]{empId}, new EmpRowMapper());
    }

    public List<Employee> list() {
        return getJdbcTemplate().query(EMP_LIST_QUERY, new EmpRowMapper());
    }

    public int[] bulkInsert(final List<Employee> empList) {
        List<Object[]> employeeBatch = new ArrayList<Object[]>();
        for (Employee employee : empList) {
            Object[] values = new Object[]{employee.getFirstname(), employee.getLastname(), employee.getBirthDate(), employee.getDeptId()};
            employeeBatch.add(values);
        }
        return getJdbcTemplate().batchUpdate(INSERT_QUERY, employeeBatch);
    }

    private class EmpRowMapper implements RowMapper<Employee> {
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            return mapEmployee(rs);
        }

        private Employee mapEmployee(ResultSet rs) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getLong("emp_id"));
            employee.setFirstname(rs.getString("firstname"));
            employee.setLastname(rs.getString("lastname"));
            employee.setBirthDate(rs.getDate("birth_date"));
            employee.setDeptId(rs.getLong("dept_id"));
            return employee;
        }
    }
}