package java.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.businesslayer.FoodItemBusinessLogic;
import java.businesslayer.OrderBusinessLogic;
import java.businesslayer.ValidationException;
import java.io.IOException;
import java.transferobject.FoodItemDTO;
import java.transferobject.OrderDTO;
import java.transferobject.StatusOrder;
import java.transferobject.UserDTO;

    /**
     * @author Wenqi Tang
     */
    public class AddOrderServlet extends HttpServlet {

        private FoodItemBusinessLogic foodDAO = null;
        private OrderBusinessLogic orderDAO = null;

        public AddOrderServlet() {
            this.foodDAO = new FoodItemBusinessLogic();
            this.orderDAO = new OrderBusinessLogic();
        }

        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            Long itemId = request.getParameter("itemId").equalsIgnoreCase("") ? null : Long.valueOf(request.getParameter("itemId"));
            Long quantity = request.getParameter("quantity").equalsIgnoreCase("") ? null : Long.valueOf(request.getParameter("quantity"));
            String address = request.getParameter("address");

            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("user");

            FoodItemDTO item = foodDAO.findById(itemId);

            if (quantity == null || quantity > item.getQuantity() || quantity <= 0) {
                request.setAttribute("errorMsg", "Quantity must be greater than 0 and less than or equal to " + item.getQuantity() + ".");
                RequestDispatcher dispatcher = request.getRequestDispatcher("createOrder.jsp");
                dispatcher.forward(request, response);
            }

            // subtract quantity from item
            item.setQuantity(item.getQuantity() - quantity);
            item.setModUserId(user.getId());

            // create order
            OrderDTO order = new OrderDTO();
            order.setItemId(itemId);
            order.setQuantity(quantity);
            order.setAddress(address);
            order.setStatusId(StatusOrder.PENDING_APPROVE);
            order.setCreateUserId(user.getId());

            try {
                foodDAO.update(item);
                orderDAO.create(order);

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
            FoodItemDTO item = foodDAO.findById(itemId);
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
