package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        if(username.equals("admin")&&password.equals("admin")) {
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            HttpSession newSession = request.getSession(true);
            newSession.setMaxInactiveInterval(5 * 60);
            response.getWriter().write("login successful");
        }
        else
        {
            response.getWriter().write("invalid uusername or password");

        }
    }
}