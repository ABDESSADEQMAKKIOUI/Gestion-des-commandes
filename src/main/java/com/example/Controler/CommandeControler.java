package com.example.Controler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.Model.Article;
import com.example.Model.Commande;
import com.example.DAO.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
/**
 * Servlet implementation class Logincontroler
 */

public class CommandeControler extends HttpServlet {

       
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommandeControler() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
  

    	protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
    		HttpSession session = request.getSession();
            String[] articles = request.getParameterValues("articles");
            String[] quantites = request.getParameterValues("quantites");
            System.out.println("Articles: " + Arrays.toString(articles));
            System.out.println("Quantities: " + Arrays.toString(quantites));

            int clientId=0;
            Object clientIdObj = session.getAttribute("clientId");

            if (clientIdObj != null && clientIdObj instanceof Integer) {
                 clientId = (Integer) clientIdObj;

            } else {
            	System.out.println("pas de client declarer");
            }
            String code  ="COD"+ Commande.generateRandomNumber();
            Commande commande = new Commande(ClientDAO.searchDOAById(clientId ) , LocalDate.now(),code );
            ArrayList<Article> listeArticles = new ArrayList<>();
            int[] listequontite = new int[15];
            for (int i = 0; i < articles.length; i++) {
                listeArticles.add( ArticleDAO.searchDOAByName(articles[i]));
                listequontite[i]=paresInt(quantites[i]);
            }
            commande.setArticles(listeArticles);
            CommandeDAO.addDBO(commande);
            CommandeDAO.addDBO(commande.getArticles(), commande.getId(),listequontite);
            CommandeDAO.updateCommandStatus(commande.getId(), "confirmed");
            response.sendRedirect("addcommand.jsp");
        }

	private int paresInt(String string) {
		// TODO Auto-generated method stub
		return 0;
	}

    }

        



