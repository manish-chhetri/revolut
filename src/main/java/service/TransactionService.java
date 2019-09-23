package service;

import model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.isNull;

public class TransactionService{
    public static Map<Long, Transaction> transactions = new HashMap();
    private static final AtomicInteger COUNTER = new AtomicInteger();

    public Transaction findById(String id) {
        return transactions.get(Long.valueOf(id));
    }

    public Transaction add(String sender, String receiver, String amount) {
        DateUtil dateUtill = new DateUtil();

        int currentId = COUNTER.incrementAndGet();
        Transaction transaction = new Transaction();
        transaction.setSender(Integer.parseInt(sender));
        transaction.setReceiver(Integer.parseInt(receiver));
        transaction.setAmount(Double.parseDouble(amount));
        transaction.setCreatedAt(dateUtill.getDate());
        transaction.setModifiedAt(dateUtill.getDate());
        transaction.setId(currentId);

        transactions.put(Long.valueOf(currentId), transaction);
        return transaction;
    }

    public Transaction update(String id, String sender, String receiver, String amount) {
        DateUtil dateUtil = new DateUtil();
        Transaction transaction = transactions.get(Long.valueOf(id));
        if (!isNull(sender)) {
            transaction.setSender(Integer.parseInt(sender));
        }
        if (!isNull(receiver)) {
            transaction.setReceiver(Integer.parseInt(receiver));
        }
        if (!isNull(amount)) {
            transaction.setAmount(Double.parseDouble(amount));
        }
        transaction.setModifiedAt(dateUtil.getDate());
        transactions.put(Long.valueOf(id), transaction);

        return transaction;
    }

    public void delete(String id) {
        transactions.remove(Long.valueOf(id));
    }

    public List<Transaction> findAll() {
        return new ArrayList(transactions.values());
    }
}
