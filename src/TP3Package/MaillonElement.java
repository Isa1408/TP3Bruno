package TP3Package;

public class MaillonElement<Element>{
    Element typeValeur;
    MaillonElement<Element> suivant;
    MaillonElement<Element> courant;

    public MaillonElement() {
        this(null, null);
    }

    public MaillonElement(Element valeur, MaillonElement<Element> suivant) {
        this.typeValeur = valeur;
        this.suivant = suivant;
    }

    public MaillonElement(MaillonElement<Element> courant) {
        //this.courant = courant;
        this.suivant = courant.suivant;
        this.typeValeur = courant.typeValeur;
    }

    public MaillonElement(Element courant) {
        //this.courant = courant;
        this.suivant = null;
        this.typeValeur = courant;
    }

    public Element getTypeValeur() {
        return typeValeur;
    }

    public void setTypeValeur(Element typeValeur) {
        this.typeValeur = typeValeur;
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
                "valeur=" + typeValeur +
                ", suivant=" + suivant +
                '}';
    }
}
