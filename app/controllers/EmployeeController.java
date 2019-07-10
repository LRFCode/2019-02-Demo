package controllers;

import com.google.common.io.Files;
import models.*;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.File;
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

    @Transactional
    public Result postEditEmployee(int employeeId)
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String firstName = form.get("firstName");

        Employee employee = employeeRepository.get(employeeId);
        employee.setFirstName(firstName);

        Http.MultipartFormData<File> formData = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> filePart = formData.getFile("picture");
        File file = filePart.getFile();

        try
        {
            byte[] picture = Files.toByteArray(file);

            if (picture != null && picture.length > 0)
            {
                employee.setPicture(picture);
            }
        }
        catch (Exception exception)
        {
            //TODO inform user of problem
        }

        return redirect(routes.EmployeeController.getEditEmployee(employeeId));
    }

    @Transactional(readOnly = true)
    public Result getPicture(int employeeId)
    {
        Employee employee = employeeRepository.get(employeeId);
        return ok(employee.getPicture()).as("image/jpeg");
    }

}
