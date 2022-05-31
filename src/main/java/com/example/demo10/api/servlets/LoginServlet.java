package com.example.demo10.api.servlets;

import com.example.demo10.database.model.Users;
import com.example.demo10.server.DefaultService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/")
public class LoginServlet extends HttpServlet {
    private DefaultService defaultService;

    @Override
    public void init() throws ServletException {
        this.defaultService = (DefaultService) getServletContext().getAttribute("service");
    }

/*    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }*/

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String login = request.getParameter("login");
        final String password = request.getParameter("password");

        if (defaultService.isUserAuthenticated(login, password)) {

            request.getSession();
            Users user = defaultService.userByLogin(login);

            request.getSession().setAttribute("user", user);

            //moveToMenu(request, response, user.getRole());
        } else {

        response.sendRedirect("/demo10_war_exploded/");
    }
    }

    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final String role)
            throws ServletException, IOException {


        if (role.equals("directorate") || role.equals("leaders") || role.equals("education")) {

            req.getRequestDispatcher("/WEB-INF/view/admin_menu.jsp").forward(req, res);

        } else {
            req.getRequestDispatcher("/WEB-INF/view/user_menu.jsp").forward(req, res);
        }
    }
}
