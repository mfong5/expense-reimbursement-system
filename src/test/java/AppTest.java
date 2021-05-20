import io.javalin.Javalin;
import org.junit.*;
import org.junit.jupiter.api.Test;


public class AppTest {
    static Javalin javalin = Javalin.create(config -> {}).start(7000);
    static Application app = new Application(javalin);

    @Test
    public void runApp() {
        app.run();
    }

    @Test
    public void stopApp() {
        app.stop();
    }
}
