package com.example.tp2b;

public class Candidat {
    private String nom;
    private String specialite;
    private int photoId;

    public Candidat(String nom, String specialite, int photoId) {
        this.nom = nom;
        this.specialite = specialite;
        this.photoId = photoId;
    }

    public String getNom() { return nom; }
    public String getSpecialite() { return specialite; }
    public int getPhotoId() { return photoId; }
}