package org.example.service;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Server;
import org.example.builder.Employee;
import org.example.builder.Gender;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Form extends HttpServlet {
    private final Server server;
    private Employee emp;

    public Form (Server server) {
        this.server = server;
    }

    public Employee result () {
        return this.emp;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String html = """
                <!DOCTYPE html>
                <html><head><meta charset='UTF-8'><title>Форма</title></head><body>
                <h1>Введите данные</h1>
                <form method='POST' action='/builder'>
                Имя: <input type='text' name='username'><br>
                Дата рождения: <input type='date' name='date'><br>
                Пол: 
                <select id="gender" name="gender">
                      <option value="male">Мужчина</option>
                      <option value="female">Женщина</option>
                </select>
                <br>
                <button type='submit'>Отправить</button>
                </form>
                </body></html>
            """;
        out.println(html);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String dateRaw = request.getParameter("date");
        String genderRaw = request.getParameter("gender");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date;

        try {
            date = sdf.parse(dateRaw);
        } catch (ParseException e) {
            System.out.println("Shitti date");
            System.out.println(dateRaw);
            throw new RuntimeException(e);
        }

        Gender gender = null;

        if (Objects.equals(genderRaw, "Мужчина")) {
            gender = Gender.Male;
        }

        if (Objects.equals(genderRaw, "Женщина")) {
            gender = Gender.Female;
        }

        this.emp = Employee.builder()
                .name(username)
                .date(date)
                .gender(gender)
                .build();

        try {
            server.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
