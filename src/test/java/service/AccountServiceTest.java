package service;

import model.Account;
import model.Model;
import model.User;
import org.junit.Before;
import org.junit.Test;
import utils.AccountException;

import static org.junit.Assert.assertEquals;

public class AccountServiceTest {
    private User user;
    private User user2;
    private AccountService accountService;

    @Before
    public void setUp() {
        Model.userFactory.deleteAll();
        Model.accountFactory.deleteAll();

        user = UserService.create(
                "manish", "manish@gmail.com"
        );
        user2 = UserService.create(
                "User To", "user.to@gmail.com"
        );
    }

    @Test
    public void testCreate() {
        Account account = accountService.create(user.getId(), 1000.0);
        assertEquals(account.getUserId(), user.getId());
        assertEquals(account.getBalance(), 1000.0, 0.001);
    }

    @Test(expected = AccountException.class)
    public void testCreateUserNotFound() throws AccountException {
        accountService.create("100000", 1000.0);
    }

    @Test(expected = AccountException.class)
    public void testCreateUserNegativeBalance() throws AccountException {
        accountService.create(user.getId(), -10);
    }

    @Test
    public void testGetById() {
        Account account = accountService.create(user.getId(), 1000.0);
        assertEquals(accountService.getById(account.getId()), account);
    }

    @Test(expected = AccountException.class)
    public void testGetByIdAccountNotFound() throws AccountException {
        accountService.getById("10000");
    }

    @Test
    public void testTransfer() {
        Account from = accountService.create(user.getId(), 1000.0);
        Account to = accountService.create(user2.getId(), 0);
        accountService.transfer(from.getId(), to.getId(), 500);
        assertEquals(from.getBalance(), 500.0, 0.001);
        assertEquals(to.getBalance(), 500.0, 0.001);
    }

    @Test(expected = AccountException.class)
    public void testTransferSameAccount() throws AccountException {
        Account from = accountService.create(user.getId(), 1000.0);
        accountService.transfer(from.getId(), from.getId(), 500);
    }

    @Test(expected = AccountException.class)
    public void testTransferNegativeAmount() throws AccountException {
        Account from = accountService.create(user.getId(), 1000.0);
        Account to = accountService.create(user2.getId(), 0);
        accountService.transfer(from.getId(), to.getId(), -10);
    }

    @Test
    public void testDelete() {
        Account account = accountService.create(user.getId(), 1000.0);
        assertEquals(accountService.getAll().size(), 1);
        accountService.delete(account.getId());
        assertEquals(accountService.getAll().size(), 0);
    }

    @Test(expected = AccountException.class)
    public void testDeleteNonExistAccount() throws AccountException {
        accountService.delete("10000");
    }

    @Test
    public void testGetAll() {
        accountService.create(user.getId(), 1000.0);
        accountService.create(user2.getId(), 500.0);

        assertEquals(accountService.getAll().size(), 2);
    }
}
