package com.qa.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.qa.utils.DatabaseConfiguration;

public class DatabaseConnector {
    private Connection connection;

    private Statement statement;

    public DatabaseConnector() throws SQLException {
        connection = DriverManager.getConnection(DatabaseConfiguration.URL, DatabaseConfiguration.USER,
                DatabaseConfiguration.PASSWORD);
        this.statement = connection.createStatement();
    }

    public void close() throws SQLException {
        connection.close();
    }

    public void createActor(String forename, String surname) throws SQLException {
        String sql = String.format("INSERT INTO actor (`first_name`, `last_name`)" + " VALUES ('%s', '%s')", forename,
                surname);

        statement.executeUpdate(sql);
    }

    public void readAllActors() throws SQLException {
        String sql = "SELECT * FROM actor";
        ResultSet results = this.statement.executeQuery(sql);

        while (results.next()) {
            System.out.println(String.format("%s %s", results.getString("first_name"), results.getString("last_name")));
        }
    }

    public void updateActor(String forename, String surname, int id) throws SQLException {
        String sql = String.format("UPDATE actor SET `first_name` = '%s', `last_name` = '%s' WHERE `actor_id` = '%s'",
                forename, surname, id);
        statement.executeUpdate(sql);
    }

    public void deleteActor(int id) throws SQLException {
        String sql = String.format("DELETE FROM actor WHERE actor_id = '%s'", id);
        statement.executeUpdate(sql);
    }

}
