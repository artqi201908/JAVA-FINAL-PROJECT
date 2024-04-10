/*
 * portions from Core Servlets and JavaServer Pages
 * Prentice Hall and Sun Microsystems Press,
 * http://www.coreservlets.com/.
 *   (C) 2003 Marty Hall and Larry Brown;
 */
package algonquin.controller;

import algonquin.dto.Item;
import algonquin.dto.User;
import algonquin.businesslayer.DateUtil;
import algonquin.businesslayer.ItemService;
import algonquin.businesslayer.ValidationException;

import javax.servlet.RequestDispatcher;
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
public class CreateItemServlet extends HttpServlet {

    private ItemService itemService = null;

    public CreateItemServlet() {
        this.itemService = new ItemService();
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
        User user = (User) session.getAttribute("user");

        Item item = new Item();
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
            itemService.create(item);

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
