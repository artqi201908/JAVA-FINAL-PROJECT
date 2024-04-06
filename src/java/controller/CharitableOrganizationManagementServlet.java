package controller;

import dataaccesslayer.FoodItemDAOImpl;
import businesslayer.CharitableOrganizationBusinessLogic;
import dataaccesslayer.FoodItemDAO;
import dataaccesslayer.impl.FoodItemDAOImpl;
import transferobject.FoodItemDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CharitableOrganizationManagementServlet
 * Author: artqi
 */

@WebServlet(urlPatterns = {"/manageRequests", "/foodItemManagement", "/dashboard"})
public class CharitableOrganizationManagementServlet extends HttpServlet {

    private CharitableOrganizationBusinessLogic logic;
    private FoodItemDAO foodItemDAO;

    public CharitableOrganizationManagementServlet() {
        this.logic = new CharitableOrganizationBusinessLogic();
        this.foodItemDAO = new FoodItemDAOImpl(); // Assume a concrete implementation exists
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/foodItemManagement":
                List<FoodItemDTO> foodItems = foodItemDAO.getAllFoodItems();
                request.setAttribute("foodItems", foodItems);
                request.getRequestDispatcher("/CharitableOrganizationFoodItemManagement.jsp").forward(request, response);
                break;
            case "/dashboard":
                request.getRequestDispatcher("/CharitableOrganizationDashboard.jsp").forward(request, response);
                break;
            default:
                response.sendRedirect("dashboard");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestId = request.getParameter("requestId");
        String action = request.getParameter("action"); // Accept or reject
        boolean isSuccess;

        if ("accept".equals(action)) {
            isSuccess = logic.acceptRequest(requestId);
        } else if ("reject".equals(action)) {
            isSuccess = logic.rejectRequest(requestId);
        } else {
            isSuccess = false;
        }

        if (isSuccess) {
            response.sendRedirect("manageRequests");
        } else {
            request.setAttribute("error", "Failed to process the request.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
