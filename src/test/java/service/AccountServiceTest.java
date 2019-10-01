package service;

import model.Account;
import model.Model;
import model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountServiceTest {
    private User user;
    private User user2;

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
        Account account = AccountService.create(user.getId(), 1000.0);
        assertEquals(account.getUserId(), user.getId());
        assertEquals(account.getBalance(), 1000.0, 0.001);
    }

    @Test
    public void testGetById() {
        Account account = AccountService.create(user.getId(), 1000.0);
        assertEquals(AccountService.getById(account.getId()), account);
    }

    @Test
    public void testTransfer() {
        Account from = AccountService.create(user.getId(), 1000.0);
        Account to = AccountService.create(user2.getId(), 0);
        AccountService.transfer(from.getId(), to.getId(), 500);
        assertEquals(from.getBalance(), 500.0, 0.001);
        assertEquals(to.getBalance(), 500.0, 0.001);
    }

    @Test
    public void testDelete() {
        Account account = AccountService.create(user.getId(), 1000.0);
        assertEquals(AccountService.getAll().size(), 1);
        AccountService.delete(account.getId());
        assertEquals(AccountService.getAll().size(), 0);
    }

    @Test
    public void testGetAll() {
        AccountService.create(user.getId(), 1000.0);
        AccountService.create(user2.getId(), 500.0);

        assertEquals(AccountService.getAll().size(), 2);
    }
}
