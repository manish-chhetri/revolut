import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Transaction;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pojo.request.TransactionPayload;
import service.TransactionService;
import spark.Spark;
import testing.TestClient;
import testing.TestResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static spark.Spark.awaitInitialization;

public class ApplicationTest {
    public static final String PORT = "8080";
    private Gson gson;
    private TestClient client;
    private TransactionService transactionService;

    @BeforeClass
    public static void beforeClass() {
        String[] args = {PORT};
        Application.main(args);
        awaitInitialization();
    }

    @AfterClass
    public static void afterClass() {
        Spark.stop();
    }

    @Before
    public void setUp() throws Exception {
        client = new TestClient("http://localhost:" + PORT);
        gson = new Gson();
        transactionService = new TransactionService();
    }

    @Test
    public void addTransaction() {
        Map<String, String> transactionPayloadMap = new HashMap<>();
        transactionPayloadMap.put("sender", "1");
        transactionPayloadMap.put("receiver", "2");
        transactionPayloadMap.put("amount", "100.2");

        TestResponse res = client.request("POST", "/transaction/add", gson.toJson(transactionPayloadMap));
        assertEquals(201, res.getStatus());
    }

    @Test
    public void getTransactionById() {
        TransactionPayload payload = getTestTransaction();
        Transaction transaction = transactionService.add(payload.getSender(), payload.getReceiver(), payload.getAmount());

        TestResponse res = client.request("GET",
                "/transaction/" + transaction.getId());
        Transaction retrieved = gson.fromJson(res.getBody(), Transaction.class);
        assertEquals(transaction, retrieved);
    }

    @Test
    public void getAllTransaction() {
        List transactionList = transactionService.findAll();

        TestResponse res = client.request("GET",
                "/transaction");
        List<Transaction> retrievedTransaction = gson.fromJson(res.getBody(), new TypeToken<List<Transaction>>(){}.getType());
        assertTrue(transactionList.size() > 0);
        assertEquals(transactionList.size(), retrievedTransaction.size());
    }

    private TransactionPayload getTestTransaction() {
        TransactionPayload transactionPayload = new TransactionPayload();
        transactionPayload.setSender("1");
        transactionPayload.setReceiver("2");
        transactionPayload.setAmount("100.2");
        return transactionPayload;
    }
}
