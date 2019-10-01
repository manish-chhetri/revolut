package model.factory;

import model.Account;
import model.User;
import utils.AccountException;
import utils.DateUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountFactory {
    private static final AtomicInteger COUNTER = new AtomicInteger();
    public static Map<String, Account> accountMap = new HashMap<>();
    private static DateUtil dateUtil = new DateUtil();

    public Account create(User user, double balance) {
        String accountId = String.valueOf(COUNTER.incrementAndGet());
        Account account = new Account(accountId, balance, user.getId(), dateUtil.getDate(), dateUtil.getDate());
        accountMap.put(accountId, account);
        return account;
    }

    public Account getById(String id) {
        return accountMap.get(id);
    }

    public void transfer(Account fromAccount, Account toAccount, double amount) {
        if (fromAccount.getBalance() - amount < 0) {
            throw new AccountException(
                    String.format("Not enough balance in account# %s", fromAccount.getId()), 400
            );
        }

        toAccount.setBalance(toAccount.getBalance() + amount);
        fromAccount.setBalance(fromAccount.getBalance() - amount);
    }

    public Account delete(String id) {
        Account account = accountMap.remove(id);
        return account;
    }

    public Collection<Account> getAll() {
        return accountMap.values();
    }

    public void deleteAll() {
        accountMap.clear();
    }
}
