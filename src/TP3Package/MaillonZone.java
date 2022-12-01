package TP3Package;

public class MaillonZone <Zone> {

    char charZone;
    MaillonElement pointeurElement;
    MaillonZone suivant;

    public MaillonZone(char charZone, MaillonElement pointeurElement,
                       MaillonZone <Zone> suivant) {
        this.charZone = charZone;
        this.pointeurElement = pointeurElement;
        this.suivant = suivant;
    }

    public char getCharZone() {
        return charZone;
    }

    public void setCharZone(char charZone) {
        this.charZone = charZone;
    }

    public MaillonElement getPointeurElement() {
        return pointeurElement;
    }

    public void setPointeurElement(MaillonElement pointeurElement) {
        this.pointeurElement = pointeurElement;
    }

    public MaillonZone getSuivant() {
        return suivant;
    }

    public void setSuivant(MaillonZone suivant) {
        this.suivant = suivant;
    }
}
