/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package java.controller;

import businesslayer.UserBusinessLogic;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferobject.UserDTO;

/**
 *
 * @author phron
 */
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String name = request.getParameter("login");
        String password = request.getParameter("pass");
        String confirmPassword = request.getParameter("pass2");
        String userTypeStr = request.getParameter("userType");
        
       if (email == null || email.isEmpty() || name == null || name.isEmpty()
                || password == null || password.isEmpty() || confirmPassword == null
                || !password.equals(confirmPassword) || userTypeStr == null || userTypeStr.isEmpty()) {
            request.setAttribute("error", "All fields are required and passwords must match.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
            return;
        }
        int userType = mapUserTypeToInt(userTypeStr);
        UserDTO user = new UserDTO();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setUserType(userType);

        UserBusinessLogic userLogic = new UserBusinessLogic();
        boolean isRegistered = userLogic.addUser(user);
        if(isRegistered == true){
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("error", "Registration failed. Email may already be in use or other error.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        }
    }

    private int mapUserTypeToInt(String userTypeStr) {
        String userTypeLowerCase = userTypeStr.toLowerCase();
        switch (userTypeLowerCase) {
            case "retailer":
                return 1;
            case "consumer":
                return 2;
            case "charitableorganization":
                return 3;
            default:
                return 0; // Invalid user type
        }
    }
}
