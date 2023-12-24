package com.example.Controler;

import java.io.IOException;

import com.example.DAO.UserDAO;
import com.example.Model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logincontroler
 */

public class Logincontroler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logincontroler() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String vlogin, vpassword;
		User c=null;
		
		vlogin=request.getParameter("Email");
		vpassword=request.getParameter("Password");
		
		c= UserDAO.login(vlogin, vpassword);
		
		if(c==null)
		{
			request.setAttribute("ERROR", "Login ou Mot de passe incorrecte");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
		else
		{
			request.getSession().setAttribute("UTILISATEUR", c);
			if(c.getRole().equals("admin")) {
			request.getRequestDispatcher("AddArticle.jsp").forward(request, response);
		}
			else {
				request.getRequestDispatcher("User/AddArticle.jsp").forward(request, response);

			}
		}
		
		
	}

}
