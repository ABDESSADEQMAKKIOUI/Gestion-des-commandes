package com.example.DAO ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.example.Model.Client;

public class ClientDAO implements InterfaceDAO {
	 static Connection connection = DatabaseConnection.getConnection();

	    public static  String addDBO(Client client)
	    {
	        try {
	            String insertQuery = "INSERT INTO client (login , password ) VALUES (?,?)";

	            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
	            preparedStatement.setInt(1, client.getId());
	            preparedStatement.setString(2, client.getLogin());
	            preparedStatement.setString(3, client.getPassword());
	            preparedStatement.executeUpdate();


	            return "user inserted into the database successfully";
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Error inserting user into the database.");
	        }
	        return null ;
	    }
	    public static Client searchDOAById(int id)
	    {
	        try {
	            String selectQuery = "SELECT * FROM client WHERE id = ?";

	            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
	            preparedStatement.setInt(1, id);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            Client client = null;
	            if (resultSet.next())
	            {
	                client = new Client(resultSet.getInt("id"),resultSet.getString("login"),resultSet.getString("password"));
	            }else{
	                System.out.println("No user with this id "+ id);
	            }

	            return client;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Error searching for user in the database.");
	        }
	        return null;
	    }
	    public static String updateDBO(Client client) {
	        try {
	            String updateQuery = "UPDATE client SET login=?, password=? WHERE id=?";

	            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
	            preparedStatement.setString(1, client.getLogin());
	            preparedStatement.setString(2, client.getPassword());
	            preparedStatement.setInt(3, client.getId());
	            int rowsUpdated = preparedStatement.executeUpdate();

	            if (rowsUpdated > 0) {
	                return "User updated in the database successfully";
	            } else {
	                System.out.println("No user found with ID " + client.getId());
	                return "No user found with the specified ID";
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Error updating user in the database.");
	        }
	        return null;
	    }
	    public static String deleteDBO(int id) {
	        try {
	            String deleteQuery = "DELETE FROM client WHERE id=?";

	            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
	            preparedStatement.setInt(1, id);
	            int rowsDeleted = preparedStatement.executeUpdate();

	            if (rowsDeleted > 0) {
	                return "User deleted from the database successfully";
	            } else {
	                System.out.println("No user found with ID " + id);
	                return "No user found with the specified ID";
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Error deleting user from the database.");
	        }
	        return null;
	    }

	    public static Client login(String login , String motPass)
	    {
	        try {
	            String selectQuery = "SELECT * FROM client WHERE login = ? AND password=?";
	            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

	            preparedStatement.setString(1, login);
	            preparedStatement.setString(2, motPass);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            Client client = null;
	            if (resultSet.next())
	            {
	                client = new Client(resultSet.getInt("id"),resultSet.getString("login"),resultSet.getString("password"));
	                System.out.println("Votre avez login");
	            }else{
	                System.out.println("Wrong login or password !");
	            }

	            return client;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    
	    

}