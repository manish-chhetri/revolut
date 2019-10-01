package controllers;

import utils.AccountException;
import utils.UserException;

import static spark.Spark.before;
import static spark.Spark.exception;
import static utils.commonUtills.errorResponse;

public class CommonController {
    public static void init() {
        before((request, response) -> response.type("application/json"));
        exception(UserException.class, (error, request, response) -> {
            response.status(500);
            response.body(errorResponse(error));
        });
        exception(AccountException.class, (error, request, response) -> {
            response.status(500);
            response.body(errorResponse(error));
        });
    }
}
