package controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Transaction;
import pojo.request.TransactionPayload;
import service.TransactionService;

import java.util.List;

import static java.util.Objects.isNull;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

public class TransactionController {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static TransactionService transactionService = new TransactionService();


    public static void init() {
        // POST - add transaction
        post("/transaction/add", (request, response) ->{
            try {
                TransactionPayload transactionPayload = objectMapper.readValue(request.body(), TransactionPayload.class);

                if (!transactionPayload.isValid()) {
                    response.status(400);
                    return "";
                }
                Transaction transaction = transactionService.add(
                        transactionPayload.getSender(),
                        transactionPayload.getReceiver(), transactionPayload.getAmount()
                );
                response.status(201);
                response.type("application/json");
                return objectMapper.writeValueAsString(transaction);
            } catch (JsonParseException jpe) {
                response.status(400);
                return "";
            }
        });

        // GET - Return transaction with given id
        get("/transaction/:id", (request, response) -> {
            Transaction transaction = transactionService.findById(request.params(":id"));
            if (transaction != null) {
                response.type("application/json");
                return objectMapper.writeValueAsString(transaction);
            } else {
                response.status(404); // 404 Not found
                return objectMapper.writeValueAsString("Transaction not found");
            }
        });

        // PUT - Edit a transaction
        put("/transaction/:id", (request, response) -> {
            try {
                String id = request.params(":id");
                TransactionPayload transactionPayload = objectMapper.readValue(request.body(), TransactionPayload.class);

                if (!transactionPayload.isValid() || isNull(id)) {
                    response.status(400);
                    return "";
                }

                Transaction transaction = transactionService.findById(id);
                if (transaction != null) {
                    Transaction transactionResponse = transactionService.update(
                            id,
                            transactionPayload.getSender(),
                            transactionPayload.getReceiver(),
                            transactionPayload.getAmount()
                    );
                    return objectMapper.writeValueAsString("Transaction with id #" + id + " is updated!");
                } else {
                    response.status(404); // 404 Not found
                    return objectMapper.writeValueAsString("Transaction not found");
                }
            } catch (JsonParseException jpe) {
                response.status(400);
                return "";
            }
        });

        // DELETE - Delete a transaction by id
        delete("/transaction/:id", (request, response) -> {
            String id = request.params(":id");
            Transaction transaction = transactionService.findById(id);
            if (transaction != null) {
                transactionService.delete(id);
                response.type("application/json");
                return objectMapper.writeValueAsString("Transaction with id #" + id + " is deleted!");
            } else {
                response.status(404); // 404 Not found
                return objectMapper.writeValueAsString("Transaction not found");
            }
        });

        // GET - Return all transactions
        get("/transaction", (request, response) -> {
            List result = transactionService.findAll();
            if (result.isEmpty()) {
                return objectMapper.writeValueAsString("Transactions not found");
            } else {
                response.type("application/json");
                return objectMapper.writeValueAsString(transactionService.findAll());
            }
        });
    }
}
