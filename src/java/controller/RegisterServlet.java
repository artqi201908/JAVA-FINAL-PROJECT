/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
        // Retrieve form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userTypeStr = request.getParameter("userType");

        // Map userTypeStr to int
        int userType = mapUserTypeToInt(userTypeStr);

        UserDTO user = new UserDTO();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setUserType(userType);

        // Attempt to add user
        UserBusinessLogic userLogic = new UserBusinessLogic();
        boolean isRegistered = userLogic.addUser(user);

        if (isRegistered) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("error", "Registration failed. Email may already be in use.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private int mapUserTypeToInt(String userTypeStr) {
        String userTypeLowerCase = userTypeStr.toLowerCase(); // Chuyển đổi thành chữ thường để so sánh
        switch (userTypeLowerCase) {
            case "retailer":
                return 1;
            case "consumer":
                return 2;
            case "charitableorganization": // Chú ý rằng giá trị này cũng được chuyển thành chữ thường
                return 3;
            default:
                return 0; // Sử dụng để xác định giá trị không hợp lệ hoặc không nhận diện được
        }
    }
}
