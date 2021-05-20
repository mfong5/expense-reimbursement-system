package daos;

import accounts.Employees;
import accounts.Managers;
import accounts.Reimbursements;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;

import com.mongodb.client.model.Filters;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import org.apache.logging.log4j.LogManager;
import java.util.List;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * It's the Mongo Dao
 */
public class MongoDao implements Dao {
    org.apache.logging.log4j.Logger rootLogger = LogManager.getRootLogger();
    MongoCollection<Employees> employees;
    MongoCollection<Managers> managers;
    MongoCollection<Reimbursements> reimbursements;

    public MongoDao() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/reimbursementSystem");
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .retryWrites(true)
                .codecRegistry(codecRegistry)
                .build();
        MongoClient client = MongoClients.create(clientSettings);
        MongoDatabase db = client.getDatabase("reimbursementSystem");
        this.employees = db.getCollection("employees", Employees.class);
        this.managers = db.getCollection("managers", Managers.class);
        this.reimbursements = db.getCollection("reimbursements", Reimbursements.class);
    }

    public MongoCollection<Employees> getEmployees() {
        return employees;
    }

    public MongoCollection<Managers> getManagers() {
        return managers;
    }

    public MongoCollection<Reimbursements> getReimbursements() {
        return reimbursements;
    }

    public boolean findEmployee(String username, String password) {
        Employees targetAccount = employees.find(and(eq("username", username), eq("password", password))).first();
        if (targetAccount == null) return false;
        else {
            rootLogger.info("Employee ({}) has logged in.", username);
            return true;
        }
    }

    public Employees getEmployee(String username) {
        return employees.find(eq("username", username)).first();
    }

    public void updateEmployeePassword(String username, String password) {
        Employees updatedEmployee = new Employees(username, password);
        employees.findOneAndReplace(eq("username", username), updatedEmployee);
        rootLogger.info("Employee ({}) has changed their password.", username);
    }

    public boolean findManager(String username, String password) {
        Managers targetAccount = managers.find(and(eq("username", username), eq("password", password))).first();
        if (targetAccount == null) return false;
        else {
            rootLogger.info("Manager ({}) has logged in.", username);
            return true;
        }
    }

    public void createReimbursement(String username, String reason, double expense) {
        Reimbursements newReimbursement = new Reimbursements(username, reason, expense);
        reimbursements.insertOne(newReimbursement);
        rootLogger.info("Employee ({}) has created a new reimbursement request.", username);
    }

    public Reimbursements findReimbursement(String username, String reason, double expense) {
        Reimbursements targetReimbursement = reimbursements.find(and(eq("employee", username), eq("reason", reason),
        eq("expense", expense))).first();
        return targetReimbursement;
    }

    public void updateReimbursement(Reimbursements reimbursement) {
        reimbursements.findOneAndReplace(Filters.and(eq("employee", reimbursement.getEmployee()), eq("reason", reimbursement.getReason()),
        eq("expense", reimbursement.getExpense()), eq("status", "Pending")), reimbursement);
        rootLogger.info("Reimbursement request from employee ({}) has been resolved.", reimbursement.getEmployee());
    }

    public FindIterable<Reimbursements> getEmployeeReimbursements(String username) {
        return reimbursements.find(eq("employee", username));
    }

    public FindIterable<Reimbursements> getAllReimbursements() {
        return reimbursements.find();
    }

    /* public void createManager() {
        Managers newAccount = new Managers("Michael", "Fong");
        Employees newEmployee = new Employees("User", "Password");
        Employees newEmployee2 = new Employees("User1", "Password");
        managers.insertOne(newAccount);
        employees.insertOne(newEmployee);
        employees.insertOne(newEmployee2);
    }*/
}
