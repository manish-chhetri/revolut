package service;

import model.Account;
import model.Model;
import model.User;
import utils.AccountException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class AccountService {
    private static Map<String, String> responseMap = new HashMap<>();

    public static Account create(String userId, double balance) {
        User user = Model.userFactory.getById(userId);

        if (isNull(user)) {
            throw new AccountException(
                    String.format("User with id# %s does not exist", userId), 404
            );
        }
        if (balance < 0) {
            throw new AccountException("Negative balance", 400);
        }

        return Model.accountFactory.create(user, balance);
    }

    public static Account getById(String id) {
        Account account = getAccount(id);
        return account;
    }

    public static Map<String, String> transfer(String fromAccountId, String toAccountId, double amount) {
        Account fromAccount = getAccount(fromAccountId);
        Account toAccount = getAccount(toAccountId);

        if (fromAccount.getId().equals(toAccount.getId())) {
            throw new AccountException(
                    "Can not transfer money to itself", 400
            );
        }

        if (amount < 0) {
            throw new AccountException("Invalid amount", 400);
        }
        Model.accountFactory.transfer(fromAccount, toAccount, amount);

        responseMap.put("message", "Amount transferred successfully");
        return responseMap;
    }

    public static Map<String, String> delete(String id) {
        Account account = Model.accountFactory.delete(id);
        if (isNull(account)) {
            throw new AccountException(
                    String.format("Account with id# %s not found", id), 404
            );
        }
        responseMap.put("message", "Account deleted successfully");
        return responseMap;
    }

    public static Collection<Account> getAll() {
        return Model.accountFactory.getAll();
    }

    private static Account getAccount(String accountId) {
        Account account = Model.accountFactory.getById(accountId);
        if (isNull(account)) {
            throw new AccountException(
                    String.format("Account id# %s not found", accountId), 404
            );
        }
        return account;

    }
}
