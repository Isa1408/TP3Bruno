package TP3Package;

public class MaillonElement<Element>{
    Integer valeur;
    MaillonElement<Element> suivant;
    MaillonElement<Element> courant;

    public MaillonElement() {
        this(null, null);
    }

    public MaillonElement(Integer valeur, MaillonElement<Element> suivant) {
        this.valeur = valeur;
        this.suivant = suivant;
    }

    public MaillonElement(MaillonElement<Element> courant) {
        //this.courant = courant;
        this.suivant = courant.suivant;
        this.valeur = courant.valeur;
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

//    @Override
//    public String toString() {
//        return "MaillonElement{" +
//                "courant=" + courant +
//                ", suivant=" + suivant +
//                '}';
//    }


    @Override
    public String toString() {
        return "MaillonElement{" +
                "valeur=" + valeur +
                ", suivant=" + suivant +
                '}';
    }
}
