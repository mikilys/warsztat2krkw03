package pl.coderslab.warsztat2krkw03.dao;

import pl.coderslab.warsztat2krkw03.db.Db;
import pl.coderslab.warsztat2krkw03.model.Exercise;
import pl.coderslab.warsztat2krkw03.model.User;

import java.sql.*;
import java.util.Arrays;

public class ExerciseDao {

    private static final String CREATE_EXERCISE_QUERY = "INSERT INTO exercise(title, description) VALUES (?, ?)";

    private static final String READ_EXERCISE_QUERY = "SELECT * FROM exercise where id = ?";

    private static final String UPDATE_EXERCISE_QUERY = "UPDATE exercise SET title = ?, description = ? WHERE id = ?";

    private static final String DELETE_EXERCISE_QUERY = "DELETE FROM exercise WHERE id = ?";

    private static final String FIND_ALL_EXERCISE_QUERY = "SELECT * FROM exercise";


    //dodanie nowego zadania
    public static Exercise create(Exercise exercise) {

        try (Connection con = DriverManager.getConnection(Db.URL, Db.USER, Db.PASSWORD)) {
            final PreparedStatement ps = con.prepareStatement(CREATE_EXERCISE_QUERY, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, exercise.getTitle());
            ps.setString(2, exercise.getDescription());

            ps.executeUpdate();

            final ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                exercise.setId(generatedKeys.getInt(1));
            }

            return exercise;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //odczytanie zadania
    public static Exercise read (int exeId) {

        try (Connection con = DriverManager.getConnection(Db.URL, Db.USER, Db.PASSWORD)) {
            final PreparedStatement ps = con.prepareStatement(READ_EXERCISE_QUERY);

            ps.setInt(1, exeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(rs.getInt("id"));
                exercise.setTitle(rs.getString("title"));
                exercise.setDescription(rs.getString("description"));
                return exercise;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //modyfikacja zadania
    public static void update (Exercise exercise) {

        try (Connection con = DriverManager.getConnection(Db.URL, Db.USER, Db.PASSWORD)) {
            final PreparedStatement ps = con.prepareStatement(UPDATE_EXERCISE_QUERY, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, exercise.getTitle());
            ps.setString(2, exercise.getDescription());
            ps.setInt(3, exercise.getId());

            ps.executeUpdate();

            final ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                exercise.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //usuwanie zadnia
    public static void delete (int exeId) {

        try (Connection con = DriverManager.getConnection(Db.URL, Db.USER, Db.PASSWORD)) {
            final PreparedStatement ps = con.prepareStatement(DELETE_EXERCISE_QUERY);

            ps.setInt(1, exeId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //szukanie wszystkich
    public static Exercise[] findAll() {

        try (Connection con = DriverManager.getConnection(Db.URL, Db.USER, Db.PASSWORD)) {

            Exercise[] exercises = new Exercise[0];
            PreparedStatement ps = con.prepareStatement(FIND_ALL_EXERCISE_QUERY);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Exercise exercise = new Exercise();
                exercise.setId((rs.getInt("id")));
                exercise.setTitle(rs.getString("title"));
                exercise.setDescription(rs.getString("description"));
                exercises = addToArray(exercise, exercises);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Exercise[] addToArray (Exercise e, Exercise[] exercises) {
        Exercise[] tmpExercise = Arrays.copyOf(exercises, exercises.length+1);
        tmpExercise[exercises.length] = e;
        return tmpExercise;
    }
}
