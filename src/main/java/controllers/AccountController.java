package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.Account;
import service.AccountService;
import utils.Serializer;
import utils.AccountException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static utils.Serializer.readJson;


public class AccountController {
    public static void init() {
        post("/account/create", (request, response) -> {
            JsonNode reqPayload = readJson(request.body());
            JsonNode accountUserId = reqPayload.get("user_id");
            JsonNode accountBalance = reqPayload.get("balance");

            if (isNull(accountUserId) || isNull(accountBalance)) {
                throw new AccountException("user_id or balance is not present", 400);
            }

            if (!accountBalance.isNumber()) {
                throw new AccountException("Balance has to be a number", 400);
            }

            return Serializer.toJson(AccountService.create(accountUserId.asText(), accountBalance.asDouble()));

        });
        get("/account/:id", (request, response) -> {
            Account account = AccountService.getById(request.params(":id"));
            return Serializer.toJson(account);
        });
        put("/account/:from_id/transfer/:to_id", (request, response) -> {
            String fromAccountId = request.params(":from_id");
            String toAccountId = request.params(":to_id");

            JsonNode reqPayload = readJson(request.body());
            JsonNode amount = reqPayload.get("amount");

            if (isNull(amount) || !amount.isNumber()) {
                throw new AccountException(
                        "Invalid transfer amount",
                        400
                );
            }

            Map<String, String> responseMap = AccountService.transfer(fromAccountId, toAccountId, amount.asDouble());
            return Serializer.toJson(responseMap);
        });
        delete("/account/:id", (request, response) -> {
            Map<String, String> responseMap = AccountService.delete(request.params(":id"));
            return Serializer.toJson(responseMap);
        });
        get("/account", (request, response) -> {
            Map<String, Collection<Account>> accountsMap = new HashMap<>();
            accountsMap.put("accounts", AccountService.getAll());
            return Serializer.toJson(accountsMap);
        });
    }
}
