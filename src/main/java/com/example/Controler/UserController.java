package com.example.Controler;

import java.io.IOException;

import com.example.DAO.UserDAO;
import com.example.Model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }
  @Override
public void init() throws ServletException {
	// TODO Auto-generated method stub
	super.init();
}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        String login = null ;
        String password = null ;
        String role = null ;


        login=request.getParameter("login");
        password=request.getParameter("password");
        role=request.getParameter("role");
        User user = new User(login,password,role);
        User c = UserDAO.addDBO(user);
        if(c==null)
        {
            request.setAttribute("ERROR", "Error inserting user into the database");
            request.getRequestDispatcher("AddUser.jsp").forward(request, response);
        }
        else
        {
            request.getSession().setAttribute("ERROR", c.getId());
            request.getRequestDispatcher("AddUser.jsp").forward(request, response);
        }



    }
}