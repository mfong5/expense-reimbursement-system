import io.javalin.Javalin;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Main {
    public static void main(String args[]) {
        BasicConfigurator.configure();

        Javalin javalinApp = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            config.addStaticFiles("/public");
        }).start(7777);

        Application app = new Application(javalinApp);

        app.run();
    }
}
