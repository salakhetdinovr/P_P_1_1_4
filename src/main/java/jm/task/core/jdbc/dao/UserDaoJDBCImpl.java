package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.service.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    Connection connection = getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable()  {

        try (Statement statement = connection.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS USER " +
                    " (id INT primary key AUTO_INCREMENT, " +
                    " name varchar(255), " +
                    " lastname varchar(255), " +
                    " age int)";

            statement.executeUpdate(sql);
    } catch (SQLException e) {
        e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS USER")) {
            preparedStatement.executeUpdate();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO USER (NAME, LASTNAME, AGE) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM USER WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM USER";
        try (Statement statement = connection.createStatement();) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user =
                        new User(resultSet.getString("name"),
                                resultSet.getString("lastname"),
                                resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE TABLE USER")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
}
