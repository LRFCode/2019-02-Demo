package controllers;

import models.Employee;
import models.EmployeeRepository;
import models.Product;
import models.ProductRepository;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class EmployeeController extends Controller
{
    private FormFactory formFactory;
    private EmployeeRepository employeeRepository;

    @Inject
    public EmployeeController(FormFactory formFactory, EmployeeRepository employeeRepository)
    {
        this.formFactory = formFactory;
        this.employeeRepository = employeeRepository;
    }

    @Transactional(readOnly = true)
    public Result getList()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String searchName = form.get("searchName");

        List<Employee> employees = employeeRepository.getList(searchName);

        return ok(views.html.Employees.render(employees));
    }

    @Transactional(readOnly = true)
    public Result getEditEmployee(int employeeid)
    {
        Employee employee = employeeRepository.get(employeeid);
        return ok(views.html.EditEmployee.render(employee));
    }

}
