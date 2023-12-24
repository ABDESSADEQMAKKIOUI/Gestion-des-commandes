package com.example.Controler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.example.DAO.ArticleDAO;
import com.example.DAO.CommandeDAO;
import com.example.Model.Article;

/**
 * Servlet implementation class CommandeUpdate
 */
public class CommandeUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommandeUpdate() {
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
		String article = request.getParameter("articles");
        String quantite = request.getParameter("quantites");
        String idcommande = request.getParameter("idcommande"); 
        Article article1 = ArticleDAO.searchDOAByName(article);
        CommandeDAO.addArticleToCommand(paresInt(idcommande),article1.getId(), paresInt(quantite));
        CommandeDAO.updateCommandStatus(paresInt(idcommande), "updated");
	}

}
