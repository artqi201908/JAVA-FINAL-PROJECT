package controller;

import businesslayer.CharitableOrganizationBusinessLogic;
import dataaccesslayer.FoodItemDAO;
import dataaccesslayer.FoodItemDAOImpl;
import transferobject.FoodItemDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handles web requests for managing food items and requests by charitable organizations.
 */
@WebServlet(urlPatterns = {"/manageRequests", "/foodItemManagement", "/dashboard"})
public class CharitableOrganizationManagementServlet extends HttpServlet {

    private CharitableOrganizationBusinessLogic businessLogic;
    private FoodItemDAO foodItemDAO;

    @Override
    public void init() {
        this.businessLogic = new CharitableOrganizationBusinessLogic();
        this.foodItemDAO = new FoodItemDAOImpl(); // Assuming a concrete implementation exists
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        try {
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
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestId = request.getParameter("requestId");
        String action = request.getParameter("action"); // Accept or reject
        boolean isSuccess;

        try {
            if ("accept".equals(action)) {
                isSuccess = businessLogic.acceptRequest(requestId);
            } else if ("reject".equals(action)) {
                isSuccess = businessLogic.rejectRequest(requestId);
            } else {
                isSuccess = false;
            }

            if (isSuccess) {
                response.sendRedirect("manageRequests");
            } else {
                request.setAttribute("error", "Failed to process the request.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred while processing the request: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}

