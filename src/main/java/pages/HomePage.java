package pages;

import controllers.LoggedInController;
import io.javalin.Javalin;

import java.util.HashMap;

/**
 * Class for the home page javalin get method for rendering the home page
 */
public class HomePage implements Page {
    private Javalin javalin;
    private HashMap<String, Object> context;

    public HomePage(Javalin javalinApp, HashMap<String, Object> appContext) {
        javalin = javalinApp;
        context = appContext;
    }

    @Override
    public void doPage() {
        javalin.get("/", ctx -> {((LoggedInController) (context.get("LoggedInController"))).homePage(ctx);});
    }
}
