package org.example.servlet;

import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Interface.UserService;
import org.example.model.User;
import org.example.user.factory.UserInstanceFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

public class UserServlet extends HttpServlet {
    private UserService userService = UserInstanceFactory.getUserService();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //TODO Get Persistance type from request or configuration/ property file or environment
        List<User> users = userService.listUsers();
        response.getWriter().write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(users));

        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println("header name" + headerName);
            System.out.println("header value" + headerValue);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = objectMapper.readValue(request.getReader(), User.class);
        userService.insert(user);

        try {
            this.userService.insert(user);
        } catch (UnknownError var5) {
            response.setStatus(400);
            response.getWriter().write("request error...!!!");
        } catch (Exception var6) {
            response.setStatus(500);
            response.getWriter().write("Internal server error");
        }
    }

    @Override
    protected final void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        userService.delete(Integer.valueOf(req.getParameter("empId")));

    }


}