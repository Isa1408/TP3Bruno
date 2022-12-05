package TP3Package;

import javax.lang.model.element.Element;

public class Principal {
    public static void main(String[] args) {
        ListeZone listeZone = new ListeZone();
        MaillonElement<Element> maillonElement1 = new MaillonElement<Element>(9,
                null);
        MaillonElement maillonElement2 = new MaillonElement<Element>(8, null);
       // maillonElement1.setSuivant(maillonElement2);
        MaillonElement maillonElement3 = new MaillonElement(7, null);
        MaillonZone maillonZone1 = new MaillonZone('a', maillonElement1,
                null);
        MaillonElement maillonElement4 = new MaillonElement<Element>(6, null);




        listeZone.ajouter(maillonZone1.charZone, maillonZone1.pointeurElement);
        listeZone.ajouter(maillonElement1);
        listeZone.ajouter(maillonElement2);
        listeZone.ajouter(maillonZone1.charZone, maillonElement4);
        listeZone.ajouter(maillonElement3);

        //System.out.println(listeZone.toString());

    }
}
