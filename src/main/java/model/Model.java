package model;

import model.factory.AccountFactory;
import model.factory.UserFactory;

public class Model {
    public static AccountFactory accountFactory = new AccountFactory();
    public static UserFactory userFactory = new UserFactory();
}
