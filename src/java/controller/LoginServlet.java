/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package java.controller;

import java.businesslayer.FoodItemBusinessLogic;
import java.businesslayer.UserBusinessLogic;
import java.io.IOException;
import java.transferobject.FoodItemDTO;
import java.transferobject.UserDTO;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author
 */
public class LoginServlet extends HttpServlet {

    private UserBusinessLogic userDAO = null;
    private FoodItemBusinessLogic itemDAO = null;

    public LoginServlet() {
        userDAO = new UserBusinessLogic();
        itemDAO = new FoodItemBusinessLogic();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDTO user = userDAO.findByUsername(username);
        boolean isValid = user != null && user.getUsername().equalsIgnoreCase(username) && user.getPassword().equalsIgnoreCase(password);
        if (isValid) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("user", user);

            List<FoodItemDTO> items = itemDAO.findByUser(user);
            request.setAttribute("items", items);

        } else {
            request.setAttribute("errorMsg", "Username or password is invalid.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


}