package com.example.Controler;

import java.io.IOException;

import com.example.DAO.ArticleDAO;
import com.example.Model.Article;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class GereArticle extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GereArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String code = null;
        String nom = null;
        int quontite = 0;

        code = request.getParameter("code");
        quontite = Integer.parseInt(request.getParameter("quontite"));
        nom = request.getParameter("nom");
        Article article = new Article(quontite, nom, code);
        String c = ArticleDAO.addDBO(article);

        if (c == null) {
            request.setAttribute("ERROR", "Error inserting user into the database");
            request.getRequestDispatcher("AddArticle.jsp").forward(request, response);
        } else {
            request.setAttribute("ERROR", c);
            request.getRequestDispatcher("AddArticle.jsp").forward(request, response);
        }

    }
}