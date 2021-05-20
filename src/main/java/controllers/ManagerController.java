package controllers;

import accounts.Managers;
import com.mongodb.client.MongoCollection;
import daos.MongoDao;
import io.javalin.http.Context;
import org.json.JSONArray;

/**
 * Controller class for manager related methods
 */
public class ManagerController {
    private String currentUser = null;
    private MongoDao dao = new MongoDao();
    private String currEmployee = null;

    /**
     * Checks if the provided credentials match an existing manager account
     * If not do not allow access to any manager pages
     * @param ctx
     */
    public void managerLogin(Context ctx) {
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        if(dao.findManager(username, password)) {
            currentUser = username;
            ctx.redirect("/ManagerHome");
        }

        else {
            ctx.render("/public/ManagerLogin.html");
        }
    }

    /**
     * Method to find if the employee that was entered exists
     * If they do exist, we will be able to load all of their reimbursements
     * @param ctx
     */
    public void employeeSearch(Context ctx) {
        String username = ctx.formParam("username");

        if(dao.getEmployee(username) != null) {
            currEmployee = username;
            ctx.redirect("/Managers/ManageReimbursements/EmployeeReimbursements");
        }

        else {
            ctx.render("/public/ManageReimbursements.html");
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

    public String getCurrEmployee() {
        return currEmployee;
    }
}
