/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package java.controller;


import java.businesslayer.FoodItemBusinessLogic;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferobject.FoodItemDTO;

/**
 *
 * @author phron
 */
public class AddFoodServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddFoodServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddFoodServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Extract and convert form parameters
            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));
            java.sql.Date expirationDate = java.sql.Date.valueOf(request.getParameter("expirationDate"));
            double discountRate = Double.parseDouble(request.getParameter("discountRate"));
            boolean isForDonation = request.getParameter("isForDonation") != null;

            // Retrieve user ID from session (ensure user is logged in and has a valid session)
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            if (userId == null) {
                // Handle not logged in state, e.g., redirect to login page
                response.sendRedirect("login.jsp");
                return;
            }

            // Create and populate FoodItemDTO object
            FoodItemDTO foodItem = new FoodItemDTO();
            foodItem.setName(name);
            foodItem.setQuantity(quantity);
            foodItem.setPrice(price);
            foodItem.setExpirationDate(expirationDate);
            foodItem.setDiscountRate(discountRate);
            foodItem.setForDonation(isForDonation);
            foodItem.setUserId(userId); // Set the user ID

            // Use business logic to add the food item
            FoodItemBusinessLogic foodBL = new FoodItemBusinessLogic();
            foodBL.addFoodItem(foodItem);

            // Redirect or forward after successful addition
            response.sendRedirect("allfood.jsp"); // Adjust redirect as needed
        } catch (Exception e) {
            e.printStackTrace();
            // Optionally, set an error message and forward/redirect to an error page or form page
            request.setAttribute("errorMessage", "Failed to add food item.");
            request.getRequestDispatcher("addfood.jsp").forward(request, response);
        }
    }
}
