/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package java.controller;

import businesslayer.FoodItemBusinessLogic;
import dataaccesslayer.FoodItemDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import transferobject.FoodItemDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

public class RetailerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FoodItemBusinessLogic foodDAO = new FoodItemBusinessLogic();

        RequestDispatcher rd = request.getRequestDispatcher("Retailer.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve the user ID somehow, for example, from a session or a hidden form field.
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userID");

        if (userId == null) {
            // Handle case where user ID is not found. Redirect to login or error page.
            response.sendRedirect("login.jsp"); // Or show an error message.
            return;
        }

        try {
            FoodItemDAOImpl foodItemDAO = new FoodItemDAOImpl();
            List<FoodItemDTO> foodItems = foodItemDAO.getAllFoodItemsByUserId(userId);

            // Start printing out the HTML response
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Food Items List</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Food Items</h1>");

                // Check if there are food items to display
                if (foodItems.isEmpty()) {
                    out.println("<p>No food items found.</p>");
                } else {
                    out.println("<ul>");
                    for (FoodItemDTO item : foodItems) {
                        out.println("<li>");
                        out.println("Name: " + item.getName() + ", ");
                        out.println("Quantity: " + item.getQuantity() + ", ");
                        out.println("Price: " + item.getPrice() + ", ");
                        out.println("Expiration Date: " + item.getExpirationDate() + ", ");
                        out.println("Discount Rate: " + item.getDiscountRate() + ", ");
                        out.println("For Donation: " + (item.isForDonation() ? "Yes" : "No"));
                        out.println("</li>");
                    }
                    out.println("</ul>");
                }

                out.println("</body>");
                out.println("</html>");
            }
        } catch (SQLExceptionList ex) {
            Logger.getLogger(RetailerServlet.class.getName()).log(Level.SEVERE, null, ex);
            // Handle SQL Exception by showing error message or logging.
        }
    }
