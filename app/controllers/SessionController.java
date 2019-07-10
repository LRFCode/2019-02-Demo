package controllers;

import models.Employee;
import models.EmployeeRepository;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class SessionController extends Controller
{
    private FormFactory formFactory;
    private EmployeeRepository employeeRepository;

    @Inject
    public SessionController(FormFactory formFactory, EmployeeRepository employeeRepository)
    {
        this.formFactory = formFactory;
        this.employeeRepository = employeeRepository;
    }

    public Result getLogin()
    {
        return ok(views.html.Login.render());
    }

    @Transactional(readOnly = true)
    public Result postLogin()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String username = form.get("username");
        String password = form.get("password");

        Employee employee = employeeRepository.get(username, password);

        if (employee == null)
        {
            return ok("FAIL!");
        }
        else
        {
            return ok("Welcome " + employee.getFirstName());
        }
    }
}
