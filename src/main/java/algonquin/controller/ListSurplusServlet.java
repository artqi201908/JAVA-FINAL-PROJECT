/*
 * portions from Core Servlets and JavaServer Pages
 * Prentice Hall and Sun Microsystems Press,
 * http://www.coreservlets.com/.
 *   (C) 2003 Marty Hall and Larry Brown;
 */
package algonquin.controller;

import algonquin.dto.Item;
import algonquin.dto.User;
import algonquin.businesslayer.ItemService;

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
public class ListSurplusServlet extends HttpServlet {

    private ItemService itemService = null;

    public ListSurplusServlet() {
        this.itemService = new ItemService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<Item> items = itemService.findSurplus(user.getId());
        request.setAttribute("items", items);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
