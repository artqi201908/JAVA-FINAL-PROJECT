package java.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.businesslayer.FoodItemBusinessLogic;
import java.io.IOException;
import java.transferobject.FoodItemDTO;
import java.transferobject.UserDTO;
import java.util.List;

public class ListSurplusServlet extends HttpServlet {
    private FoodItemBusinessLogic itemService = null;

    public ListSurplusServlet() {
        this.itemService = new FoodItemBusinessLogic();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        List<FoodItemDTO> items = itemService.findSurplus(user.getId());
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
