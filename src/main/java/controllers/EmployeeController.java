package controllers;

import accounts.Employees;
import com.mongodb.client.MongoCollection;
import daos.MongoDao;
import io.javalin.http.Context;
import org.json.JSONArray;

/**
 * Class for Employee related methods to be done by the pages
 */
public class EmployeeController {
    private String currentUser = null;
    private MongoDao dao = new MongoDao();

    /**
     * Checks if the employee credentials match an existing employee account
     * If not, do not allow access to employee home page
     * @param ctx
     */
    public void employeeLogin(Context ctx) {
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        if(dao.findEmployee(username, password)) {
            currentUser = username;
            ctx.redirect("/EmployeeHome");

        }

        else {
            ctx.render("/public/EmployeeLogin.html");
        }
    }

    public void logOut() {
        currentUser = null;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String user) {
        this.currentUser = user;
    }

    public void getEmployeeInformation(Context ctx) {
        Employees currentUser = dao.getEmployee(this.currentUser);
        ctx.json(currentUser);
    }

    /**
     * Method to change the password of the currently logged in employee
     * @param ctx
     */
    public void changePassword(Context ctx) {
        String newPassword = ctx.formParam("updatePassword");
        dao.updateEmployeePassword(currentUser, newPassword);
        System.out.println(newPassword);
        ctx.redirect("/EmployeeHome");
    }

    /**
     * Returns a JSONArray of all the employees
     * @param ctx
     */
    public void getAllEmployees(Context ctx) {
        MongoCollection<Employees> employees = dao.getEmployees();
        JSONArray response = new JSONArray();
        for (Employees employee : employees.find()) {
            response.put(employee);
        }
        ctx.json(response);
    }
}
