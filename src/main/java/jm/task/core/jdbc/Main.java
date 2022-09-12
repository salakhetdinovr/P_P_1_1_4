package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Mike", "Michelson", (byte) 15);
        userDao.saveUser("Tom", "Ford", (byte) 20);
        userDao.saveUser("Bob", "Night", (byte) 30);
        userDao.saveUser("Silvia", "Lumia", (byte) 40);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
