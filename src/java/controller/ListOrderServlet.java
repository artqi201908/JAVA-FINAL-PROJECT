package java.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.businesslayer.OrderBusinessLogic;
import java.io.IOException;
import java.transferobject.OrderDTO;
import java.transferobject.UserDTO;
import java.util.List;

public class ListOrderServlet extends HttpServlet {
    private OrderBusinessLogic orderDAO = null;

    public ListOrderServlet() {
        this.orderDAO= new OrderBusinessLogic();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        List<OrderDTO> orders = orderDAO.findAll(user);
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
