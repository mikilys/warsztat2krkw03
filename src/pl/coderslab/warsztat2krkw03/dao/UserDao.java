package pl.coderslab.warsztat2krkw03.dao;

import pl.coderslab.warsztat2krkw03.db.Db;
import pl.coderslab.warsztat2krkw03.model.User;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class UserDao {

    private static final String CREATE_USER_QUERY = "INSERT INTO user(username, email, password) VALUES (?, ?, ?)";

    private static final String READ_USER_QUERY = "SELECT * FROM user where id = ?";

    private static final String UPDATE_USER_QUERY = "UPDATE user SET username = ?, email = ?, password = ? WHERE id = ?";

    private static final String DELETE_USER_QUERY = "DELETE FROM user WHERE id = ?";

    private static final String FIND_ALL_USER_QUERY = "SELECT * FROM user";


    //zapisanie nowego usera
    public static User create(User user) {

        try (Connection con = DriverManager.getConnection(Db.URL, Db.USER, Db.PASSWORD)) {
            final PreparedStatement ps = con.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();

            final ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //odczytanie danych o userze
    public static User read (int userId) {

        try (Connection con = DriverManager.getConnection(Db.URL, Db.USER, Db.PASSWORD)) {
            final PreparedStatement ps = con.prepareStatement(READ_USER_QUERY);

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //modyfikacja usera
    public static void update (User user) {

        try (Connection con = DriverManager.getConnection(Db.URL, Db.USER, Db.PASSWORD)) {
            final PreparedStatement ps = con.prepareStatement(UPDATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getId());

            ps.executeUpdate();

            final ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete (int userId) {

        try (Connection con = DriverManager.getConnection(Db.URL, Db.USER, Db.PASSWORD)) {
            final PreparedStatement ps = con.prepareStatement(DELETE_USER_QUERY);

            ps.setInt(1, userId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //szukanie wszystkich
    public static User[] findAll() {

        try (Connection con = DriverManager.getConnection(Db.URL, Db.USER, Db.PASSWORD)) {

            User[] users = new User[0];
            PreparedStatement ps = con.prepareStatement(FIND_ALL_USER_QUERY);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId((rs.getInt("id")));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                users = addToArray(user, users);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static User[] addToArray (User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length+1);
        tmpUsers[users.length] = u;
        return tmpUsers;
    }

}
