/*
 * portions from Core Servlets and JavaServer Pages
 * Prentice Hall and Sun Microsystems Press,
 * http://www.coreservlets.com/.
 *   (C) 2003 Marty Hall and Larry Brown;
 */
package algonquin.controller;

import algonquin.constant.OrderStatus;
import algonquin.constant.UserType;
import algonquin.dto.Item;
import algonquin.dto.Order;
import algonquin.dto.User;
import algonquin.businesslayer.ItemService;
import algonquin.businesslayer.OrderService;
import algonquin.businesslayer.UserService;
import algonquin.businesslayer.ValidationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author 
 */
public class ApproveOrderServlet extends HttpServlet {

    private OrderService orderService = null;
    private ItemService itemService = null;
    private UserService userService = null;

    public ApproveOrderServlet() {
        this.orderService = new OrderService();
        this.itemService = new ItemService();
        this.userService = new UserService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User retailer = (User) session.getAttribute("user");
        Long orderId = request.getParameter("orderId").equalsIgnoreCase("") ? null : Long.valueOf(request.getParameter("orderId"));
        // get order
        Order order = orderService.findById(orderId);
        order.setStatusId(OrderStatus.APPROVED);
        order.setModUserId(retailer.getId());

        // update balance
        Long itemId = order.getItemId();
        Long quantity = order.getQuantity();
        Item item = itemService.findById(itemId);
        User buyer = userService.findById(order.getCreateUserId());

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
