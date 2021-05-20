import accounts.Employees;
import accounts.Managers;
import accounts.Reimbursements;
import com.mongodb.client.MongoCollection;
import daos.MongoDao;
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MongoTests {
    private MongoDao dao = new MongoDao();

    @Test
    public void testCollections() {
        MongoCollection<Employees> testEmployees = dao.getEmployees();
        MongoCollection<Managers> testManagers = dao.getManagers();
        MongoCollection<Reimbursements> testReimbursements = dao.getReimbursements();
    }

    @Test
    public void testFindEmployee() {
        boolean test = dao.findEmployee("User", "Password");
        Assertions.assertEquals(true, test);
    }

    @Test
    public void testFindManager() {
        boolean test = dao.findManager("Michael", "Fong");
        Assertions.assertEquals(true, test);
    }

    @Test
    public void testFindReimbursement() {
        Reimbursements testReimbursement = dao.findReimbursement("User", "Trip", 100.0);
        Assertions.assertNotEquals(null, testReimbursement);
    }
}
