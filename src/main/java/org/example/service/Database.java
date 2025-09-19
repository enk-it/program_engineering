package org.example.service;

import java.sql.*;
import java.time.LocalDate;

public class Database {
    private final Connection conn;
    private final String url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

    private void createTables () throws SQLException {
        Statement st = conn.createStatement();
        st.execute("""
                CREATE TABLE employee (
                  id IDENTITY PRIMARY KEY,
                  name     VARCHAR(100) NOT NULL,
                  email    VARCHAR(255) NOT NULL UNIQUE,
                  gender   VARCHAR(10)  NOT NULL CHECK (gender IN ('MALE','FEMALE','OTHER')),
                  birthday DATE         NOT NULL
                )
            """);
    }

    private void insertData() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO employee(name,email,gender,birthday) VALUES (?,?,?,?)");
        ps.setString(1, "Alice");
        ps.setString(2, "alice@example.com");
        ps.setString(3, "FEMALE");
        ps.setDate(4, Date.valueOf(LocalDate.of(1999, 5, 12)));
        ps.executeUpdate();

        ps.setString(1, "Bob");
        ps.setString(2, "bob@example.com");
        ps.setString(3, "MALE");
        ps.setDate(4, Date.valueOf(LocalDate.of(1995, 11, 3)));
        ps.executeUpdate();
    }

    public void readData() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "SELECT id, name, email, gender, birthday FROM employee ORDER BY id");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.printf("%d | %s | %s | %s | %s%n",
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("gender"),
                rs.getDate("birthday"));
        }
    }

    public Database () throws SQLException {
        conn = DriverManager.getConnection(url, "sa", "");
        createTables();
        insertData();
    }

    public ResultSet getUser () throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "SELECT id, name, email, gender, birthday FROM employee ORDER BY id;");
        ResultSet rs = ps.executeQuery();
        rs.first();

        return rs;
    }


}
