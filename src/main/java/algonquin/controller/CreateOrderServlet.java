/*
 * portions from Core Servlets and JavaServer Pages
 * Prentice Hall and Sun Microsystems Press,
 * http://www.coreservlets.com/.
 *   (C) 2003 Marty Hall and Larry Brown;
 */
package algonquin.controller;

import algonquin.constant.OrderStatus;
import algonquin.dto.Item;
import algonquin.dto.Order;
import algonquin.dto.User;
import algonquin.businesslayer.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 
 */
public class CreateOrderServlet extends HttpServlet {

    private ItemService itemService = null;
    private OrderService orderService = null;

    public CreateOrderServlet() {
        this.itemService = new ItemService();
        this.orderService = new OrderService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long itemId = request.getParameter("itemId").equalsIgnoreCase("") ? null : Long.valueOf(request.getParameter("itemId"));
        Long quantity = request.getParameter("quantity").equalsIgnoreCase("") ? null : Long.valueOf(request.getParameter("quantity"));
        String address = request.getParameter("address");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Item item = itemService.findById(itemId);

        if (quantity == null || quantity > item.getQuantity() || quantity <= 0) {
            request.setAttribute("errorMsg", "Quantity must be greater than 0 and less than or equal to " + item.getQuantity() + ".");
            RequestDispatcher dispatcher = request.getRequestDispatcher("createOrder.jsp");
            dispatcher.forward(request, response);
        }

        // subtract quantity from item
        item.setQuantity(item.getQuantity() - quantity);
        item.setModUserId(user.getId());

        // create order
        Order order = new Order();
        order.setItemId(itemId);
        order.setQuantity(quantity);
        order.setAddress(address);
        order.setStatusId(OrderStatus.PENDING_APPROVE);
        order.setCreateUserId(user.getId());

        try {
            itemService.update(item);
            orderService.create(order);

            response.sendRedirect("listOrder");
        } catch (ValidationException e) {
            request.setAttribute("errorMsg", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("createOrder.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long itemId = request.getParameter("itemId").equalsIgnoreCase("") ? null : Long.valueOf(request.getParameter("itemId"));
        Item item = itemService.findById(itemId);
        request.setAttribute("item", item);

        RequestDispatcher dispatcher = request.getRequestDispatcher("createOrder.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
