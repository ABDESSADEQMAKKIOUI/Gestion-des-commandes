package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.Model.Article;
import com.example.Model.Client;
import com.example.Model.Commande;

public class CommandeDAO implements InterfaceDAO {

	static Connection connection =DatabaseConnection.getConnection();
    public static String addDBO(Commande commande)
    {
        try {
            String insertQuery = "INSERT INTO article (client_id,dateC,code,stats) VALUES ('"+commande.getClient().getId()+"','"+commande.getDateC()+"','"+commande.getStats()+"')";

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.executeUpdate();
            String selectQuery = "SELECT MAX(id) as id FROM commande ";
            PreparedStatement s = connection.prepareStatement(selectQuery);
            ResultSet resultSet = s.executeQuery();
            while (resultSet.next())
            {
              commande.setId(resultSet.getInt("id"));
            }
           

          return "commande inserted into the database successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error inserting user into the database.");
        }
        return null ;
    }
    public static  ArrayList<Commande> getAll()
    {
        try {
            String selectQuery = "SELECT * FROM commande";

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Commande> commandes = new ArrayList<Commande>();
            Client client = null;
            while (resultSet.next())
            {
               client = ClientDAO.searchDOAById(resultSet.getInt("id_client"));
               Commande commande = new Commande(client , resultSet.getDate("dateC").toLocalDate(),resultSet.getString("code"));
                commandes.add(commande);
            }
 return commandes ;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    
    public static String addDBO(ArrayList<Article> articles ,int id_commande , int[] quontite)
    {
        try {
            
            for (int i = 0; i < articles.size(); i++) {
            	
            	String insert = "INSERT INTO commande_article (id_commande,id_article,quantite	) VALUES ('"+id_commande+","+articles.get(i).getId()+","+quontite[i]+"')";
            	PreparedStatement preparedStatement = connection.prepareStatement(insert);
            	preparedStatement.executeUpdate();

            }
            

          return "commande inserted into the database successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error inserting user into the database.");
        }
        return null ;
    }
    
    public static void updateArticleQuantityInCommand(int commandeId, int articleId, int newQuantity) {
        try  {
            String updateArticleQuantitySQL = "UPDATE commande_article SET quantite=? WHERE id_commande=? AND id_article=?";
            try (PreparedStatement statement = connection.prepareStatement(updateArticleQuantitySQL)) {
                statement.setInt(1, newQuantity);
                statement.setInt(2, commandeId);
                statement.setInt(3, articleId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateCommandStatus(int commandeId, String newStatus) {
        try  {
            String updateCommandStatusSQL = "UPDATE commande SET status=? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(updateCommandStatusSQL)) {
                statement.setString(1, newStatus);
                statement.setInt(2, commandeId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
    public static void addArticleToCommand(int commandeId, int articleId, int quantity) {
        try  {
            String insertArticleToCommandSQL = "INSERT INTO commande_article (id_commande, id_article, quantite) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertArticleToCommandSQL)) {
                statement.setInt(1, commandeId);
                statement.setInt(2, articleId);
                statement.setInt(3, quantity);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteArticleFromCommand(int commandeId, int articleId) {
        try  {
            String deleteArticleFromCommandSQL = "DELETE FROM commande_article WHERE id_commande=? AND id_article=?";
            try (PreparedStatement statement = connection.prepareStatement(deleteArticleFromCommandSQL)) {
                statement.setInt(1, commandeId);
                statement.setInt(2, articleId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
    
    public static ArrayList<Article> getArticlesForCommand(int commandeId) {
    	ArrayList<Article> articles = new ArrayList<>();

        try  {
            String selectArticlesSQL = "SELECT a.id, a.nom, a.quantite FROM article a " +
                                       "JOIN commande_article ca ON a.id = ca.id_article " +
                                       "WHERE ca.id_commande = ?";
            try (PreparedStatement statement = connection.prepareStatement(selectArticlesSQL)) {
                statement.setInt(1, commandeId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Article article = new Article();
                        article.setId(resultSet.getInt("id"));
                        article.setNom(resultSet.getString("nom"));
                        article.setQuontite(resultSet.getInt("quantite"));
                        articles.add(article);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return articles;
    }

}