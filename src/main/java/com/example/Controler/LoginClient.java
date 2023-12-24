package com.example.Controler;

import java.io.IOException;

import com.example.DAO.ClientDAO;
import com.example.Model.Client;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginClient
 */
public class LoginClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
		
		
		vlogin=request.getParameter("Email");
		vpassword=request.getParameter("Password");
		System.out.println(vpassword );
		System.out.println(vlogin );
		Client client= ClientDAO.login(vlogin, vpassword);
		 HttpSession session = request.getSession(true);
		if(client.equals(null))
		{
			request.setAttribute("ERROR", "Login ou Mot de passe incorrecte");
			request.getRequestDispatcher("LoginClient.jsp").forward(request, response);
		}
		else
		{
			session.setAttribute("clientId", client.getId());
			request.getRequestDispatcher("addcommand.jsp").forward(request, response);
		}
		
		
		
	}

}
