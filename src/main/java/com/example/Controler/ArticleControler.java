package com.example.Controler;

import java.io.IOException;

import com.example.DAO.ArticleDAO;
import com.example.Model.Article;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ArticleControler extends HttpServlet {
        private static final long serialVersionUID = 1L;

        /**
         * @see HttpServlet#HttpServlet()
         */
        public ArticleControler() {
            super();
            // TODO Auto-generated constructor stub
        }


        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException {
            // TODO Auto-generated method stub

            String code = null ;
            String nom = null ;

            code=request.getParameter("code");
            int quontite = Integer.parseInt(request.getParameter("quontite"));
            nom=request.getParameter("nom");
             Article article= new Article(quontite,nom,code);
           String c = null;
           c = ArticleDAO.addDBO(article);
            if(c==null)
            {
                request.setAttribute("ERROR", "Error inserting user into the database");
                request.getRequestDispatcher("AddArticle.jsp").forward(request, response);
            }
            else
            {
                request.setAttribute("ERROR", c);
                request.getRequestDispatcher("AddArticle.jsp").forward(request, response);
            }



        }

    }
