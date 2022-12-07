package TP3Package;

import javax.lang.model.element.Element;

public class MaillonZone <Zone, Element> {

    Zone typeZone;
    MaillonElement<Zone, Element> pointeurElement;
    MaillonZone<Zone, Element> suivant;

    public MaillonZone(Zone typeZone, Element element,
                       MaillonZone<Zone, Element> suivant) {
        this.typeZone = typeZone;
        this.pointeurElement = new MaillonElement(typeZone, element);
        this.suivant = suivant;
    }


//    public char getCharZone() {
//        return charZone;
//    }
//
//    public void setCharZone(char charZone) {
//        this.charZone = charZone;
//    }

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

    @Override
    public String toString() {
        return "MaillonZone{" +
                "charZone=" + typeZone +
                ", pointeurElement=" + pointeurElement +
                ", suivant=" + suivant +
                '}';
    }
}
