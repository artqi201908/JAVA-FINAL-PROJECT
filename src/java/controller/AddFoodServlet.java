/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package java.controller;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.businesslayer.FoodItemBusinessLogic;
import java.businesslayer.ValidationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.transferobject.FoodItemDTO;
import java.transferobject.UserDTO;


/**
 * @author
 */
public class AddFoodServlet extends HttpServlet {

    private FoodItemBusinessLogic foodDAO = null;

    public AddFoodServlet(){
        this.foodDAO = new FoodItemBusinessLogic();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String expirationDate = request.getParameter("expirationDate");
        Long quantity = request.getParameter("quantity").equalsIgnoreCase("") ? null : Long.valueOf(request.getParameter("quantity"));
        BigDecimal price = request.getParameter("price").equalsIgnoreCase("") ? null : new BigDecimal(request.getParameter("price"));
        Long discount = request.getParameter("discount").equalsIgnoreCase("") ? null : Long.valueOf(request.getParameter("discount"));
        Boolean isDonate = "on".equalsIgnoreCase(request.getParameter("isDonate"));

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        FoodItemDTO item = new FoodItemDTO();
        item.setTitle(title);
        item.setDescription(description);
        item.setExpirationDate(DateUtil.format(expirationDate));
        item.setQuantity(quantity);
        item.setPrice(price);
        item.setDiscount(discount);
        item.setIsDonate(isDonate);
        item.setIsSurplus(false);
        item.setCreateUserId(user.getId());

        try {
            foodDAO.create(item);

            response.sendRedirect("listItem");
        } catch (ValidationException e) {
            request.setAttribute("errorMsg", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("createItem.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("createItem.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
