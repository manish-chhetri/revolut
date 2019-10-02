package service;

import model.Model;
import model.User;
import org.junit.Before;
import org.junit.Test;
import utils.UserException;

import static org.junit.Assert.assertEquals;

public class UserServiceTest {
    @Before
    public void setUp() {
        Model.userFactory.deleteAll();
        Model.accountFactory.deleteAll();
    }

    @Test
    public void testCreate() {
        User user = UserService.create("manish", "manish@gmail.com");
        assertEquals(user.getName(), "manish");
        assertEquals(user.getEmail(), "manish@gmail.com");
    }

    @Test(expected = UserException.class)
    public void testCreateNullName() throws UserException {
        User user = UserService.create("", "manish@gmail.com");
    }

    @Test(expected = UserException.class)
    public void testCreateNullEmail() throws UserException {
        User user = UserService.create("manish", "");
    }

    @Test
    public void testGetById() {
        User user = UserService.create("manish", "manish@gmail.com");
        assertEquals(UserService.getById(user.getId()), user);
    }

    @Test(expected = UserException.class)
    public void testGetByIdNotFound() throws UserException {
        UserService.getById("10000");
    }

    @Test
    public void testDelete() {
        User user = UserService.create("manish", "manish@gmail.com");
        assertEquals(UserService.getAll().size(), 1);
        UserService.delete(user.getId());
        assertEquals(UserService.getAll().size(), 0);
    }

    @Test(expected = UserException.class)
    public void testDeleteNonExistUser() throws UserException {
        UserService.delete("10000");
    }

    @Test
    public void testGetAll() {
        UserService.create("manish", "manish@gmail.com");
        UserService.create("User To", "user.to@gmail.com");

        assertEquals(UserService.getAll().size(), 2);
    }
}
