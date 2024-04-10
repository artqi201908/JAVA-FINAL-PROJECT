/*
 * portions from Core Servlets and JavaServer Pages
 * Prentice Hall and Sun Microsystems Press,
 * http://www.coreservlets.com/.
 *   (C) 2003 Marty Hall and Larry Brown;
 */
package algonquin.controller;

import algonquin.dao.OrderDTO;
import algonquin.dto.User;
import algonquin.businesslayer.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author 
 */
public class ListOrderServlet extends HttpServlet {

    private OrderService orderService = null;

    public ListOrderServlet() {
        this.orderService = new OrderService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<OrderDTO> orders = orderService.findAll(user);
        request.setAttribute("orders", orders);

        RequestDispatcher dispatcher = request.getRequestDispatcher("listOrder.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
