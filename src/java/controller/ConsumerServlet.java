/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Shao Tang
 */
public class ConsumerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract purchase details from the request
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            response.sendRedirect(request.getContextPath() + "/purchaseSuccess.jsp");
        } catch (NumberFormatException e) {

            request.setAttribute("error", "Invalid input format.");
            request.getRequestDispatcher("/purchaseError.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/purchaseError.jsp").forward(request, response);
        }
    }
}
