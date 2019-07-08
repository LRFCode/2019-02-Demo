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
}






