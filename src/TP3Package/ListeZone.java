package TP3Package;

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
    int nbrElement;
    int nbrZone;

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

        //mettre si e est non null
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

        //TODO verifier si zone existe
            // TODO si existe, rajouter element a la fin de la liste de base
            // TODO si non, rajouter la Zone et ajouter l'element a la liste
            //  de base
        MaillonZone courantZone = debutZone;
        MaillonZone precedentZone = null;
        MaillonZone<Zone, Element> nouvelleZone = null;
        MaillonElement nouveauElement;
        MaillonElement courantElement = debutElement;
        MaillonElement precedentElement = null;

        //CAS 1: si la zone existe
        // si courantZone == null alors il n y a pas de zones ds la liste
        while (courantZone != null && courantZone.typeZone != z ){
            precedentZone = courantZone;
            courantZone = courantZone.suivant;
        }
        //si la zone existe alors ca ne va pas etre null
        //s il existe d autres zones aussi
        if(courantZone != null){
            MaillonElement elementTemp;
            MaillonElement precedentElementAvantProchaineZone = null;
            MaillonElement premierElementDansProchaineZone;
            MaillonZone prochaineZone;

            //s il y a une zone apres celle la alors il ne faut pas perdre
            // les donnees
            if(courantZone.suivant != null){
                prochaineZone = courantZone.suivant;
                premierElementDansProchaineZone = prochaineZone.getPointeurElement();
                elementTemp = debutElement;

                while (elementTemp != null && elementTemp.typeValeur != premierElementDansProchaineZone.typeValeur ){
                    precedentElementAvantProchaineZone = elementTemp; // a la
                    // fin c le dernier element dans zone
                    elementTemp = elementTemp.suivant; //a la fin c le
                    // premier element dans prochaine zone
                    System.out.println("je suis entre");
                }
                // bien remettre les pointeurs
                nouveauElement = new MaillonElement<Zone, Element>(z, e);
                nouveauElement.setSuivant(elementTemp);
                precedentElementAvantProchaineZone.setSuivant(nouveauElement);
            }else {
                //s il n y a pas de zone apres celle la et que c la bonne zone
                ajouter(e);
            }
        }else {
            //CAS 2: si la zone existe pas
            //TODO ajouter la zone avec son element
//            nouvelleZone = new MaillonZone<Zone, Element>(z,
//                    (MaillonElement)e, null);
//            precedentZone.setSuivant(nouvelleZone);


            if(debutZone == null){
                //TODO je pense que c bon (a revoir)
                //s il n existe pas de zone
                nouvelleZone = new MaillonZone<>(z,
                        e, null);
                debutZone = nouvelleZone;
                // nouvelleZone.setPointeurElement(ajouter(e));
                // debutElement = new MaillonElement<>(z, e);
                //ajouter(e);
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

        System.out.println( "le debut de la zone : "+ debutZone);
    }

    @Override
    public String toString() {
        return "ListeZone{" +
                "debutElement=" + debutElement +
                ", debutZone=" + debutZone +
                '}';
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
        //TODO verifier que z et e ne sont pas null
            //TODO aller a la zone z, si la zone z existe pas alors on retourne
            // false
            //TODO si la zone z existe, aller a la zone suivante de z et iterer
            // dans les elements jusqu a trouver e
                //TODO si e existe pas alors false
        MaillonZone courantZone = debutZone;
        MaillonZone precedentZone = null;
        MaillonZone nouvelleZone = null;
        MaillonElement elementDansCourantZone;
        MaillonZone prochaineZone;
        MaillonElement elementDansProchaineZone;
        MaillonElement precedentElement = null;
        boolean elementTrouve = false;
        boolean existeProchaineZone = false;
        if (z != null && e != null){
            //verifier si zone z existe
            while (courantZone != null && courantZone.typeZone != z ){
                precedentZone = courantZone;
                courantZone = courantZone.suivant;
            }

            //si la zone existe alors ca ne va pas etre null
            if(courantZone != null){
                if (courantZone.suivant != null){
                    existeProchaineZone = true;

                    //TODO

                    courantZone = courantZone.suivant;
                    // je dois le savoir avant de commencer



                    elementDansCourantZone = courantZone.pointeurElement;
                    //element trouve est le premier element dans la zone
                    // initiale
                    if(courantZone.pointeurElement.typeValeur == e){
                        elementTrouve = true;
                    }else { //voir si on peut trouver l element dans la zone

                        if(courantZone.suivant != null){
                            prochaineZone = courantZone.suivant;
                            elementDansProchaineZone = prochaineZone.pointeurElement;
                            while (elementDansCourantZone != null && elementDansCourantZone != e &&
                                    elementDansCourantZone.typeValeur != elementDansProchaineZone.typeValeur ){
                                precedentElement = elementDansCourantZone;
                                elementDansCourantZone =
                                        elementDansCourantZone.suivant;
                            }
                            // element trouve dans la zone initiale
                            if( precedentElement != null && precedentElement.typeValeur == e){
                                elementTrouve = true;
                            }
                        }else {
                            while (elementDansCourantZone != null && elementDansCourantZone.typeValeur != e &&
                                    elementDansCourantZone.typeValeur != null){
                                precedentElement = elementDansCourantZone;
                                elementDansCourantZone =
                                        elementDansCourantZone.suivant;
                            }
                            // element trouve dans la zone initiale
                            if( elementDansCourantZone != null && elementDansCourantZone.typeValeur == e){
                                elementTrouve = true;
                            }
                        }

                    }
                }
            }else {//la zone z existe pas
                elementTrouve = false;
            }

        }
        if(!existeProchaineZone){
            elementTrouve = false;
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
        //TODO verifier si z et e sont non null
            //TODO aller a la zone z et verifier si e est present
                //TODO si oui, alors pas la peine de continuer (return true)
                //TODO si non, aller dans les prochaines zones et verifier
        MaillonZone courantZone = debutZone;
        MaillonZone precedentZone = null;
        MaillonElement elementDansCourantZone;
        MaillonZone prochaineZone;
        MaillonElement elementDansProchaineZone;
        MaillonElement precedentElement = null;
        boolean trouveDansProchaineZone = false;
        boolean existeProchaineZone = false;
        boolean trouve = false;

        if (z != null && e != null) {
            //verifier si zone z existe
            while (courantZone != null && courantZone.typeZone != z) {
                precedentZone = courantZone;
                courantZone = courantZone.suivant;
            }
            if (courantZone != null) {//la zone z existe
                if (courantZone.suivant != null) {//savoir si j ai d autres zones
                    // je dois le savoir avant de commencer
                    prochaineZone = courantZone.suivant;
                    elementDansProchaineZone =
                            prochaineZone.getPointeurElement();
                    elementDansCourantZone = courantZone.getPointeurElement();
                    //element trouve est le premier element dans la zone
                    // initiale
                    if (courantZone.pointeurElement.typeValeur == e || elementDansProchaineZone.typeValeur == e) {
                        trouve = true;
                    } else { //voir si on peut trouver l element dans la zone
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
                        // element trouve dans la zone initiale
                        if (precedentElement.typeValeur == e || elementDansCourantZone.typeValeur == e) {
                            trouve = true;
                            System.out.println(trouve);
                        }else {//aller dans les prochaines zones pour essayer
                            // de trouver e
                            while(!trouveDansProchaineZone && prochaineZone.suivant != null){
                                prochaineZone = courantZone;
                                existeProchaineZone = true;
                                elementDansProchaineZone = prochaineZone.pointeurElement;

                                elementDansCourantZone = courantZone.pointeurElement;
                                //element trouve est le premier element dans la zone
                                // initiale
                                if(courantZone.pointeurElement.typeValeur == e){
                                    trouve = true;
                                    trouveDansProchaineZone = true;
                                }else { //voir si on peut trouver l element dans la zone
                                    if(elementDansCourantZone.suivant != null){
                                        elementDansCourantZone = elementDansCourantZone.suivant;
                                    }
                                    while (elementDansCourantZone.suivant != null && elementDansCourantZone.typeValeur != e &&
                                            elementDansCourantZone.typeValeur != elementDansProchaineZone.typeValeur){
                                        elementDansCourantZone =
                                                elementDansCourantZone.suivant;
                                    }
                                    // element trouve dans la zone suivante
                                    if(elementDansCourantZone.typeValeur == e){
                                        trouveDansProchaineZone = true;
                                    }else {
                                        trouveDansProchaineZone = false;
                                        courantZone = courantZone.suivant;
                                    }
                                }
                            }
                        }
                    }

                } else {// si c la derniere zone
                    elementDansCourantZone = courantZone.pointeurElement;
                    //pour le premier
                    if (elementDansCourantZone.typeValeur == e){
                        trouve = true;
                    }

                    if(!trouve){
                        //TODO je pense que c bon (a revoir)
                        while (elementDansCourantZone.suivant != null && elementDansCourantZone.typeValeur != e){
                            elementDansCourantZone = elementDansCourantZone.suivant;
                        }

                        //pour le dernier element de la liste de base
                        if (elementDansCourantZone.typeValeur == e){
                            trouve = true;
                        }
                    }
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
        boolean zoneSuivant;
        boolean trouveDansZoneInit = false;
        boolean trouveDansToutesProchainesZones = false;
        boolean trouveDansProchaineZone = false;
        boolean existeProchaineZone = false;

        //TODO aller a la zone z, prendre son pointeur element, aller a la
        // prochaine zone, prendre son premier element, ... jusqu a la fin.

        boolean trouve = false;
        //si la liste de zone ne contient pas la zone z alors on continue mm pas

        if(z != null && e != null){

            //verifier si zone z existe
            while (courantZone != null && courantZone.typeZone != z ){
                precedentZone = courantZone;
                courantZone = courantZone.suivant;
            }

            //si la zone existe alors ca ne va pas etre null
            if(courantZone != null){

                //TODO verifier si element e existe dans la zone z et les
                // suivantes

                //s il y a des zones apres
                if (courantZone.suivant != null){

                    existeProchaineZone = true;
                    // je dois le savoir avant de commencer
                    prochaineZone = courantZone.suivant;
                    elementDansProchaineZone =
                            prochaineZone.getPointeurElement();

                    elementDansCourantZone = courantZone.getPointeurElement();
                    //element trouve est le premier element dans la zone
                    // initiale
                    if(courantZone.pointeurElement.typeValeur == e){
                        trouveDansZoneInit = true;
                    }else { //voir si on peut trouver l element dans la zone
                        while (elementDansCourantZone != e &&
                                elementDansCourantZone.typeValeur != elementDansProchaineZone.typeValeur && elementDansCourantZone.suivant != null){
                            precedentElement = elementDansCourantZone;
                            elementDansCourantZone =
                                    elementDansCourantZone.suivant;
                        }
                        // element trouve dans la zone initiale
                        if(precedentElement.typeValeur == e){
                            trouveDansZoneInit = true;
                        }
                    }

                    //si on a trouve dans la premiere zone
                    if(trouveDansZoneInit){
                        courantZone = prochaineZone;
                        if(courantZone.suivant != null){ //s il existe des
                            // prochaines zones
                            existeProchaineZone = true;

                            //trouveDansProchaineZone = true;
                            trouveDansToutesProchainesZones = true;
                            //TODO
                            while(trouveDansToutesProchainesZones && prochaineZone.suivant != null){
                                prochaineZone = courantZone;
                                existeProchaineZone = true;
                                elementDansProchaineZone = prochaineZone.pointeurElement;

                                elementDansCourantZone = courantZone.pointeurElement;
                                //element trouve est le premier element dans la zone
                                // initiale
                                if(courantZone.pointeurElement.typeValeur == e){
                                    trouveDansZoneInit = true;
                                    courantZone = courantZone.suivant;
                                }else { //voir si on peut trouver l element dans la zone
                                    prochaineZone = courantZone.suivant;

                                    if(prochaineZone != null){
                                        elementDansProchaineZone = prochaineZone.pointeurElement;
                                    }

                                    while (elementDansCourantZone.suivant != null && elementDansCourantZone.typeValeur != e &&
                                            elementDansCourantZone.typeValeur != elementDansProchaineZone.typeValeur ){
                                        elementDansCourantZone =
                                                elementDansCourantZone.suivant;
                                    }
                                    // element trouve dans la zone suivante
                                    if(elementDansCourantZone.typeValeur == e){
                                        trouveDansProchaineZone = true;
                                        trouveDansToutesProchainesZones = true;
                                    }else {
                                        trouveDansProchaineZone = false;
                                    }

                                    if (!trouveDansProchaineZone){
                                        trouveDansToutesProchainesZones = false;
                                    }
                                }
                            }


                        }else { // si c la derniere zone
                            elementDansCourantZone = courantZone.pointeurElement;
                            //pour le premier
                            if (elementDansCourantZone.typeValeur == e){
                                trouve = true;
                            }

                            if(!trouve){
                                //TODO je pense que c bon (a revoir)
                                while (elementDansCourantZone.suivant != null && elementDansCourantZone.typeValeur != e){
                                    elementDansCourantZone = elementDansCourantZone.suivant;
                                }

                                //pour le dernier element de la liste de base
                                if (elementDansCourantZone.typeValeur == e){
                                    trouve = true;
                                }
                            }
                        }

                    } //pas trouve dans la zone init

                }else{ //si c la derniere zone
                    elementDansCourantZone = courantZone.pointeurElement;
                    //pour le premier
                    if (elementDansCourantZone.typeValeur == e){
                        trouveDansZoneInit = true;
                        trouve = true;
                    }

                    if(!trouve){
                        while (elementDansCourantZone.suivant != null && elementDansCourantZone.typeValeur != e){
                            elementDansCourantZone = elementDansCourantZone.suivant;
                        }

                        //pour le dernier element de la liste de base
                        if (elementDansCourantZone.typeValeur == e){
                            trouve = true;
                        }
                    }


                }


            }else{//la zone existe pas

                trouve = false;
            }
        }

        if(existeProchaineZone && trouveDansZoneInit && trouveDansToutesProchainesZones){
            trouve = true;
        }

        if(!existeProchaineZone && trouveDansZoneInit){
            trouve = true;
        }

        return trouve;
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
        return false;
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
        return false;
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
        return false;
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
        return false;
    }
}
