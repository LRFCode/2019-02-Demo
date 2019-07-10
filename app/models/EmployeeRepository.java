package models;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeRepository
{
    private JPAApi jpaApi;

    @Inject
    public EmployeeRepository(JPAApi jpaApi)
    {
        this.jpaApi = jpaApi;
    }

    public List<Employee> getList(String searchName)
    {
        String searchValue = searchName;

        if (searchValue == null)
        {
            searchValue = "";
        }

        if (searchValue.length() <= 1)
        {
            searchValue = searchValue + "%";
        }
        else
        {
            searchValue = "%" + searchValue + "%";
        }

        String sql = "SELECT e " +
                "FROM Employee e " +
                // "WHERE e.firstName || e.lastName LIKE :searchName " +
                "WHERE e.firstName LIKE :searchName OR e.lastName LIKE :searchName " +
                "ORDER BY e.lastName, e.firstName";
        TypedQuery<Employee> query = jpaApi.em().createQuery(sql, Employee.class);
        query.setParameter("searchName", searchValue);
        List<Employee> employees = query.getResultList();
        return employees;
    }

    public Employee get(int employeeId)
    {
        String sql = "SELECT e FROM Employee e WHERE employeeId = :employeeId";
        TypedQuery<Employee> query = jpaApi.em().createQuery(sql, Employee.class);
        query.setParameter("employeeId", employeeId);
        return query.getSingleResult();
    }

    public Employee get(String username, String password)
    {
        //Example of SQL Injection Problem!!!
        //String sql = "SELECT e FROM Employee e WHERE email = '" + username + "' AND password = '" + password + "'";
        String sql = "SELECT e FROM Employee e WHERE email = :username AND password = :password";

        System.out.println(sql);
        TypedQuery<Employee> query = jpaApi.em().createQuery(sql, Employee.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Employee> employees = query.getResultList();

        if (employees.size() == 0)
        {
            return null;
        }
        else
        {
            return employees.get(0);
        }
    }
}






