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

    Element debutElement;
    Zone debutZone;


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
    public void ajouter( Element e ) {

        //TODO while debut.suivant != null
        //TODO qd c est egal a null, ajouter

        try {

        }catch (IndexOutOfBoundsException o){

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
        return false;
    }

    /**
     * Vérifie si l'élément <code>e</code> peut être trouvé dans la zone
     * <code>z</code> OU une zone suivant la zone <code>z</code>.
     * @param z la zone où la recherche commence.
     * @param e l'élément recherché.
     * @return \(\exists z_i \in [z..z_m], e \in z_i\)
     */
    public boolean contientF( Zone z, Element e ) {
        return false;
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
        return false;
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
