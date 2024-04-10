package java.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.businesslayer.DateFormat;
import java.businesslayer.FoodItemBusinessLogic;
import java.businesslayer.UserBusinessLogic;
import java.businesslayer.ValidationException;
import java.constant.UserType;
import java.io.IOException;
import java.math.BigDecimal;
import java.transferobject.FoodItemDTO;
import java.transferobject.UserDTO;
import java.util.List;

public class UpdateItemServlet extends HttpServlet {
    private FoodItemBusinessLogic itemDAO = null;
    private UserBusinessLogic userDAO = null;

    public UpdateItemServlet() {
        itemDAO = new FoodItemBusinessLogic();
        userDAO = new UserBusinessLogic();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long itemId = request.getParameter("itemId").equalsIgnoreCase("") ? null : Long.valueOf(request.getParameter("itemId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String expirationDate = request.getParameter("expirationDate");
        Long quantity = request.getParameter("quantity").equalsIgnoreCase("") ? null : Long.valueOf(request.getParameter("quantity"));
        BigDecimal price = request.getParameter("price").equalsIgnoreCase("") ? null : new BigDecimal(request.getParameter("price"));
        Long discount = request.getParameter("discount").equalsIgnoreCase("") ? null : Long.valueOf(request.getParameter("discount"));
        Boolean isDonate = "on".equalsIgnoreCase(request.getParameter("isDonate"));
        Boolean isSurplus = "on".equalsIgnoreCase(request.getParameter("isSurplus"));

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        FoodItemDTO item = itemDAO.findById(user.getId(), itemId);
        item.setTitle(title);
        item.setDescription(description);
        item.setExpirationDate(DateFormat.format(expirationDate));
        item.setQuantity(quantity);
        item.setPrice(price);
        item.setDiscount(discount);
        item.setIsDonate(isDonate);
        item.setIsSurplus(isSurplus);
        item.setModUserId(user.getId());

        try {
            itemDAO.update(item);

            if (isSurplus) {
                List<UserDTO> users = userDAO.findSubscribedUsers();
                if (users != null && !users.isEmpty()) {
                    for (UserDTO u : users) {
                        if (u.getTypeId() == UserType.CHARITABLE_ORGANIZATION && item.getIsDonate()) {
                            System.out.println("Send email to " + u.getEmail() + ", surplus item " + item.getTitle() + " is for donation.");
                        } else if (u.getTypeId() == UserType.CONSUMER && !item.getIsDonate() && item.getDiscount() > 0) {
                            System.out.println("Send email to " + u.getEmail() + ", surplus item " + item.getTitle() + " discount " + item.getDiscount() + "%.");
                        } else if (u.getTypeId() == UserType.RETAILER) {
                            System.out.println("Send email to " + u.getEmail() + ", surplus item " + item.getTitle() + " was marked.");
                        }

                    }
                }
            }

            response.sendRedirect("listItem");
        } catch (ValidationException e) {
            request.setAttribute("errorMsg", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("updateItem.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        Long itemId = request.getParameter("itemId").equalsIgnoreCase("") ? null : Long.valueOf(request.getParameter("itemId"));
        FoodItemDTO item = itemDAO.findById(user.getId(), itemId);
        request.setAttribute("item", item);

        RequestDispatcher dispatcher = request.getRequestDispatcher("updateItem.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
