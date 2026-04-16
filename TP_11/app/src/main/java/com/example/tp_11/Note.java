package com.example.tp_11;

public class Note {
    public String titre;
    public String description;
    public String date;

    public Note() {} // constructeur vide nécessaire pour Firebase

    public Note(String titre, String description, String date) {
        this.titre = titre;
        this.description = description;
        this.date = date;
    }
}