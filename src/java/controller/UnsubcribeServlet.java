package java.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.businesslayer.UserBusinessLogic;
import java.businesslayer.ValidationException;
import java.io.IOException;
import java.transferobject.UserDTO;

public class UnsubcribeServlet extends HttpServlet {
    private UserBusinessLogic userService = null;

    public UnsubcribeServlet() {
        this.userService = new UserBusinessLogic();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        user.setIsSubscribe(false);
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
