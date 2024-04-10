package java.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.businesslayer.FoodItemBusinessLogic;
import java.businesslayer.ValidationException;
import java.io.IOException;

public class DeleteItemServlet extends HttpServlet {
    private FoodItemBusinessLogic foodDAO = null;

    public DeleteItemServlet() {
        this.foodDAO = new FoodItemBusinessLogic();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long itemId = request.getParameter("itemId").equalsIgnoreCase("") ? null : Long.valueOf(request.getParameter("itemId"));
        try {
            foodDAO.delete(itemId);
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        response.sendRedirect("listItem");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
