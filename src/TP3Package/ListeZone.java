package TP3Package;
/**
 * @Author Isabelle Tamas
 * Code permanent: TAMI76580208
 * Présenté à: Bruno Malenfant
 * Date: 18 décembre 2022
 */

/**
 *<p>
 * Une <code>ListeZone</code> est une classe contenant deux listes en
 * parallèles.  La liste de base contient une suite d'éléments
 * de type <code>Element</code>.  La liste parallèle contient des
 * élément de type <code>Zone</code>.  Les éléments de type <code>Zone</code>
 * vont pointer sur certain élément de la liste de base.
 *</p>
 *
 * z_1     ->     z_2       ->        z_3      ->     z_4
 *  |              |                   |               |
 * a_1, a_2, a_3, a_4, a_5, a_6, a_7, a_8, a_9, a_10, a_11, a_12
 *
 *<p>
 * La liste de zone permet d'avancer plus rapidement dans la liste de base.
 * Nous définissons les éléments d'une zone comme étant ceux qui sont pointé
 * par le début d'une zone, jusqu'à la valeur précédant le marquer de la prochaine
 * zone.
 *</p><p>
 * Dans notre exemple.
 *</p>
 *<ul>
 * <li>La zone \(z_1\) représente la suite \(a_1, a_2, a_3\).</li>
 * <li>La zone \(z_2\) représente la suite \(a_4, a_5, a_6, a_7\).</li>
 * <li>La zone \(z_3\) représente la suite \(a_8, a_9, a_10\).</li>
 * <li>La zone \(z_4\) représente la suite \(a_11, a_12\)</li>
 *</ul>
 *<p>
 * Nous disons donc que \(a_1\) appartient à la zone \(z_1\) (\(a_1 \in z_1\)).
 * Par contre, \(a_5\) n'appartient pas à \(z_1\) (\(a_5 \notin z_1\)).
 *</p>
 *<p>
 * Formalisme utilisé pour décrire les méthodes.
 *</p>
 *<ol>
 * <li>\(suivant( z )\) désigne la zone qui vient immédiatement après la zone <code>z</code>.</li>
 * <li>\(m\) désigne le nombre d'élément dans la liste de zone.</li>
 * <li>\(premiere( z, e )\) désigne la première zone a partir de \(z\) contenant l'élément \(e\).  S'il n'y a pas
 de zone contenant \(e\), alors \(\bottom\) est retourné.</li>
 *</ol>
 * @param <Zone> Le type des éléments identifiant le début d'une zone.
 * @param <Element> Le type des éléments placés dans la liste de base.
 */
public class ListeZone< Zone, Element > {
    MaillonElement<Zone, Element> debutElement;
    MaillonZone<Zone, Element> debutZone;

    /**
     * Construis une nouvelle <code>ListeZone</code> vide.
     */
    public ListeZone() {
        debutZone = null;
        debutElement = null;
    }

    /**
     * Ajoute un élément à la fin de la liste de base.
     * Si la liste ne contient pas d'élément, cela lance l'exception
     * IndexOutOfBoundsException.  L'élément est donc ajouté dans la dernière zone.
     * @param e l'élément ajouté à la fin de la liste de base.
     */
    public void ajouter( Element e ) throws IndexOutOfBoundsException{
        MaillonElement<Zone, Element> courant = debutElement;
        MaillonZone<Zone, Element> finZone = debutZone;
        MaillonElement courantElement;
        MaillonElement precedentElement = null;

        if (debutZone == null){
            throw new IndexOutOfBoundsException();
        }else {
            if(courant != null){
                while (courant.suivant != null){
                    courant = courant.suivant;
                }
                while (finZone.suivant != null){
                    finZone = finZone.suivant;
                }

                courantElement = finZone.pointeurElement;
                while (courantElement != null){
                    precedentElement = courantElement;
                    courantElement = courantElement.suivant;
                }
                precedentElement.setSuivant(new MaillonElement<>(finZone, e));
            }
            courant.setSuivant(new MaillonElement<Zone, Element>(finZone, e));
        }
    }

    /**
     * Ajoute un élément à la fin de la zone indiquée.
     * Si la zone n'est pas présente, alors la zone est ajoutée à la fin de la
     * liste de zone et l'élément devient le premier élément de la zone.
     * @param z l'identificateur pour la zone.
     * @param e le premier élément de la nouvelle zone.
     */
    public void ajouter( Zone z, Element e ) {
        MaillonZone courantZone = debutZone;
        MaillonZone precedentZone = null;
        MaillonZone<Zone, Element> nouvelleZone = null;
        MaillonElement nouveauElement;
        MaillonElement elementTemp;
        MaillonElement precedentElementAvantProchaineZone = null;
        MaillonElement premierElementDansProchaineZone;
        MaillonZone prochaineZone;

        while (courantZone != null && courantZone.typeZone != z ){
            precedentZone = courantZone;
            courantZone = courantZone.suivant;
        }
        if(courantZone != null){
            if(courantZone.suivant != null){
                prochaineZone = courantZone.suivant;
                premierElementDansProchaineZone = prochaineZone.getPointeurElement();
                elementTemp = debutElement;

                while (elementTemp != null && elementTemp.typeValeur
                        != premierElementDansProchaineZone.typeValeur ){
                    precedentElementAvantProchaineZone = elementTemp;
                    elementTemp = elementTemp.suivant;
                }
                nouveauElement = new MaillonElement<Zone, Element>(z, e);
                nouveauElement.setSuivant(elementTemp);
                precedentElementAvantProchaineZone.setSuivant(nouveauElement);
            }else {
                ajouter(e);
            }
        }else {
            ajouterSiNouvelleZone(z, e, precedentZone);
        }
    }

    /**
     * Ajoute la zone et l'élément indiqués à la fin de la liste de zone et
     * l'élément devient le premier élément de la zone.
     *
     * @param z l'identificateur pour la zone.
     * @param e le premier élément de la nouvelle zone.
     * @param precedentZone la zone qui précède la zone z.
     */
    private void ajouterSiNouvelleZone(Zone z, Element e,
                                       MaillonZone precedentZone) {
        MaillonElement courantElement = debutElement;
        MaillonElement precedentElement = null;
        MaillonZone<Zone, Element> nouvelleZone;
        if(debutZone == null){
            nouvelleZone = new MaillonZone<>(z,
                    e, null);
            debutZone = nouvelleZone;
            debutElement = nouvelleZone.getPointeurElement();
        }else {
            precedentZone.setSuivant(new MaillonZone<>(z, e, null));
            while (courantElement != null){
                precedentElement = courantElement;
                courantElement = courantElement.suivant;
            }
            precedentElement.setSuivant(new MaillonElement<>(z, e));
        }
    }

    /**
     * Enleve le premier élément égal à <code>e</code> dans la zone <code>z</code>.
     *
     * Si la zone ne contient pas l'élément, alors rien n'est enlevé.
     * Si l'élément est le seul élément de la zone, alors cela enlève aussi la zone.
     * Si l'élément est le premier élément de la zone, cela fait avancer le pointeur de zone.
     * Voir les règles de la méthode pour avancer.
     * @param z zone dans lequelle l'élément est enlevé
     * @param e l'élément à enlevé.
     */
    public void enlever( Zone z, Element e ) {
        MaillonZone courantZone = debutZone;
        MaillonZone precedentZone = null;
        boolean unTour = true;
        boolean fini;

        if(z != null && e != null){
            while (courantZone != null && courantZone.typeZone != z ){
                precedentZone = courantZone;
                courantZone = courantZone.suivant;
                unTour = false;
            }

            if(courantZone != null){
                fini = seulElementDeLaZone(e, courantZone, precedentZone, unTour);

                if(courantZone.pointeurElement.typeValeur == e && !fini){
                    avancer(z);
                }
            }
        }
    }

    /**
     * Savoir si e est le seul élément de la zone.
     *
     * @param e l'élément à enlevé
     * @param courantZone la zone courante
     * @param precedentZone la zone qui précède la zone courante
     * @param unTour false si la zone z n'est pas la première dans la liste
     * @return true si z n'est pas la première zone
     */
    private boolean seulElementDeLaZone(Element e, MaillonZone courantZone,
                                        MaillonZone precedentZone,
                                        boolean unTour) {
        boolean fini = false;
        if(courantZone.pointeurElement.typeValeur == e
                && courantZone.pointeurElement.zoneParent != null
                && courantZone.pointeurElement.suivant != null
                && courantZone.pointeurElement.suivant.zoneParent != null
                && !courantZone.pointeurElement.zoneParent.toString()
                .equals(courantZone.pointeurElement.suivant.zoneParent.toString())){

            if(!unTour){
                fini = zNestPasLaPremiereZone(courantZone, precedentZone);
            }
        }
        return fini;
    }

    /**
     * Savoir si z est la première zone dans la liste.
     *
     * @param courantZone la zone courante
     * @param precedentZone la zone qui précède la zone courante
     * @return true si z n'est pas la première zone de la liste
     */
    private static boolean zNestPasLaPremiereZone(MaillonZone courantZone,
                                                  MaillonZone precedentZone) {
        MaillonElement precedentElement = null;
        MaillonElement elementPrecedentZone;
        boolean fini = false;

        elementPrecedentZone =
                precedentZone.pointeurElement;
        while (elementPrecedentZone.suivant != null && (elementPrecedentZone.zoneParent != null
                && !elementPrecedentZone.zoneParent.toString().equals(courantZone.typeZone.toString()))
                || (elementPrecedentZone.parent != null
                && !elementPrecedentZone.parent.toString().equals(courantZone.typeZone.toString()))){
            precedentElement = elementPrecedentZone;
            elementPrecedentZone = elementPrecedentZone.suivant;
        }
        if(precedentElement != null){
            precedentElement.setSuivant(courantZone.pointeurElement);
            precedentElement.suivant.setZoneParent(precedentZone.typeZone);
            courantZone.setPointeurElement(courantZone.pointeurElement.suivant);
            if(courantZone.pointeurElement == null){
                precedentZone.setSuivant(courantZone.suivant);
                fini = true;
            }
        }
        return fini;
    }

    /**
     * Avance le pointeur de la zone <code>z</code> afin qu'il pointe sur le
     * prochain élément de la liste de base.
     *
     * L'élément qui était pointé par le pointeur de zone devient le dernier élément de la zone précédante.
     * Si le pointeur de zone pointait sur le dernier élément de la zone, alors le pointeur de zone
     * est supprimé.
     * Si le pointeur de zone pointait sur le premier élément de la liste de base, alors le premier
     * élément de la liste de base est supprimé.
     * @param z
     */
    public void avancer( Zone z ) {
        MaillonZone courantZone = debutZone;
        MaillonZone precedentZone = null;
        MaillonElement elementPrecedentZone = null;
        boolean unTour = true;

        if(z != null){
            while (courantZone != null && courantZone.typeZone != z ){
                precedentZone = courantZone;
                courantZone = courantZone.suivant;
                unTour = false;
            }

            if(courantZone != null){
                if(unTour){
                    if(courantZone.pointeurElement.zoneParent != null
                            && courantZone.pointeurElement.suivant.zoneParent != null
                            && !courantZone.pointeurElement.zoneParent.toString()
                            .equals(courantZone.pointeurElement.suivant.zoneParent.toString())){
                        debutZone = courantZone.suivant;
                        debutElement = courantZone.suivant.pointeurElement;
                        courantZone.setPointeurElement(null);
                        courantZone.setSuivant(null);
                    }else {
                        courantZone.pointeurElement =
                                courantZone.pointeurElement.suivant;
                    }
                }else {
                    zNestPasLePremier(courantZone, precedentZone);
                }
            }
        }
    }

    /**
     * Pointer la zone vers l'élément qui lui correspond.
     * Si l'élément e est le dernier dans la zone z, alors le pointeur
     * de zone est supprimé.
     * Si non, l'élément qui était pointé par le pointeur de zone devient le
     * dernier élément de la zone précédante.
     *
     * @param courantZone la zone courante (z)
     * @param precedentZone la zone qui précède la zone courante
     */
    private static void zNestPasLePremier(MaillonZone courantZone,
                                          MaillonZone precedentZone) {
        MaillonElement elementPrecedentZone;
        MaillonElement precedentElement = null;
        if(courantZone.suivant == null
                && courantZone.pointeurElement.suivant == null){
            precedentZone.suivant = null;
            elementPrecedentZone =
                    precedentZone.pointeurElement.suivant;

            while (elementPrecedentZone.suivant != null){
                precedentElement = elementPrecedentZone;
                elementPrecedentZone = elementPrecedentZone.suivant;
            }

            if(precedentElement != null){
                precedentElement.suivant = null;
            }

        }else {
            nEstPasDernierElement(courantZone, precedentZone);
        }
    }

    /**
     * Pointer la zone vers l'élément qui lui correspond, c'est-à-dire que
     * l'élément qui était pointé par le pointeur de zone devient le dernier
     * élément de la zone précédante.
     *
     * @param courantZone la zone courante (z)
     * @param precedentZone la zone qui précède la zone courante
     */
    private static void nEstPasDernierElement(MaillonZone courantZone,
                                              MaillonZone precedentZone) {
        MaillonElement elementPrecedentZone;
        MaillonElement precedentElement = null;

        elementPrecedentZone =
                precedentZone.pointeurElement;
        while (elementPrecedentZone.suivant != null && (elementPrecedentZone.zoneParent != null
                && !elementPrecedentZone.zoneParent.toString().equals(courantZone.typeZone.toString()))
                || (elementPrecedentZone.parent != null
                && !elementPrecedentZone.parent.toString().equals(courantZone.typeZone.toString()))){
            precedentElement = elementPrecedentZone;
            elementPrecedentZone = elementPrecedentZone.suivant;
        }
        if(precedentElement != null){
            precedentElement.setSuivant(courantZone.pointeurElement);
            precedentElement.suivant.setZoneParent(precedentZone.typeZone);
            courantZone.setPointeurElement(courantZone.pointeurElement.suivant);
            if(courantZone.pointeurElement == null){
                precedentZone.setSuivant(courantZone.suivant);
            }
        }
    }

    /**
     * Vérifie si l'élément <code>e</code> peut être trouvé dans la
     * zone suivant la zone <code>z</code>.  S'il n'y a pas de zone
     * après <code>z</code>, alors <code>false</code> est retourné.
     * @param z l'élément est recherché dans la zone immédiatement après <code>z</code>.
     * @param e l'élément recherché.
     * @return \(e \in suivant( z )\)
     */
    public boolean contientX( Zone z, Element e ) {
        MaillonZone courantZone = debutZone;
        MaillonZone precedentZone = null;
        MaillonZone nouvelleZone = null;
        MaillonZone prochaineZone;
        MaillonElement elementDansProchaineZone;
        boolean elementTrouve = false;
        boolean existeProchaineZone = false;
        if (z != null && e != null){
            while (courantZone != null && courantZone.typeZone != z ){
                precedentZone = courantZone;
                courantZone = courantZone.suivant;
            }

            if(courantZone != null){
                if (courantZone.suivant != null){
                    existeProchaineZone = true;

                    elementTrouve = trouverLElement(e, courantZone);
                }
            }else {
                elementTrouve = false;
            }

        }
        if(!existeProchaineZone){
            elementTrouve = false;
        }
        return elementTrouve;
    }

    /**
     * Trouver l'élément e. S'il n'est pas le premier élément de la zone qui
     * suit z,alors il faut itérer dans les éléments de la zone qui suit z pour
     * trouver e.
     *
     * @param e l'élément recherché
     * @param courantZone la zone courante (z)
     * @return true si l'élément recherché est trouvé
     */
    private boolean trouverLElement(Element e, MaillonZone courantZone) {
        MaillonElement elementDansProchaineZone;
        MaillonZone prochaineZone;
        MaillonElement precedentElement = null;
        MaillonElement elementDansCourantZone;
        boolean elementTrouve = false;

        courantZone = courantZone.suivant;
        elementDansCourantZone = courantZone.pointeurElement;

        if(courantZone.pointeurElement.typeValeur == e){
            elementTrouve = true;
        }else {
            if(courantZone.suivant != null){
                elementTrouve = existeProchaineZone(e, courantZone,
                        elementDansCourantZone);
            }else {
                while (elementDansCourantZone != null
                        && elementDansCourantZone.typeValeur != e
                        && elementDansCourantZone.typeValeur != null){
                    precedentElement = elementDansCourantZone;
                    elementDansCourantZone =
                            elementDansCourantZone.suivant;
                }
                if( elementDansCourantZone != null
                        && elementDansCourantZone.typeValeur == e){
                    elementTrouve = true;
                }
            }
        }
        return elementTrouve;
    }

    /**
     * Trouver l'élément e dans la zone qui suit z.
     *
     * @param e l'élément recherché
     * @param courantZone la zone qui suit la zone z
     * @param elementDansCourantZone le premier élément de la zone qui suit z
     * @return true si l'élément est trouvé
     */
    private boolean existeProchaineZone(Element e, MaillonZone courantZone,
                                        MaillonElement elementDansCourantZone) {
        MaillonZone prochaineZone;
        MaillonElement elementDansProchaineZone;
        MaillonElement precedentElement = null;
        boolean elementTrouve = false;

        prochaineZone = courantZone.suivant;
        elementDansProchaineZone = prochaineZone.pointeurElement;
        while (elementDansCourantZone != null
                && elementDansCourantZone != e
                && elementDansCourantZone.typeValeur
                != elementDansProchaineZone.typeValeur ){
            precedentElement = elementDansCourantZone;
            elementDansCourantZone =
                    elementDansCourantZone.suivant;
        }

        if( precedentElement != null && precedentElement.typeValeur == e){
            elementTrouve = true;
        }
        return elementTrouve;
    }

    /**
     * Vérifie si l'élément <code>e</code> peut être trouvé dans la zone
     * <code>z</code> OU une zone suivant la zone <code>z</code>.
     * @param z la zone où la recherche commence.
     * @param e l'élément recherché.
     * @return \(\exists z_i \in [z..z_m], e \in z_i\)
     */
    public boolean contientF( Zone z, Element e ) {
        MaillonZone courantZone = debutZone;
        MaillonZone precedentZone = null;
        MaillonElement elementDansCourantZone = null;
        MaillonZone prochaineZone;
        MaillonElement elementDansProchaineZone;
        MaillonElement precedentElement = null;
        boolean existeProchaineZone = false;
        boolean trouve = false;

        if (z != null && e != null) {
            while (courantZone != null && courantZone.typeZone != z) {
                precedentZone = courantZone;
                courantZone = courantZone.suivant;
            }
            if (courantZone != null) {
                if (courantZone.suivant != null) {
                    prochaineZone = courantZone.suivant;
                    elementDansProchaineZone =
                            prochaineZone.getPointeurElement();
                    elementDansCourantZone = courantZone.getPointeurElement();

                    if (courantZone.pointeurElement.typeValeur == e || elementDansProchaineZone.typeValeur == e) {
                        trouve = true;
                    } else {
                        trouve = elementNestPasLePremierDansLaZone(e, courantZone, elementDansCourantZone, prochaineZone, elementDansProchaineZone);
                    }

                } else {
                    trouve = trouverElementDansDerniereZone(courantZone, e);
                }
            }
        }
        return trouve;
    }

    /**
     * Trouver l'élément dans la zone z. Si l'élément recherché n'est pas
     * trouvé, aller le chercher dans les prochaines zones.
     *
     * @param e élément recherché
     * @param courantZone la zone courante (z)
     * @param elementDansCourantZone le premier élément dans la zone courante
     * @param prochaineZone la zone qui suit la zone z
     * @param elementDansProchaineZone le premier élément de la zone qui suit z
     * @return true si l'élément est trouvé
     */
    private boolean elementNestPasLePremierDansLaZone(Element e,
                                                      MaillonZone courantZone,
                                                      MaillonElement elementDansCourantZone,
                                                      MaillonZone prochaineZone,
                                                      MaillonElement elementDansProchaineZone) {
        boolean trouve;
        MaillonElement precedentElement;
        precedentElement = elementDansCourantZone;
        if(elementDansCourantZone.suivant != null){
            elementDansCourantZone = elementDansCourantZone.suivant;
        }

        while (elementDansCourantZone.typeValeur != e &&
                elementDansCourantZone.typeValeur != elementDansProchaineZone.typeValeur && elementDansCourantZone.suivant != null) {
            precedentElement = elementDansCourantZone;
            elementDansCourantZone =
                    elementDansCourantZone.suivant;
        }
        if (precedentElement.typeValeur == e || elementDansCourantZone.typeValeur == e) {
            trouve = true;
        }else {
            trouve = TrouveDansProchaineZone(e, courantZone, prochaineZone);
        }
        return trouve;
    }

    /**
     * Trouver l'élément dans la dernière zone de la liste.
     *
     * @param courantZone la zone courante (z)
     * @param e l'élément recherché
     * @return true si l'élément est trouvé
     */
    private boolean trouverElementDansDerniereZone(MaillonZone courantZone, Element e) {
        boolean trouve = false;
        MaillonElement elementDansCourantZone;
        elementDansCourantZone = courantZone.pointeurElement;
        if (elementDansCourantZone.typeValeur == e){
            trouve = true;
        }

        trouve = chercherDansDerniereZone(e, elementDansCourantZone, trouve);
        return trouve;
    }

    /**
     * Vérifie si l'élément peut être trouvé dans les prochaines zones.
     *
     * @param e l'élément recherché
     * @param courantZone la zone courante (z)
     * @param prochaineZone la zone qui suit z
     * @return true si l'élément est trouvé
     */
    private boolean TrouveDansProchaineZone(Element e, MaillonZone courantZone, MaillonZone prochaineZone) {
        MaillonElement elementDansProchaineZone;
        MaillonElement elementDansCourantZone;
        boolean existeProchaineZone;
        boolean trouve = false;
        boolean trouveDansProchaineZone = false;

        while(!trouveDansProchaineZone && prochaineZone.suivant != null){
            prochaineZone = courantZone;
            existeProchaineZone = true;
            elementDansProchaineZone = prochaineZone.pointeurElement;
            elementDansCourantZone = courantZone.pointeurElement;

            if(courantZone.pointeurElement.typeValeur == e){
                trouve = true;
                trouveDansProchaineZone = true;
            }else {
                if(elementDansCourantZone.suivant != null){
                    elementDansCourantZone = elementDansCourantZone.suivant;
                }
                while (elementDansCourantZone.suivant != null && elementDansCourantZone.typeValeur != e &&
                        elementDansCourantZone.typeValeur != elementDansProchaineZone.typeValeur){
                    elementDansCourantZone =
                            elementDansCourantZone.suivant;
                }
                if(elementDansCourantZone.typeValeur == e){
                    trouveDansProchaineZone = true;
                }else {
                    trouveDansProchaineZone = false;
                    courantZone = courantZone.suivant;
                }
            }
        }
        return trouve;
    }

    /**
     * Vérifie si l'élément <code>e</code> peut être trouvé dans la
     * zone <code>z</code> ET dans chaque zone suivante.
     *
     * L'élément doit être trouvé dans chaque zone à partir
     * de <code>z</code>.
     * @param z la zone où la recherche commence.
     * @param e l'élément recherché.
     * @return \(\forall z_i \in [z..z_m], e \in z_i\)
     */
    public boolean contientG( Zone z, Element e ) {
        MaillonZone courantZone = debutZone;
        MaillonZone precedentZone = null;
        MaillonZone nouvelleZone = null;
        MaillonElement elementDansCourantZone;
        MaillonZone prochaineZone;
        MaillonElement elementDansProchaineZone;
        MaillonElement precedentElement = null;
        boolean trouveDansZoneInit = false;
        boolean trouveDansToutesProchainesZones = false;
        boolean trouveDansProchaineZone = false;
        boolean existeProchaineZone = false;
        boolean trouve = false;

        if(z != null && e != null){
            courantZone = TrouverMaillonZone(courantZone, z, precedentZone);

            if(courantZone != null){
                if (courantZone.suivant != null){
                    existeProchaineZone = true;
                    prochaineZone = courantZone.suivant;
                    elementDansProchaineZone =
                            prochaineZone.getPointeurElement();
                    elementDansCourantZone = courantZone.getPointeurElement();
                    trouveDansZoneInit = TrouveDansZoneInit(e, courantZone, elementDansCourantZone, elementDansProchaineZone);

                    if(trouveDansZoneInit){
                        courantZone = prochaineZone;
                        if(courantZone.suivant != null){
                            existeProchaineZone = true;
                            trouveDansToutesProchainesZones = true;
                            while(trouveDansToutesProchainesZones && prochaineZone.suivant != null){
                                prochaineZone = courantZone;
                                existeProchaineZone = true;
                                elementDansProchaineZone = prochaineZone.pointeurElement;
                                elementDansCourantZone = courantZone.pointeurElement;

                                if(courantZone.pointeurElement.typeValeur == e){
                                    trouveDansZoneInit = true;
                                    courantZone = courantZone.suivant;
                                }else {
                                    prochaineZone = courantZone.suivant;
                                    if(prochaineZone != null){
                                        elementDansProchaineZone = prochaineZone.pointeurElement;
                                    }

                                    elementDansCourantZone = TrouverMaillonElement(e, elementDansCourantZone, elementDansProchaineZone);
                                    trouveDansToutesProchainesZones = ElementDansToutesLesZones(e, elementDansCourantZone, trouveDansToutesProchainesZones);
                                }
                            }
                        }else {
                            trouve = trouverElementDansDerniereZone(courantZone, e);
                        }
                    }
                }else{
                    elementDansCourantZone = courantZone.pointeurElement;
                    if (elementDansCourantZone.typeValeur == e){
                        trouveDansZoneInit = true;
                        trouve = true;
                    }

                    trouve = chercherDansDerniereZone(e, elementDansCourantZone, trouve);
                }
            }
        }
        trouve = validerElement(trouveDansZoneInit, trouveDansToutesProchainesZones, existeProchaineZone, trouve);
        return trouve;
    }

    /**
     * Chercher l'élément dans la zone z.
     *
     * @param e l'élément recherché
     * @param courantZone la zone courante (z)
     * @param elementDansCourantZone le premier élément dans la zone courante
     * @param elementDansProchaineZone le premier élément dans la zone qui
     *                                 suit la zone courante
     * @return true si l'élément est trouvé
     */
    private boolean TrouveDansZoneInit(Element e, MaillonZone courantZone, MaillonElement elementDansCourantZone, MaillonElement elementDansProchaineZone) {
        boolean trouveDansZoneInit;
        if(courantZone.pointeurElement.typeValeur == e){
            trouveDansZoneInit = true;
        }else {
            trouveDansZoneInit = ChercherDansZ(e, elementDansCourantZone, elementDansProchaineZone);
        }
        return trouveDansZoneInit;
    }

    /**
     * Chercher l'élément dans la zone courante.
     *
     * @param e l'élément recherché
     * @param elementDansCourantZone l'élément dans la zone courante
     * @param elementDansProchaineZone le premier élément dans la prochaine zone
     * @return l'élément
     */
    private MaillonElement TrouverMaillonElement(Element e, MaillonElement elementDansCourantZone, MaillonElement elementDansProchaineZone) {
        while (elementDansCourantZone.suivant != null && elementDansCourantZone.typeValeur != e &&
                elementDansCourantZone.typeValeur != elementDansProchaineZone.typeValeur ){
            elementDansCourantZone =
                    elementDansCourantZone.suivant;
        }
        return elementDansCourantZone;
    }

    /**
     * Chercher la zone z dans la liste de zones.
     *
     * @param courantZone la première zone de la liste de zones
     * @param z la zone recherchée
     * @param precedentZone la zone qui précède z
     * @return la zone z
     */
    private MaillonZone TrouverMaillonZone(MaillonZone courantZone, Zone z, MaillonZone precedentZone) {
        while (courantZone != null && courantZone.typeZone != z){
            precedentZone = courantZone;
            courantZone = courantZone.suivant;
        }
        return courantZone;
    }

    /**
     * Chercher l'élément e dans la derniere zone.
     *
     * @param e l'élément recherché
     * @param elementDansCourantZone le premier élément dans la zone courante
     * @param trouve true si l'élément est trouvé
     * @return true si l'élément est trouvé
     */
    private boolean chercherDansDerniereZone(Element e, MaillonElement elementDansCourantZone, boolean trouve) {
        if(!trouve){
            while (elementDansCourantZone.suivant != null && elementDansCourantZone.typeValeur != e){
                elementDansCourantZone = elementDansCourantZone.suivant;
            }

            if (elementDansCourantZone.typeValeur == e){
                trouve = true;
            }
        }
        return trouve;
    }

    /**
     * Valider si l'élément recherché se trouve dans la zone z et dans
     * chacune des suivantes.
     *
     * @param trouveDansZoneInit true si l'élément a été trouvé dans la zone z
     * @param trouveDansToutesProchainesZones true si l'élément a été trouvé
     *                                        dans les zones qui suivent z
     * @param existeProchaineZone true si z n'est pas la dernière zone
     * @param trouve true si l'élément a été trouvé
     * @return true si l'élément se trouve dans la zone z et dans chacune des
     * zones suivantes
     */
    private static boolean validerElement(boolean trouveDansZoneInit, boolean trouveDansToutesProchainesZones, boolean existeProchaineZone, boolean trouve) {
        if(existeProchaineZone && trouveDansZoneInit && trouveDansToutesProchainesZones){
            trouve = true;
        }

        if(!existeProchaineZone && trouveDansZoneInit){
            trouve = true;
        }
        return trouve;
    }

    /**
     * Chercher l'élément e dans la zone z.
     *
     * @param e l'élément recherché
     * @param elementDansCourantZone le premier élément de la zone z
     * @param elementDansProchaineZone le premier élément de la zone qui suit z
     * @return true si l'élément a été trouvé dans la zone z
     */
    private boolean ChercherDansZ(Element e, MaillonElement elementDansCourantZone, MaillonElement elementDansProchaineZone) {
        MaillonElement precedentElement = null;
        boolean trouveDansZoneInit = false;

        while (elementDansCourantZone != e &&
                elementDansCourantZone.typeValeur != elementDansProchaineZone.typeValeur && elementDansCourantZone.suivant != null){
            precedentElement = elementDansCourantZone;
            elementDansCourantZone =
                    elementDansCourantZone.suivant;
        }
        if(precedentElement.typeValeur == e){
            trouveDansZoneInit = true;
        }
        return trouveDansZoneInit;
    }

    /**
     * Valider que l'élément est trouvé dans toutes les prochaines zones qui
     * suivent z.
     *
     * @param e l'élément recherché
     * @param elementDansCourantZone l'élément dans la zone courante
     * @param trouveDansToutesProchainesZones true si l'élément a été trouvé
     *                                        dans toutes les zones apres z
     * @return true si si l'élément a été trouvé dans la zone apres z
     */
    private boolean ElementDansToutesLesZones(Element e, MaillonElement elementDansCourantZone, boolean trouveDansToutesProchainesZones) {
        boolean trouveDansProchaineZone;
        if(elementDansCourantZone.typeValeur == e){
            trouveDansProchaineZone = true;
            trouveDansToutesProchainesZones = true;
        }else {
            trouveDansProchaineZone = false;
        }

        if (!trouveDansProchaineZone){
            trouveDansToutesProchainesZones = false;
        }
        return trouveDansToutesProchainesZones;
    }

    /**
     * Vérifie que l'élément <code>e2</code> se trouve dans <code>z</code> ou dans
     * une zone suivant la zone <code>z</code>.
     * Aussi, l'élément <code>e1</code> doit être présent dans chaque
     * zone entre <code>z</code> (inclusif) et la zone où <code>e2</code> a été trouvé (exclusif).
     * L'élément <code>e1</code> peut être dans la zone où <code>e2</code> est,
     * mais ce n'est pas necéssaire pour donner vrai.
     * @param z la zone de départ pour la recherche.
     * @param e1 l'élément à trouver dans chaque zone intermédiaire.
     * @param e2 l'élément à trouver dans une zone à partir de la zone
     *           <code>z</code>.
     * @return \(z_k = premiere( z, e2 ), r_k \not= \bottom \wedge \forall z_i \in [z..z_k[, e1 \in z_i\)
     */
    public boolean contientU( Zone z, Element e1, Element e2 ) {
        MaillonZone courantZone = debutZone;
        MaillonZone zoneInit = null;
        MaillonZone zoneE2Trouve = null;
        MaillonZone precedentZone = null;
        MaillonElement elementDansCourantZone;
        MaillonZone prochaineZone;
        MaillonElement elementDansProchaineZone;
        MaillonElement precedentElement = null;
        boolean trouveDansProchaineZone = false;
        boolean existeProchaineZone = false;
        boolean lesDeuxTrouve = false;
        boolean e2Trouve = false;
        boolean e1Trouve = false;
        boolean e1TrouveDansChaqueZones = false;
        boolean unTour = true;

        if (z != null && e2 != null && e1 != null) {
            while (courantZone != null && courantZone.typeZone != z) {
                unTour = false;
                precedentZone = courantZone;
                courantZone = courantZone.suivant;
            }
            if (courantZone != null) {
                zoneInit = courantZone;
                if (courantZone.suivant != null) {
                    prochaineZone = courantZone.suivant;
                    elementDansProchaineZone =
                            prochaineZone.getPointeurElement();
                    elementDansCourantZone = courantZone.getPointeurElement();

                    if (courantZone.pointeurElement.typeValeur == e2 || elementDansProchaineZone.typeValeur == e2) {
                        e2Trouve = true;
                        if(courantZone.pointeurElement.typeValeur == e1 || elementDansProchaineZone.typeValeur == e1){
                            e1Trouve = true;
                            lesDeuxTrouve = true;
                        }
                        if(unTour){
                            lesDeuxTrouve = true;
                        }
                    } else {
                        precedentElement = elementDansCourantZone;
                        if(elementDansCourantZone.suivant != null){
                            elementDansCourantZone = elementDansCourantZone.suivant;
                        }

                        while (elementDansCourantZone.typeValeur != e2 &&
                                elementDansCourantZone.typeValeur != elementDansProchaineZone.typeValeur && elementDansCourantZone.suivant != null) {
                            precedentElement = elementDansCourantZone;
                            elementDansCourantZone =
                                    elementDansCourantZone.suivant;
                        }

                        if (precedentElement.typeValeur == e2 || elementDansCourantZone.typeValeur == e2) {
                            e2Trouve = true;
                            if(unTour){
                                lesDeuxTrouve = true;
                            }
                        }else {
                            prochaineZone = courantZone;
                            while(!trouveDansProchaineZone && prochaineZone.suivant != null){
                                prochaineZone = courantZone;
                                existeProchaineZone = true;
                                elementDansProchaineZone = prochaineZone.pointeurElement;
                                elementDansCourantZone = courantZone.pointeurElement;

                                if(courantZone.pointeurElement.typeValeur == e2){
                                    e2Trouve = true;
                                    trouveDansProchaineZone = true;
                                }else {
                                    if(elementDansCourantZone.suivant != null){
                                        elementDansCourantZone = elementDansCourantZone.suivant;
                                    }
                                    while (elementDansCourantZone.suivant != null && elementDansCourantZone.typeValeur != e2 &&
                                            elementDansCourantZone.typeValeur != elementDansProchaineZone.typeValeur){
                                        elementDansCourantZone =
                                                elementDansCourantZone.suivant;
                                    }
                                    if(elementDansCourantZone.typeValeur == e2){
                                        e2Trouve = true;
                                        trouveDansProchaineZone = true;
                                    }else {
                                        trouveDansProchaineZone = false;
                                        courantZone = courantZone.suivant;
                                    }
                                }
                            }
                        }
                    }
                    if(e2Trouve && !lesDeuxTrouve){
                        zoneE2Trouve = courantZone;
                        courantZone = zoneInit;

                        if(courantZone.suivant != null){
                            existeProchaineZone = true;
                            e1TrouveDansChaqueZones = true;

                            while(prochaineZone.suivant != null && e1TrouveDansChaqueZones
                                    && prochaineZone.suivant.pointeurElement.typeValeur != zoneE2Trouve.pointeurElement.typeValeur){
                                prochaineZone = courantZone;
                                existeProchaineZone = true;
                                elementDansProchaineZone = prochaineZone.pointeurElement;
                                elementDansCourantZone = courantZone.pointeurElement;

                                if(courantZone.pointeurElement.typeValeur == e1){
                                    courantZone = courantZone.suivant;
                                }else {
                                    prochaineZone = courantZone.suivant;

                                    if(prochaineZone != null){
                                        elementDansProchaineZone = prochaineZone.pointeurElement;
                                    }

                                    while (elementDansCourantZone.suivant != null && elementDansCourantZone.typeValeur != e1 &&
                                            elementDansCourantZone.typeValeur != elementDansProchaineZone.typeValeur ){
                                        elementDansCourantZone =
                                                elementDansCourantZone.suivant;
                                    }
                                    if(elementDansCourantZone.typeValeur == e1){
                                        trouveDansProchaineZone = true;
                                        e1TrouveDansChaqueZones = true;
                                    }else {
                                        trouveDansProchaineZone = false;
                                    }

                                    if (!trouveDansProchaineZone){
                                        e1TrouveDansChaqueZones = false;
                                    }
                                }
                            }
                        }else {
                            lesDeuxTrouve = trouverElementDansDerniereZone(courantZone, e1);
                        }
                    }
                } else {
                    lesDeuxTrouve = trouverElementDansDerniereZone(courantZone, e2);
                }
            }
        }
        if(e2Trouve && e1TrouveDansChaqueZones){
            lesDeuxTrouve = true;
        }
        return lesDeuxTrouve;
    }


    /**
     * Vérifie que l'élément <code>e2</code> ce trouve dans <code>z</code> ou dans
     * une zone suivant la zone <code>z</code>.
     * Aussi, l'élément <code>e1</code> doit être présent dans chaque
     * zone entre <code>z</code> (inclusif) et la zone où <code>e2</code> a été trouvé (inclusif).
     * L'élément <code>e1</code> doit être dans la zone où <code>e2</code> est.
     * @param z la zone de départ pour la recherche.
     * @param e1 l'élément à trouver dans chaque zone intermédiaire.
     * @param e2 l'élément à trouver dans une zone à partir de la zone
     *           <code>z</code>.
     * @return \(z_k = premiere( z, e2 ), r_k \not= \bottom \wedge \forall z_i \in [z..z_k], e1 \in z_i\)
     */
    public boolean contientM( Zone z, Element e1, Element e2 ) {
        MaillonZone courantZone = debutZone;
        MaillonZone zoneInit = null;
        MaillonZone zoneE2Trouve = null;
        MaillonZone precedentZone = null;
        MaillonElement elementDansCourantZone;
        MaillonZone prochaineZone;
        MaillonElement elementDansProchaineZone;
        MaillonElement precedentElement = null;
        boolean trouveDansProchaineZone = false;
        boolean e1TrouveDansChaqueZones = false;
        boolean existeProchaineZone = false;
        boolean lesDeuxTrouve = false;
        boolean e2Trouve = false;
        boolean e1Trouve = false;
        boolean e1TrouveDansProchaineZone = false;
        boolean unTour = true;

        if (z != null && e2 != null && e1 != null) {
            while (courantZone != null && courantZone.typeZone != z) {
                unTour = false;
                precedentZone = courantZone;
                courantZone = courantZone.suivant;
            }
            if (courantZone != null) {
                zoneInit = courantZone;
                if (courantZone.suivant != null) {
                    prochaineZone = courantZone.suivant;
                    elementDansProchaineZone =
                            prochaineZone.getPointeurElement();
                    elementDansCourantZone = courantZone.getPointeurElement();

                    if (courantZone.pointeurElement.typeValeur == e2 || elementDansProchaineZone.typeValeur == e2) {
                        e2Trouve = true;
                        if(courantZone.pointeurElement.typeValeur == e1 || elementDansProchaineZone.typeValeur == e1){
                            e1Trouve = true;
                        }
                        if(unTour){
                            lesDeuxTrouve = true;
                        }
                    } else {
                        precedentElement = elementDansCourantZone;
                        if(elementDansCourantZone.suivant != null){
                            elementDansCourantZone = elementDansCourantZone.suivant;
                        }

                        while (elementDansCourantZone.typeValeur != e2 &&
                                elementDansCourantZone.typeValeur != elementDansProchaineZone.typeValeur && elementDansCourantZone.suivant != null) {
                            precedentElement = elementDansCourantZone;
                            elementDansCourantZone =
                                    elementDansCourantZone.suivant;
                        }

                        if (precedentElement.typeValeur == e2 || elementDansCourantZone.typeValeur == e2) {
                            e2Trouve = true;
                            if(unTour && precedentElement.typeValeur == e1){
                                lesDeuxTrouve = true;
                            }
                        }else {
                            prochaineZone = courantZone;
                            while(!trouveDansProchaineZone && prochaineZone.suivant != null){
                                prochaineZone = courantZone;
                                existeProchaineZone = true;
                                elementDansProchaineZone = prochaineZone.pointeurElement;
                                elementDansCourantZone = courantZone.pointeurElement;

                                if(courantZone.pointeurElement.typeValeur == e2){
                                    e2Trouve = true;
                                    trouveDansProchaineZone = true;
                                }else {
                                    if(elementDansCourantZone.suivant != null){
                                        elementDansCourantZone = elementDansCourantZone.suivant;
                                    }
                                    while (elementDansCourantZone.suivant != null && elementDansCourantZone.typeValeur != e2 &&
                                            elementDansCourantZone.typeValeur != elementDansProchaineZone.typeValeur){
                                        elementDansCourantZone =
                                                elementDansCourantZone.suivant;
                                    }

                                    if(elementDansCourantZone.typeValeur == e2){
                                        e2Trouve = true;
                                        trouveDansProchaineZone = true;
                                    }else {
                                        trouveDansProchaineZone = false;
                                        courantZone = courantZone.suivant;
                                    }
                                }
                            }
                        }
                    }
                    if(e2Trouve && !lesDeuxTrouve){
                        zoneE2Trouve = courantZone;
                        courantZone = zoneInit;

                        if(courantZone.suivant != null && zoneE2Trouve.suivant != null){
                            existeProchaineZone = true;
                            e1TrouveDansProchaineZone = true;
                            prochaineZone = courantZone;

                            while(prochaineZone.suivant != null && e1TrouveDansProchaineZone
                                    && !prochaineZone.typeZone.toString().equals(zoneE2Trouve.suivant.typeZone.toString())){
                                prochaineZone = courantZone;
                                existeProchaineZone = true;
                                elementDansProchaineZone = prochaineZone.pointeurElement;
                                elementDansCourantZone = courantZone.pointeurElement;

                                if(courantZone.pointeurElement.typeValeur == e1){
                                    courantZone = courantZone.suivant;
                                    e1Trouve = true;
                                    e1TrouveDansChaqueZones = true;
                                }else {
                                    prochaineZone = courantZone.suivant;

                                    if(prochaineZone != null){
                                        elementDansProchaineZone = prochaineZone.pointeurElement;
                                    }

                                    while (elementDansCourantZone.suivant != null && elementDansCourantZone.typeValeur != e1 &&
                                            elementDansCourantZone.typeValeur != elementDansProchaineZone.typeValeur ){
                                        elementDansCourantZone =
                                                elementDansCourantZone.suivant;
                                    }
                                    if(elementDansCourantZone.typeValeur == e1){
                                        trouveDansProchaineZone = true;
                                        e1TrouveDansProchaineZone = true;
                                        e1TrouveDansChaqueZones = true;
                                    }else {
                                        trouveDansProchaineZone = false;
                                    }

                                    if (!trouveDansProchaineZone){
                                        e1TrouveDansProchaineZone = false;
                                        e1TrouveDansChaqueZones = false;
                                    }
                                }
                            }
                        }else {
                            lesDeuxTrouve = trouverElementDansDerniereZone(courantZone, e1);
                        }
                    }
                } else {
                    elementDansCourantZone = courantZone.pointeurElement;
                    if (elementDansCourantZone.typeValeur == e2){
                        e2Trouve = true;
                    }

                    if (elementDansCourantZone.typeValeur == e1){
                        e1Trouve = true;
                    }

                    if(!e2Trouve || !e1Trouve){
                        while ((elementDansCourantZone.suivant != null && elementDansCourantZone.typeValeur != e2)
                                || (!e1Trouve && elementDansCourantZone.suivant != null)){
                            if (elementDansCourantZone.typeValeur == e1){
                                e1Trouve = true;
                            }
                            if(elementDansCourantZone.suivant != null){
                                elementDansCourantZone = elementDansCourantZone.suivant;
                            }
                        }

                        if (elementDansCourantZone.typeValeur == e2){
                            e2Trouve = true;
                        }
                        if (elementDansCourantZone.typeValeur == e1){
                            e1Trouve = true;
                        }
                    }
                    if(e1Trouve && e2Trouve){
                        lesDeuxTrouve = true;
                    }
                }
            }
        }
        if(e2Trouve && e1TrouveDansChaqueZones){
            lesDeuxTrouve = true;
        }
        return lesDeuxTrouve;
    }

    /**
     * Vérifie que <code>e1</code> est dans chaque zone à partir de
     * <code>z</code> (inclusivement) jusqu'à la zone où <code>e2</code> est.
     * La zone où <code>e2</code> apparait pour la première fois
     * (a partir de <code>z</code>) doit aussi contenir <code>e1</code>.
     * Si <code>e2</code> n'est pas dans une zone à partir de <code>z</code>,
     * alors <code>e1</code> doit être dans chaque zone de <code>z</code> jusqu'à
     * la fin de la liste.
     * @param z la zone de départ pour la recherche.
     * @param e1 l'élément à trouver dans chaque zone intermédiaire.
     * @param e2 l'élément à trouver dans une zone à partir de la zone
     *           <code>z</code>.
     * @return \(z_k = premiere( z, e2 ), ( r_k \not= \bottom \wedge \forall z_i \in [z..z_k], e1 \in z_i )
    \vee ( r_k = \bottom \wedge \forall z_i \in [z..z_m], e1 \in z_i )\)
     */
    public boolean contientR( Zone z, Element e1, Element e2 ) {
        MaillonZone courantZone = debutZone;
        MaillonZone precedentZone = null;
        MaillonElement elementDansCourantZone;
        MaillonZone prochaineZone = null;
        MaillonZone zoneInit;
        String zoneE2Trouve = "";
        String finZoneE2 = "";
        boolean lesDeuxTrouve = false;
        boolean e2Trouve = false;
        boolean e1Trouve = false;
        boolean e2DerniereZone = false;

        if(z != null && e1 != null && e2 != null){
            while (courantZone != null && courantZone.typeZone != z) {
                precedentZone = courantZone;
                courantZone = courantZone.suivant;
            }
            if (courantZone != null) {
                zoneInit = courantZone;
                if(courantZone.suivant != null){
                    elementDansCourantZone = courantZone.pointeurElement;
                    if (elementDansCourantZone.typeValeur == e2){
                        e2Trouve = true;
                        zoneE2Trouve =
                                courantZone.typeZone.toString();
                        prochaineZone = courantZone.suivant;
                        finZoneE2 = prochaineZone.typeZone.toString();
                    }
                    prochaineZone = courantZone.suivant;

                    if(!e2Trouve){
                        while ((elementDansCourantZone.suivant != null && elementDansCourantZone.typeValeur != e2)){
                            elementDansCourantZone = elementDansCourantZone.suivant;
                            if((elementDansCourantZone.parent != null
                                    && !courantZone.typeZone.toString().equals(elementDansCourantZone.parent.toString()))
                                    || (elementDansCourantZone.zoneParent != null
                                    && !courantZone.typeZone.toString().equals(elementDansCourantZone.zoneParent.toString()))){
                                precedentZone = courantZone;
                                prochaineZone = courantZone.suivant;
                                courantZone = courantZone.suivant;
                            }
                        }

                        if (elementDansCourantZone.typeValeur == e2){
                            e2Trouve = true;
                            zoneE2Trouve =
                                    precedentZone.typeZone.toString();
                            prochaineZone = precedentZone.suivant;

                            if(prochaineZone != null){
                                finZoneE2 =
                                        prochaineZone.typeZone.toString();
                            }else {
                                e2DerniereZone = true;
                            }
                        }
                    }
                    elementDansCourantZone = zoneInit.pointeurElement;
                    if (elementDansCourantZone.typeValeur == e1){
                        e1Trouve = true;
                    }

                    if(!e1Trouve && !e2DerniereZone){
                        do{
                            if(elementDansCourantZone.suivant != null && elementDansCourantZone.typeValeur != e1){
                                elementDansCourantZone = elementDansCourantZone.suivant;
                            }

                            if (elementDansCourantZone.typeValeur == e1){
                                e1Trouve = true;
                            }
                        }while ((elementDansCourantZone.parent != null && !elementDansCourantZone.parent.toString().equals(finZoneE2))
                                || ( elementDansCourantZone.zoneParent != null && !elementDansCourantZone.zoneParent.toString().equals(finZoneE2)));
                    }

                    if((!e1Trouve && e2DerniereZone) || !e2Trouve){
                        while (elementDansCourantZone.suivant != null && elementDansCourantZone.typeValeur != e1){
                            elementDansCourantZone = elementDansCourantZone.suivant;
                        }

                        if (elementDansCourantZone.typeValeur == e1){
                            e1Trouve = true;
                            lesDeuxTrouve = true;
                        }
                    }

                    if(e1Trouve && e2DerniereZone){
                        lesDeuxTrouve = true;
                    }

                    if(e1Trouve && e2Trouve){
                        lesDeuxTrouve = true;
                    }

                }else {
                    elementDansCourantZone = courantZone.pointeurElement;
                    if (elementDansCourantZone.typeValeur == e1){
                        e1Trouve = true;
                        lesDeuxTrouve = true;
                    }

                    if(!e1Trouve){
                        while (elementDansCourantZone.suivant != null && elementDansCourantZone.typeValeur != e1){
                            elementDansCourantZone = elementDansCourantZone.suivant;
                        }

                        if (elementDansCourantZone.typeValeur == e1){
                            e1Trouve = true;
                            lesDeuxTrouve = true;
                        }
                    }
                }
            }
        }
        return lesDeuxTrouve;
    }

    /**
     * Vérifie que <code>e1</code> est dans chaque zone à partir de
     * <code>z</code> (inclusivement) jusqu'à la zone où <code>e2</code> est.
     * La zone où <code>e2</code> apparait pour la première fois
     * (a partir de <code>z</code>) n'est pas obligé de contenir <code>e1</code>.
     * Si <code>e2</code> n'est pas dans une zone à partir de <code>z</code>,
     * alors <code>e1</code> doit être dans chaque zone de <code>z</code> jusqu'à
     * la fin de la liste.
     * @param z la zone de départ pour la recherche.
     * @param e1 l'élément à trouver dans chaque zone intermédiaire.
     * @param e2 l'élément à trouver dans une zone à partir de la zone
     *           <code>z</code>.
     * @return \(z_k = premiere( z, e2 ), ( r_k \not= \bottom \wedge \forall z_i \in [z..z_k[, e1 \in z_i )
    \vee ( r_k = \bottom \wedge \forall z_i \in [z..z_m], e1 \in z_i )\)
     */
    public boolean contientW( Zone z, Element e1, Element e2 ) {
        MaillonZone courantZone = debutZone;
        MaillonZone precedentZone = null;
        MaillonElement elementDansCourantZone;
        MaillonZone prochaineZone = null;
        MaillonZone zoneInit;
        String zoneE2Trouve = "";
        String finZoneE2 = "";
        boolean lesDeuxTrouve = false;
        boolean e2Trouve = false;
        boolean e1Trouve = false;
        boolean e2DerniereZone = false;
        boolean unTour = true;

        if(z != null && e1 != null && e2 != null){
            while (courantZone != null && courantZone.typeZone != z) {
                unTour = false;
                precedentZone = courantZone;
                courantZone = courantZone.suivant;
            }
            if (courantZone != null) {
                zoneInit = courantZone;
                if(courantZone.suivant != null){
                    elementDansCourantZone = courantZone.pointeurElement;
                    if (elementDansCourantZone.typeValeur == e2){
                        e2Trouve = true;
                        zoneE2Trouve =
                                courantZone.typeZone.toString();
                        prochaineZone = courantZone.suivant;
                        finZoneE2 = prochaineZone.typeZone.toString();
                    }
                    prochaineZone = courantZone.suivant;

                    if(!e2Trouve){
                        while ((elementDansCourantZone.suivant != null && elementDansCourantZone.typeValeur != e2)){
                            elementDansCourantZone = elementDansCourantZone.suivant;
                            if((elementDansCourantZone.parent != null
                                    && !courantZone.typeZone.toString().equals(elementDansCourantZone.parent.toString()))
                                    || (elementDansCourantZone.zoneParent != null
                                    && !courantZone.typeZone.toString().equals(elementDansCourantZone.zoneParent.toString()))){
                                precedentZone = courantZone;
                                prochaineZone = courantZone.suivant;
                                courantZone = courantZone.suivant;
                            }
                        }

                        if (elementDansCourantZone.typeValeur == e2){
                            e2Trouve = true;
                            zoneE2Trouve =
                                    precedentZone.typeZone.toString();
                            prochaineZone = precedentZone.suivant;

                            if(zoneInit.typeZone.toString().equals(zoneE2Trouve)){
                                lesDeuxTrouve = true;
                            }

                            if(prochaineZone != null && !lesDeuxTrouve){
                                finZoneE2 =
                                        precedentZone.typeZone.toString();
                            }else {
                                e2DerniereZone = true;
                            }
                        }
                    }
                    elementDansCourantZone = zoneInit.pointeurElement;
                    if (elementDansCourantZone.typeValeur == e1){
                        e1Trouve = true;
                    }

                    if(((!e1Trouve && e2DerniereZone) || !e2Trouve) && !lesDeuxTrouve){
                        while (elementDansCourantZone.suivant != null && elementDansCourantZone.typeValeur != e1){
                            elementDansCourantZone = elementDansCourantZone.suivant;
                        }

                        if (elementDansCourantZone.typeValeur == e1){
                            e1Trouve = true;
                            lesDeuxTrouve = true;
                        }
                    }

                    if(e1Trouve && e2DerniereZone && !lesDeuxTrouve) {
                        lesDeuxTrouve = true;
                    }

                    if(e1Trouve && e2Trouve && !lesDeuxTrouve){
                        lesDeuxTrouve = true;
                    }

                }else {
                    elementDansCourantZone = courantZone.pointeurElement;
                    if (elementDansCourantZone.typeValeur == e2 || elementDansCourantZone.typeValeur == e1){
                        e2Trouve = true;
                        lesDeuxTrouve = true;
                    }

                    if(!e2Trouve){
                        while ((elementDansCourantZone.suivant != null && elementDansCourantZone.typeValeur != e2)
                                || (elementDansCourantZone.suivant != null && elementDansCourantZone.typeValeur != e1)){
                            elementDansCourantZone = elementDansCourantZone.suivant;
                        }

                        if (elementDansCourantZone.typeValeur == e2 || elementDansCourantZone.typeValeur == e1){
                            e2Trouve = true;
                            lesDeuxTrouve = true;
                        }
                    }
                }
            }
        }
        return lesDeuxTrouve;
    }
}
