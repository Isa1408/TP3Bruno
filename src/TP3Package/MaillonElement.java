package TP3Package;

public class MaillonElement {
    Integer valeur;
    MaillonElement suivant;

    public MaillonElement() {
        this(null, null);
    }

    public MaillonElement(Integer valeur, MaillonElement suivant) {
        this.valeur = valeur;
        this.suivant = suivant;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public MaillonElement getSuivant() {
        return suivant;
    }

    public void setSuivant(MaillonElement suivant) {
        this.suivant = suivant;
    }
}
