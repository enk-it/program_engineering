package org.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.example.builder.Employee;
import org.example.builder.Gender;
import org.example.service.Api;
import org.example.service.Database;
import org.example.service.Form;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static Employee hardCodeBuilder() {
        return Employee.builder().name("Viktor").email("gladysh.dani@yandex.ru").gender(Gender.Male).build();
    }

    public static Employee apiBuilder() throws IOException{
        Api api = new Api();
        return api.getUser();
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

    public static void dbBuilder () throws SQLException {
        Database db = new Database();
        db.getUser();
    }



    public static void main(String[] args) throws Exception {
//        Employee emp1 = hardCodeBuilder();
//        Employee emp2 = apiBuilder();
//        Employee emp3 = formBuilder();

        dbBuilder();

//        System.out.println(emp1);
//        System.out.println(emp2);
//        System.out.println(emp3);

    }
}