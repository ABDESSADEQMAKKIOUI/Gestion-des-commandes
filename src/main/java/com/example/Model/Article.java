package com.example.Model ;

public class Article {
    private int id ;
    private int quontite ;
    private  String nom ;
    private String code ;

    public Article(int id, int quontite, String nom, String code) {
        this.id = id;
        this.quontite = quontite;
        this.nom = nom;
        this.code = code;
    }
    public Article( int quontite, String nom, String code) {
        this.quontite = quontite;
        this.nom = nom;
        this.code = code;
    }
    public Article( ) {
 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuontite() {
        return quontite;
    }

    public void setQuontite(int quontite) {
        this.quontite = quontite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}