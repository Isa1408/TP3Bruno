package TP3Package;

public class MaillonElement<Zone, Element>{
    Element typeValeur;
    Zone zoneParent;
    MaillonElement<Zone, Element> suivant;
    MaillonElement<Zone, Element> courant;
    MaillonZone<Zone, Element> parent;

    public MaillonElement() {
        this(null, null, null);
    }

    public MaillonElement(MaillonZone<Zone, Element> parent, Element valeur,
                           MaillonElement<Zone, Element> suivant) {
        this.parent = parent;
        this.typeValeur = valeur;
        this.suivant = suivant;
    }

    public MaillonElement(MaillonElement<Zone, Element> courant) {
        this.suivant = courant.suivant;
        this.typeValeur = courant.typeValeur;
    }

    public MaillonElement(MaillonZone<Zone, Element> zoneParent,Element courant) {
        this.parent = zoneParent;
        this.suivant = null;
        this.typeValeur = courant;
    }

    public MaillonElement(Zone zoneParent,Element courant) {
        this.zoneParent = zoneParent;
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

    public void setZoneParent(Zone zoneParent) {
        this.zoneParent = zoneParent;
    }

    public void setParent(MaillonZone<Zone, Element> parent) {
        this.parent = parent;
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
