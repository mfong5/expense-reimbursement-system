package controllers;

import accounts.Reimbursements;
import com.mongodb.client.FindIterable;
import daos.MongoDao;
import io.javalin.http.Context;
import org.json.JSONArray;

/**
 * Controller class for all the reimbursement related methods
 */
public class ReimbursementController {
    private EmployeeController employeeController;
    private ManagerController managerController;
    private MongoDao dao = new MongoDao();

    public ReimbursementController(EmployeeController employeeController, ManagerController managerController) {
        this.employeeController = employeeController;
        this.managerController = managerController;
    }

    /**
     * Makes a new reimbursement and adds it to the Mongo database
     * @param ctx
     */
    public void createReimbursement(Context ctx) {
        String username = employeeController.getCurrentUser();
        String expense = ctx.formParam("expense");
        String reason = ctx.formParam("reason");
        double cost;
        cost = Double.valueOf(expense);

        dao.createReimbursement(username, reason, cost);
        ctx.redirect("/EmployeeHome");
    }

    /**
     * Grabs a list of all the reimbursements for a certain employee in the context
     * of an employee wishing to see their reimbursements
     * @param ctx
     */
    public void getEmployeeReimbursements(Context ctx) {
        FindIterable<Reimbursements> reimbursements = dao.getEmployeeReimbursements(employeeController.getCurrentUser());
        JSONArray response = new JSONArray();
        for (Reimbursements reimbursement : reimbursements) {
            response.put(reimbursement);
        }
        ctx.json(response);
    }

    /**
     * Grabs a list of all the reimbursements for a certain employee in the context
     * of a manager wishing to see all reimbursements related to a particular employee
     * @param ctx
     */
    public void managerGetEmployeeReimbursements(Context ctx) {
        FindIterable<Reimbursements> reimbursements = dao.getEmployeeReimbursements(managerController.getCurrEmployee());
        JSONArray response = new JSONArray();
        for (Reimbursements reimbursement : reimbursements) {
            response.put(reimbursement);
        }
        ctx.json(response);
    }

    /**
     * Updates a reimbursement to be either approved or denied by a certain manager
     * @param ctx
     */
    public void updateReimbursement(Context ctx) {
        String username = ctx.formParam("username");
        String expense = ctx.formParam("expense");
        String reason = ctx.formParam("reason");
        String status = ctx.formParam("status");
        double cost;

        cost = Double.valueOf(expense);

        Reimbursements targetReimbursement = dao.findReimbursement(username, reason, cost);

        if (status.equals("Approved")) {
            targetReimbursement.approveRequest(managerController.getCurrentUser());
        }
        else if (status.equals("Denied")) {
            targetReimbursement.denyRequest(managerController.getCurrentUser());
        }

        dao.updateReimbursement(targetReimbursement);
        ctx.redirect("/Managers/ManageReimbursements/EmployeeReimbursements");
    }

    /**
     * Grabs the list of all reimbursements
     * @param ctx
     */
    public void managerGetAllReimbursements(Context ctx) {
        FindIterable<Reimbursements> reimbursements = dao.getAllReimbursements();
        JSONArray response = new JSONArray();
        for (Reimbursements reimbursement : reimbursements) {
            response.put(reimbursement);
        }
        ctx.json(response);
    }

    /**
     * Updates a reimbursement to be either approved or denied by a certain manager
     * @param ctx
     */
    public void updateReimbursementAll(Context ctx) {
        String username = ctx.formParam("username");
        String expense = ctx.formParam("expense");
        String reason = ctx.formParam("reason");
        String status = ctx.formParam("status");
        double cost;

        cost = Double.valueOf(expense);

        Reimbursements targetReimbursement = dao.findReimbursement(username, reason, cost);

        if (status.equals("Approved")) {
            targetReimbursement.approveRequest(managerController.getCurrentUser());
        }
        else if (status.equals("Denied")) {
            targetReimbursement.denyRequest(managerController.getCurrentUser());
        }

        dao.updateReimbursement(targetReimbursement);
        ctx.redirect("/Managers/ManageReimbursements/AllReimbursements");
    }
}
