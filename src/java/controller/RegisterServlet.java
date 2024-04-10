
package java.controller;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.businesslayer.UserBusinessLogic;
import java.businesslayer.ValidateException;
import java.io.IOException;
import java.math.BigDecimal;
import java.transferobject.UserDTO;


/**
 *
 * @author Danni
 */
public class  RegisterServlet extends HttpServlet {

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


            UserDTO user = new UserDTO();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setPhone(phone);
            user.setTypeId(type);
            user.setBalance(balance);
            user.setIsSubscribe(isSubscribe);
            user.setCreateUserId(-1L);

            try {
                UserBusinessLogic.create(user);

                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("user", UserBusinessLogic.findByUsername(username));

                response.sendRedirect("listItem");
            } catch (ValidateException.ValidationException e) {
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

