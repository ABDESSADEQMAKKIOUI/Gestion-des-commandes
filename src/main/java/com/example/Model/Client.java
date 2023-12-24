package com.example.Model;

import java.util.ArrayList;

public class Client {
    private int id;
    private String login;
    private String password;
    private ArrayList<Commande> commandes = new ArrayList<>();
    private static Client clientConnect;

    public Client(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }
    public Client( String login, String password) {
      
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(ArrayList<Commande> commandes) {
        this.commandes = commandes;
    }

    public static Client getClientConnect() {
        return clientConnect;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password.hashCode() + '\'' +
                '}';
    }
}