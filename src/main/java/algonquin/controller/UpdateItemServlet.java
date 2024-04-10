/*
 * portions from Core Servlets and JavaServer Pages
 * Prentice Hall and Sun Microsystems Press,
 * http://www.coreservlets.com/.
 *   (C) 2003 Marty Hall and Larry Brown;
 */
package algonquin.controller;

import algonquin.constant.UserType;
import algonquin.dto.Item;
import algonquin.dto.User;
import algonquin.businesslayer.DateUtil;
import algonquin.businesslayer.ItemService;
import algonquin.businesslayer.UserService;
import algonquin.businesslayer.ValidationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author 
 */
public class UpdateItemServlet extends HttpServlet {

    private ItemService itemService = null;
    private UserService userService = null;

    public UpdateItemServlet() {
        this.itemService = new ItemService();
        this.userService = new UserService();
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
        User user = (User) session.getAttribute("user");

        Item item = itemService.findById(user.getId(), itemId);
        item.setTitle(title);
        item.setDescription(description);
        item.setExpirationDate(DateUtil.format(expirationDate));
        item.setQuantity(quantity);
        item.setPrice(price);
        item.setDiscount(discount);
        item.setIsDonate(isDonate);
        item.setIsSurplus(isSurplus);
        item.setModUserId(user.getId());

        try {
            itemService.update(item);

            if (isSurplus) {
                List<User> users = userService.findSubscribedUsers();
                if (users != null && !users.isEmpty()) {
                    for (User u : users) {
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
        User user = (User) session.getAttribute("user");
        Long itemId = request.getParameter("itemId").equalsIgnoreCase("") ? null : Long.valueOf(request.getParameter("itemId"));
        Item item = itemService.findById(user.getId(), itemId);
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
