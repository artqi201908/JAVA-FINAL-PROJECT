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
public class SignupServlet extends HttpServlet {

    private UserService userService = null;

    public SignupServlet() {
        this.userService = new UserService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Long type = Long.valueOf(request.getParameter("type"));
        BigDecimal balance = request.getParameter("balance").equalsIgnoreCase("") ? null : new BigDecimal(request.getParameter("balance"));
        Boolean isSubscribe = "on".equalsIgnoreCase(request.getParameter("isSubscribe"));

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setTypeId(type);
        user.setBalance(balance);
        user.setIsSubscribe(isSubscribe);
        user.setCreateUserId(-1L);

        try {
            userService.create(user);

            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("user", userService.findByUsername(username));

            response.sendRedirect("listItem");
        } catch (ValidationException e) {
            request.setAttribute("errorMsg", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
