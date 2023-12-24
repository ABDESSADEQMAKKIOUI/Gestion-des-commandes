package com.example.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Commande {
	private int id ;
    private Client client ;
    private   ArrayList<Article> articles = new ArrayList<>();
    private LocalDate  dateC ;
    private String code ;
    private String stats ;

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public Commande(Client client, LocalDate dateC, String code) {
        this.client = client;
        this.dateC = dateC;
        this.code = code;
        this.stats  = "Pending" ;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

   

    public LocalDate getDateC() {
        return dateC;
    }

    public void setDateC(LocalDate dateC) {
        this.dateC = dateC;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    


    public static String generateRandomNumber() {
        // Generate a random number (this is a simple example, you may need a more sophisticated approach)
        return String.valueOf((int) (Math.random() * 1000));
    }

  

   

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Article> getArticles() {
		return articles;
	}

	public void setArticles(ArrayList<Article> articles) {
		this.articles = articles;
	}
}