/*
 * portions from Core Servlets and JavaServer Pages
 * Prentice Hall and Sun Microsystems Press,
 * http://www.coreservlets.com/.
 *   (C) 2003 Marty Hall and Larry Brown;
 */
package algonquin.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import algonquin.dto.Item;
import algonquin.dto.User;
import algonquin.businesslayer.ItemService;
import algonquin.businesslayer.UserService;

/**
 * @author 
 */
public class SigninServlet extends HttpServlet {

    private UserService userService = null;
    private ItemService itemService = null;

    public SigninServlet() {
        this.userService = new UserService();
        this.itemService = new ItemService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.findByUsername(username);
        boolean isValid = user != null && user.getUsername().equalsIgnoreCase(username) && user.getPassword().equalsIgnoreCase(password);
        if (isValid) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("user", user);

            List<Item> items = itemService.findByUser(user);
            request.setAttribute("items", items);

        } else {
            request.setAttribute("errorMsg", "Username or password is invalid.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


}
