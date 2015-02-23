package com.martynovich.hotel.controllers.commands;

import com.martynovich.hotel.dao.ClientDao;
import com.martynovich.hotel.domain.Client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author
 */
public class LoginCommand implements Command {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        if (checkLogin(login, pass)) {
            page = "main.jsp";
            request.getSession().setAttribute("clintLogin", login);
            request.setAttribute("pass", pass);
        } else {
            page = "error.jsp";
        }
        return page;
    }

    private boolean checkLogin(String login, String password){
        ClientDao clientDao = new ClientDao();
        Client client = clientDao.read(login);
        return login.equals(client.getLogin()) & password.equals(client.getPassword());
    }
}
