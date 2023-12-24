package com.example.Controler;

import java.io.IOException;

import com.example.DAO.ClientDAO;
import com.example.Model.Client;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ClientControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientControler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String vlogin, vpassword;
		String c=null;
		
		vlogin=request.getParameter("nom");
		vpassword=request.getParameter("password");
		Client client = new Client(vlogin,vpassword);
		c= ClientDAO.addDBO(client);
		
		if(c.equals(null))
		{
			request.setAttribute("ERROR", "Login ou Mot de passe incorrecte");
			request.getRequestDispatcher("AddCommande.jsp").forward(request, response);
		}
		else
		{
			request.getSession().setAttribute("UTILISATEUR", c);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}	
}


