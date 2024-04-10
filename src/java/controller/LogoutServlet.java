/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package java.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author danni
 */

public class LogoutServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 通过request.getSession()获取当前请求的会话对象。
        HttpSession session = request.getSession();
        // 从会话对象中移除属性
        session.removeAttribute("username");
        // 从会话对象中移除属性
        session.removeAttribute("user");


        // 使用request.getRequestDispatcher()方法获取RequestDispatcher对象，它引用了"index.jsp"页面。
        // 这意味着用户登出操作完成后将重定向到"index.jsp"页面。
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);


    }


    // 重写HttpServlet类的doGet方法。
    // 当服务器接收到一个GET请求时，它会调用此方法。
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException {

        // 在doGet方法中，直接调用processRequest方法，这样无论是GET请求还是POST请求，最终都会执行相同的登出逻辑。
        processRequest(request, response);
    }
    }
