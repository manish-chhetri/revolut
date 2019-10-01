import controllers.AccountController;
import controllers.CommonController;
import controllers.UserController;

import static spark.Spark.get;
import static spark.Spark.port;

public class Application {
    public static final int applicationPort = 8080;

    public static void main(String[] args) {
        port(applicationPort);
        get("/", (request, response) -> "Test API");

        CommonController.init();
        AccountController.init();
        UserController.init();
    }
}
