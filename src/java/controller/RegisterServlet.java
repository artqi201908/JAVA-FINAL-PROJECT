
package java.controller;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.businesslayer.UserBusinessLogic;
import java.math.BigDecimal;


/**
 *
 * @author
 */
public class RegisterServlet extends HttpServlet {

        private UserBusinessLogic userDAO = null;

        public RegisterServlet() {
            userDAO = new UserBusinessLogic();
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




            userDAO.create(user);

            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            processRequest(request, response);
        }

    }

}