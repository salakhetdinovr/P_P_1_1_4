package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService user = new UserServiceImpl();
        Util.getSessionFactory();
        user.createUsersTable();

        user.saveUser("Mike", "Michelson", (byte) 15);
        user.saveUser("Tom", "Ford", (byte) 20);
        user.saveUser("Bob", "Night", (byte) 30);
        user.saveUser("Silvia", "Lumia", (byte) 40);

        user.removeUserById(1);
        user.getAllUsers();
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
