/*
 * portions from Core Servlets and JavaServer Pages
 * Prentice Hall and Sun Microsystems Press,
 * http://www.coreservlets.com/.
 *   (C) 2003 Marty Hall and Larry Brown;
 */
package algonquin.controller;

import algonquin.dto.User;
import algonquin.businesslayer.UserService;
import algonquin.businesslayer.ValidationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 
 */
public class SubscribeServlet extends HttpServlet {

    private UserService userService = null;

    public SubscribeServlet() {
        this.userService = new UserService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        user.setIsSubscribe(true);
        user.setModUserId(user.getId());

        try {
            userService.update(user);

            response.sendRedirect("listItem");
        } catch (ValidationException e) {
            response.sendRedirect("listItem");
        }

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
