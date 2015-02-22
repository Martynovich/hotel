package com.martynovich.hotel.controllers.commands;

import com.martynovich.hotel.dao.ApartmentDao;
import com.martynovich.hotel.domain.Apartment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
public class RoomsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        page = "rooms.jsp";
        request.setAttribute("freeRooms", this.getFreeRooms());
        return page;
    }

    private List<Apartment> getFreeRooms(){
        ApartmentDao apartmentDao = new ApartmentDao();
        List<Apartment> freeRooms = apartmentDao.readFreeRooms();
        return freeRooms;
    }
}
