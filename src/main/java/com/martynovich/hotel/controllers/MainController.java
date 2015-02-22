package com.martynovich.hotel.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author
 */
public class MainController extends HttpServlet {
    RequestHelper requestHelper = RequestHelper.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {


        /*if ("vasya".equals(login) && "1".equals(password)) {
            req.getSession().setAttribute("clintLogin", login);
            req.setAttribute("pass", 1);
            req.getRequestDispatcher("main.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("error.jsp").forward(req, resp);
            //resp.sendRedirect("error.jsp");
        }*/
        String page = requestHelper.getCommand(req).execute(req, resp);
        req.getRequestDispatcher(page).forward(req, resp);
    }
}
