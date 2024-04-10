package java.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.businesslayer.FoodItemBusinessLogic;
import java.businesslayer.OrderBusinessLogic;
import java.businesslayer.UserBusinessLogic;
import java.businesslayer.ValidationException;
import java.constant.UserType;
import java.constant.orderStatus;
import java.io.IOException;
import java.math.BigDecimal;
import java.transferobject.FoodItemDTO;
import java.transferobject.OrderDTO;
import java.transferobject.UserDTO;

public class ApproveOrderServlet extends HttpServlet {
    private OrderBusinessLogic orderService = null;
    private FoodItemBusinessLogic itemService = null;
    private UserBusinessLogic userService = null;

    public ApproveOrderServlet() {
        this.orderService = new OrderBusinessLogic();
        this.itemService = new FoodItemBusinessLogic();
        this.userService = new UserBusinessLogic();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserDTO retailer = (UserDTO) session.getAttribute("user");
        Long orderId = request.getParameter("orderId").equalsIgnoreCase("") ? null : Long.valueOf(request.getParameter("orderId"));
        // get order
        OrderDTO order = orderService.findById(orderId);
        order.setStatusId(orderStatus.APPROVED);
        order.setModUserId(retailer.getId());

        // update balance
        Long itemId = order.getItemId();
        Long quantity = order.getQuantity();
        FoodItemDTO item = itemService.findById(itemId);
        UserDTO buyer = userService.findById(order.getCreateUserId());

        if (buyer.getTypeId() == UserType.CONSUMER) {
            retailer.setBalance(retailer.getBalance().add(item.getPrice().multiply(new BigDecimal(quantity))));
            buyer.setBalance(buyer.getBalance().subtract(item.getPrice().multiply(new BigDecimal(quantity))));
        }

        try {
            orderService.update(order);
            if (buyer.getTypeId() == UserType.CONSUMER) {
                userService.update(retailer);
                userService.update(buyer);
            }
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        response.sendRedirect("listOrder");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
