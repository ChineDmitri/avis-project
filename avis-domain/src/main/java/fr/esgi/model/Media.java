package fr.esgi.model;

public abstract class Media {
    private String titre;
    private int    annee;

    public String getTitre() {
        return titre;
    }

    public int getAnnee() {
        return annee;
    }

    public abstract String getFullDescription();
}
