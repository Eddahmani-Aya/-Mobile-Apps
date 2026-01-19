package com.example.tp2b;

public class Stage {
    private String entreprise;
    private String poste;
    private String duree;
    private String ville;
    private int logoId;

    public Stage(String entreprise, String poste, String duree, String ville, int logoId) {
        this.entreprise = entreprise;
        this.poste = poste;
        this.duree = duree;
        this.ville = ville;
        this.logoId = logoId;
    }

    public String getEntreprise() { return entreprise; }
    public String getPoste() { return poste; }
    public String getDuree() { return duree; }
    public String getVille() { return ville; }
    public int getLogoId() { return logoId; }

    @Override
    public String toString() {
        return poste + " - " + entreprise;
    }
}