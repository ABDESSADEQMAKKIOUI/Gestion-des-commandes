package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.Model.Article;

public class ArticleDAO implements InterfaceDAO {
    static Connection connection =DatabaseConnection.getConnection();
    public static String addDBO(Article article)
    {
        try {
            String insertQuery = "INSERT INTO article (quantite , nom , code) VALUES (?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1,article.getQuontite());
            preparedStatement.setString(2, article.getNom());
            preparedStatement.setString(3, article.getCode());

            preparedStatement.executeUpdate();


          return "user inserted into the database successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error inserting user into the database.");
        }
        return null ;
    }
    
    public static String deleteDBO(int articleId) {
        try {
            String deleteQuery = "DELETE FROM article WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, articleId);
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                return "Article deleted from the database successfully";
            } else {
                System.out.println("No article found with ID " + articleId);
                return "No article found with the specified ID";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting article from the database.");
        }
        return null;
    }

    public static  ArrayList<Article> getAll()
    {
        try {
            String selectQuery = "SELECT * FROM article";

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Article> articles = new ArrayList<Article>();
            while (resultSet.next())
            {
                Article article = new Article(resultSet.getInt("id"),resultSet.getInt("quontite"),resultSet.getString("nom"),resultSet.getString("code"));
                articles.add(article);
            }
 return articles ;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    public static Article searchDOAByName(String name)
    {
        try {
            String selectQuery = "SELECT * FROM article WHERE nom = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            Article article = null;
            if (resultSet.next())
            {
                article = new Article(resultSet.getInt("id"),resultSet.getInt("quontite"),resultSet.getString("nom"),resultSet.getString("code"));
            }else{
                System.out.println("No user with this id "+ name);
            }

            return article;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error searching for user in the database.");
        }
        return null;
    } 
    
    public static void updateArticleQuantity(int articleId, int newQuantity) {
        try  {
            String updateArticleQuantitySQL = "UPDATE article SET quantite = quantite + ? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(updateArticleQuantitySQL)) {
                statement.setInt(1, newQuantity);
                statement.setInt(2, articleId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}