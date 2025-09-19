package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.example.builder.Employee;
import org.example.builder.Gender;
import org.example.service.Api;
import org.example.service.Database;
import org.example.service.Form;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Main {

    public static Employee hardCodeBuilder() {
        return Employee.builder().name("Viktor").email("gladysh.dani@yandex.ru").gender(Gender.Male).build();
    }

    public static Employee apiBuilder() throws IOException{
        Api api = new Api();
        JsonNode userJson = api.getUser();
        return Employee.builder()
                .name(String.valueOf(userJson.get("name")))
                .email(String.valueOf(userJson.get("email")))
                .build();
    }

    public static Employee formBuilder() throws Exception {
        Server server = new Server(8081);
        Form form = new Form(server);

        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(form), "/builder");

        server.setHandler(handler);

        server.start();
        server.join();

        return form.result();
    }

    public static Employee dbBuilder () throws SQLException {
        Database db = new Database();
        ResultSet user = db.getUser();

        Gender gender;

        if (Objects.equals(user.getString("gender"), "MALE")) {
            gender = Gender.Male;
        }
        else {
            gender = Gender.Female;
        }

        return Employee.builder()
                .name(user.getString("name"))
                .email(user.getString("email"))
                .date(user.getString("birthday"))
                .gender(gender)
                .build();
    }



    public static void main(String[] args) throws Exception {
        Employee emp1 = hardCodeBuilder();
        Employee emp2 = apiBuilder();
        Employee emp3 = formBuilder();
        Employee emp4 = dbBuilder();

        System.out.println(emp1);
        System.out.println(emp2);
        System.out.println(emp3);
        System.out.println(emp4);
    }
}